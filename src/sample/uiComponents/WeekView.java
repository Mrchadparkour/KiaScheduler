package sample.uiComponents;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.VisibleWeekControl;
import java.util.concurrent.*;

public class WeekView extends UIComponent {

    private static AnchorPane visibleWeek = new AnchorPane();
    private static VisibleWeekControl vw = new VisibleWeekControl();
    private static boolean eventPlaying = false;
    private static ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();

    private static Runnable task = () -> {
        eventPlaying = false;
    };

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
            if (!eventPlaying) {
                int dir = vw.moveWeek((int)(event.getDeltaY()));
                if (dir != 0){
                    eventPlaying = true;
                    animateWeekChange(dir);
                    s.schedule(task, 1, TimeUnit.SECONDS);
                }
            }
        });
    }

    private void animateWeekChange(int dir) {
        double margin = scene.getWidth() / 20;

        AnchorPane temp = (AnchorPane) super.clone(visibleWeek);
        temp.setLayoutY(margin);

        TranslateTransition t = new TranslateTransition(Duration.seconds(1), temp);
        FadeTransition f = new FadeTransition(Duration.seconds(1), temp);

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

        TranslateTransition t2 = new TranslateTransition(Duration.seconds(1), visibleWeek);
        FadeTransition f2 = new FadeTransition(Duration.seconds(1), visibleWeek);
        t2.setToY( -1 * dir * (scene.getHeight() - (margin * dir)) );
        f2.setFromValue(0.2);
        f2.setToValue(1.0);

        f2.play();
        t2.play();
    }
}
