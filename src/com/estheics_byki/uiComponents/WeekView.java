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
    private static ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();
    private double margin;
    private Scene scene;
    private Week currWeek;

    public WeekView(Scene scene, Parent parent) {
        super(scene, parent);
        this.scene = scene;
        this.currWeek = thirtyDay.getCurrWeek();
        this.margin = scene.getWidth() / 20;
    }

    private void drawVisibleWeek(double y) {
        visibleWeek = new AnchorPane();
        visibleWeek.setLayoutX(scene.getWidth() / 4 + margin);
        visibleWeek.setLayoutY(y);
        visibleWeek.setPrefHeight(scene.getHeight() - (margin * 2));
        visibleWeek.setPrefWidth(scene.getWidth() - (scene.getWidth() / 4 + (margin * 2)));
        visibleWeek.setStyle("-fx-background-color: #FFF");
        showName();
        showDays();
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
        AnchorPane temp = (AnchorPane) super.clone(visibleWeek);
        temp.setLayoutY(margin);
        temp.setStyle("-fx-background-color: #FFF");

        TranslateTransition t = new TranslateTransition(Duration.seconds(1), temp);

        t.setToY(-1 * dir * (scene.getHeight() - margin));
        t.play();

        ((AnchorPane)(parent)).getChildren().addAll(temp);
        ((AnchorPane)(parent)).getChildren().removeAll(visibleWeek);

        // TODO: 4/29/18 animate text along with visibleWeek
        drawVisibleWeek(dir * scene.getHeight());
        ((AnchorPane)(parent)).getChildren().addAll(visibleWeek);

        TranslateTransition t2 = new TranslateTransition(Duration.seconds(1), visibleWeek);
        t2.setToY( -1 * dir * (scene.getHeight() - (margin * dir)) );

        t2.play();
    }

    private void showName() {
        Text t = new Text();
        t.setText(currWeek.genName());
        t.setFont(Font.loadFont(WeekView.class.getResource("../assets/fonts/Roboto-Bold.ttf").toExternalForm(), 10));
        t.setStyle("-fx-font-size:30;");
        AnchorPane.setLeftAnchor(t,50.0);
        AnchorPane.setTopAnchor(t,50.0);
        visibleWeek.getChildren().add(t);
    }

    private void showDays() {
        for (int i=0; i < currWeek.getDays().size() - 1; i++) {
            AnchorPane ap = new AnchorPane();
            AnchorPane.setLeftAnchor(ap, margin);
            AnchorPane.setRightAnchor(ap, margin);
            AnchorPane.setTopAnchor(ap, (margin * i) + margin * 2 );
            ap.setPrefHeight(margin * .7);
            ap.setStyle("-fx-background-color: #f0f0f0");
            visibleWeek.getChildren().add(ap);
        }
    }
}
