package com.estheics_byki.uiComponents;

import com.estheics_byki.dataComponents.ThirtyDay;
import com.estheics_byki.dataComponents.Week;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.concurrent.*;

public class WeekView extends UIComponent {

    private static AnchorPane visibleWeek;
    private static ThirtyDay thirtyDay = new ThirtyDay();
    private static boolean eventPlaying = false;
    private static Week currWeek;
    private static ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();

    public WeekView(Scene scene, Parent parent) {
        super(scene, parent);
        this.currWeek = thirtyDay.getCurrWeek();
    }

    private void drawVisibleWeek(double y) {
        double margin = scene.getWidth() / 20;
        visibleWeek = new AnchorPane();
        visibleWeek.setLayoutX(scene.getWidth() / 4 + margin);
        visibleWeek.setLayoutY(y);
        visibleWeek.setPrefHeight(scene.getHeight() - (margin * 2));
        visibleWeek.setPrefWidth(scene.getWidth() - (scene.getWidth() / 4 + (margin * 2)));
        visibleWeek.setStyle("-fx-background-color: #FFFFFF");
        showName();
    }

    @Override
    public void initView() {

        //Scroll to change change week view
        parent.setOnScroll(event -> {
            if (!eventPlaying) {
                int dir = thirtyDay.moveWeek((int)(event.getDeltaY()));
                currWeek = thirtyDay.getCurrWeek();
                if (dir != 0){
                    eventPlaying = true;
                    animateWeekChange(dir);
                    s.schedule((Runnable)(() -> eventPlaying = false), 1, TimeUnit.SECONDS);
                }
            }
        });

        drawVisibleWeek(scene.getWidth() / 20);
        ((AnchorPane)(parent)).getChildren().addAll(visibleWeek);
    }

    private void animateWeekChange(int dir) {
        double margin = scene.getWidth() / 20;

        AnchorPane temp = (AnchorPane) super.clone(visibleWeek);
        temp.setLayoutY(margin);
        temp.setStyle("-fx-background-color: #FFFFFF");

        TranslateTransition t = new TranslateTransition(Duration.seconds(1), temp);

        t.setToY(-1 * dir * (scene.getHeight() - margin));
        t.play();

        ((AnchorPane)(parent)).getChildren().addAll(temp);
        ((AnchorPane)(parent)).getChildren().removeAll(visibleWeek);
        drawVisibleWeek(dir * scene.getHeight());
        ((AnchorPane)(parent)).getChildren().addAll(visibleWeek);

        TranslateTransition t2 = new TranslateTransition(Duration.seconds(1), visibleWeek);
        t2.setToY( -1 * dir * (scene.getHeight() - (margin * dir)) );

        t2.play();
    }

    private void showName() {
//        TextFlow tf = new TextFlow();
        Text t = new Text();
        t.setText(currWeek.genName());
        t.setFont(Font.loadFont("file:assets/fonts/Roboto_Bold.ttf", 1000));
        visibleWeek.getChildren().add(t);
    }
}
