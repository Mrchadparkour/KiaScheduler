package sample.uiComponents;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.VisibleWeekControl;

public class WeekView extends UIComponent {

    private static AnchorPane visibleWeek = new AnchorPane();
    private static VisibleWeekControl vw = new VisibleWeekControl();
    private static boolean eventPlaying = false;

    public WeekView(Scene scene, Parent parent) {
        super(scene, parent);
    }
    @Override
    public void initView() {
        //Add this component to the scene
        ((AnchorPane)(parent)).getChildren().addAll(visibleWeek);

        double margin = scene.getWidth() / 20;
        visibleWeek.setLayoutX(scene.getWidth() / 4 + margin);
        visibleWeek.setLayoutY(margin);
        visibleWeek.setPrefHeight(scene.getHeight() - (margin * 2));
        visibleWeek.setPrefWidth(scene.getWidth() - (scene.getWidth() / 4 + (margin * 2)));
        visibleWeek.setStyle("-fx-background-color: " + vw.getCurrWeek().getColor());

        //Scroll to change change week view
        parent.setOnScroll(event -> {
            animateWeekChange(vw.moveWeek((int)(event.getDeltaY())));
        });
    }

    private void animateWeekChange(int dir) {
        if (dir == 0) return;
        double margin = scene.getWidth() / 20;

        AnchorPane temp = (AnchorPane) super.clone(visibleWeek);
        temp.setLayoutY(margin);

        TranslateTransition t = new TranslateTransition(Duration.seconds(2), temp);
        FadeTransition f = new FadeTransition(Duration.seconds(2), temp);

        f.setToValue(0.2);
        t.setToY(-1 * dir * (scene.getHeight() - margin));
        t.play();
        f.play();

        ((AnchorPane)(parent)).getChildren().addAll(temp);
        ((AnchorPane)(parent)).getChildren().removeAll(visibleWeek);

        visibleWeek = new AnchorPane();
        ((AnchorPane)(parent)).getChildren().addAll(visibleWeek);
        visibleWeek.setLayoutX(scene.getWidth() / 4 + margin);
        visibleWeek.setLayoutY( dir * scene.getHeight() );
        visibleWeek.setPrefHeight(scene.getHeight() - (margin * 2));
        visibleWeek.setPrefWidth(scene.getWidth() - (scene.getWidth() / 4 + (margin * 2)));
        visibleWeek.setStyle("-fx-background-color: " + vw.getCurrWeek().getColor());

        TranslateTransition t2 = new TranslateTransition(Duration.seconds(2), visibleWeek);
        FadeTransition f2 = new FadeTransition(Duration.seconds(2), visibleWeek);
        t2.setToY( -1 * dir * (scene.getHeight() - (margin * dir)) );
        f2.setFromValue(0.2);
        f2.setToValue(1.0);

        f2.play();
        t2.play();
    }
}
