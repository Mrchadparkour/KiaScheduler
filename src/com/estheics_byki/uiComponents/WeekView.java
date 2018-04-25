package com.estheics_byki.uiComponents;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import com.estheics_byki.dataComponents.Month;
import java.util.concurrent.*;

public class WeekView extends UIComponent {

    private static AnchorPane visibleWeek;
    private static Month vw = new Month();
    private static boolean eventPlaying = false;
    private static ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();

    public WeekView(Scene scene, Parent parent) {
        super(scene, parent);
    }

    private void drawVisibleWeek(double y) {
        double margin = scene.getWidth() / 20;
        visibleWeek = new AnchorPane();
        visibleWeek.setLayoutX(scene.getWidth() / 4 + margin);
        visibleWeek.setLayoutY(y);
        visibleWeek.setPrefHeight(scene.getHeight() - (margin * 2));
        visibleWeek.setPrefWidth(scene.getWidth() - (scene.getWidth() / 4 + (margin * 2)));
        visibleWeek.setStyle("-fx-background-color: #FFFFFF");
    }

    @Override
    public void initView() {
        drawVisibleWeek(scene.getWidth() / 20);
        ((AnchorPane)(parent)).getChildren().addAll(visibleWeek);

        //Scroll to change change week view
        parent.setOnScroll(event -> {
            if (!eventPlaying) {
                int dir = vw.moveWeek((int)(event.getDeltaY()));
                if (dir != 0){
                    eventPlaying = true;
                    animateWeekChange(dir);
                    s.schedule((Runnable)(() -> eventPlaying = false), 1, TimeUnit.SECONDS);
                }
            }
        });
    }

    private void animateWeekChange(int dir) {
        double margin = scene.getWidth() / 20;

        AnchorPane temp = (AnchorPane) super.clone(visibleWeek);
        temp.setLayoutY(margin);
        temp.setStyle("-fx-background-color: #FFFFFF");

        TranslateTransition t = new TranslateTransition(Duration.seconds(1), temp);
        FadeTransition f = new FadeTransition(Duration.seconds(1), temp);

        f.setToValue(0.0);
        t.setToY(-1 * dir * (scene.getHeight() - margin));
        t.play();
//        f.play();

        ((AnchorPane)(parent)).getChildren().addAll(temp);
        ((AnchorPane)(parent)).getChildren().removeAll(visibleWeek);
        drawVisibleWeek(dir * scene.getHeight());
        ((AnchorPane)(parent)).getChildren().addAll(visibleWeek);

        TranslateTransition t2 = new TranslateTransition(Duration.seconds(1), visibleWeek);
        FadeTransition f2 = new FadeTransition(Duration.seconds(1), visibleWeek);
        t2.setToY( -1 * dir * (scene.getHeight() - (margin * dir)) );
        f2.setFromValue(0.0);
        f2.setToValue(1.0);

//        f2.play();
        t2.play();
    }
}
