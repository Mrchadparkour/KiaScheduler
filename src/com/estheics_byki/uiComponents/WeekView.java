package com.estheics_byki.uiComponents;

import com.estheics_byki.dataComponents.Day;
import com.estheics_byki.dataComponents.ThirtyDay;
import com.estheics_byki.dataComponents.Week;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class WeekView extends UIComponent {

    private Scene scene;
    private static AnchorPane visibleWeek;
    private static ThirtyDay thirtyDay = new ThirtyDay();
    private double y;
    private double bottomLim;
    private Font myFont;

    //SIZING CALCULATORS
    private double margin() {
        return scene.getWidth() / 20;
    }

    private double dayPaneStep(double m, int i) {
        return m * 1.2 * i;
    }

    private double dayPaneHeight(double m) {
        return m * 1.17;
    }

    private double dayPaneWidth(double m) {
        double sw = scene.getWidth();
        return sw - (sw / 4)- m;
    }

    //End SIZING

    public WeekView(Scene scene, Parent parent) {
        super(scene, parent);
        this.scene = scene;
        this.myFont = Font.loadFont(WeekView.class.getResource("../assets/fonts/Montserrat-Regular.ttf").toExternalForm(), 10);
    }

    @Override
    public void handleResize() {
        double m = margin();
        for (int i = 0; i < visibleWeek.getChildren().size(); i++) {
            Node n = visibleWeek.getChildren().get(i);
            if (n instanceof Text) {
                n.setLayoutX((scene.getWidth() / 4) + m);
                n.setLayoutY(dayPaneStep(m, i) + m );
            } else {
                ((AnchorPane) (n)).setPrefHeight(dayPaneHeight(m));
                ((AnchorPane) (n)).setPrefWidth(dayPaneWidth(m));
                n.setLayoutY(dayPaneStep(m, i));
                n.setLayoutX((scene.getWidth() / 4) + m/2);
            }
            bottomLim = -1 * (i * m) + 2 * m;
            animateToScrollBounds(y);
        }
    }

    private void showWeekTitle(Week w, double y, Text t) {
        t.setText(w.genName());
        t.setFont(myFont);
        t.setStyle("-fx-font-size:15");
        t.setFill(Color.WHITESMOKE);
        visibleWeek.getChildren().add(t);
    }

    private void blueBoxDate(AnchorPane dp, int dayNumber) {
        Text t = new Text();
        t.setText(""+ dayNumber);
        t.setFont(myFont);
        t.setId("datenum");
//        Pane bb = new Pane();
        AnchorPane.setRightAnchor(t,10.0);
        AnchorPane.setBottomAnchor(t, 0.0);
        AnchorPane.setTopAnchor(t,0.0);
//        System.out.println(dp.getPrefWidth() / 2);
//        bb.setPrefWidth(dayPaneWidth(margin()) / 10);
        dp.getChildren().add(t);
    }

    private void drawVWeek() {
        double m = margin();
        int i = 0;
        visibleWeek = new AnchorPane();
        for (Week w : thirtyDay.getWeeks()) {
            showWeekTitle(w, dayPaneStep(m, i) + m / 1.5, new Text());
            i++;
            for (Day d : w.getDays()) {
                AnchorPane pane = new AnchorPane();
                pane.setId("daypane");
                dayInfo(d, pane);
                visibleWeek.getChildren().add(pane);
                i++;
            }
        }
        bottomLim = -1 * (i * m) + 2 * m;
    }

    private void dayInfo(Day d, AnchorPane p) {
        Text t = new Text();
        t.setText(d.fDate());
        AnchorPane.setLeftAnchor(t, 5.0);
        AnchorPane.setTopAnchor(t, margin() /2);
        p.getChildren().add(t);

        blueBoxDate(p, d.getDayVal());
    }

    @Override
    public void initView() {
        //Scroll to change change week view
        parent.setOnScroll(event -> {
            double newY = event.getDeltaY() + y;
            if (animateToScrollBounds(newY)) {
                this.y = newY;
                visibleWeek.setLayoutY(y);
            }
        });

        drawVWeek();
        ((AnchorPane) (parent)).getChildren().addAll(visibleWeek);
    }

    private boolean animateToScrollBounds(double newY) {
        if (!(newY < 80.0 && newY > bottomLim)) {
            double safeY = newY > 0.0 ? -40.0 : 40;
            TranslateTransition tt = new TranslateTransition(Duration.seconds(1), visibleWeek);
            tt.setToY(safeY);
            this.y += safeY;
            tt.play();
            return false;
        }

        return true;

    }
}
