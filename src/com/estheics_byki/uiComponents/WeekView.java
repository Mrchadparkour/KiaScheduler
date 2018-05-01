package com.estheics_byki.uiComponents;

import com.estheics_byki.dataComponents.Day;
import com.estheics_byki.dataComponents.ThirtyDay;
import com.estheics_byki.dataComponents.Week;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WeekView extends UIComponent {

    private static AnchorPane visibleWeek;
    private static ThirtyDay thirtyDay = new ThirtyDay();
    private double y;
    private double margin;
    private Scene scene;

    public WeekView(Scene scene, Parent parent) {
        super(scene, parent);
        this.scene = scene;
        this.margin = scene.getWidth() / 20;
    }

    @Override
    public void handleResize() {
        this.margin = scene.getWidth() / 20;
        for (int i = 0; i < visibleWeek.getChildren().size(); i++) {
            Node n = visibleWeek.getChildren().get(i);
            if (n instanceof Text) {
                n.setLayoutX(scene.getWidth() / 4);
                n.setLayoutY((i * margin * 1.2) + margin / 2);
            } else {
                ((AnchorPane) (n)).setPrefHeight(margin);
                ((AnchorPane) (n)).setPrefWidth(scene.getWidth() - (scene.getWidth() / 4 + margin));
                n.setLayoutY((i * margin * 1.2));
                n.setLayoutX(scene.getWidth() / 4 + margin / 2);
            }
        }
    }

    private void showName(Week w, double y, Text t) {
        t.setText(w.genName());
        t.setFont(Font.loadFont(WeekView.class.getResource("../assets/fonts/Roboto-Bold.ttf").toExternalForm(), 10));
        t.setStyle("-fx-font-size:30;");
        t.setLayoutX(scene.getWidth() / 2);
        t.setLayoutY(y);
        visibleWeek.getChildren().add(t);
    }

    private void drawVWeek() {
        int i = 0;
        visibleWeek = new AnchorPane();
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.5, 0.5, 0.5));
        for (Week w : thirtyDay.getWeeks()) {
            showName(w, (i * margin * 1.2) + margin / 1.5, new Text());
            i++;
            for (Day d : w.getDays()) {
                AnchorPane pane = new AnchorPane();
                pane.setPrefHeight(margin);
                pane.setPrefWidth(scene.getWidth() - (scene.getWidth() / 4 + margin));
                pane.setLayoutY((i * margin * 1.2));
                pane.setEffect(dropShadow);
                pane.setStyle("-fx-background-color: #FFFFFF;");
                dayInfo(d, pane);
                visibleWeek.getChildren().add(pane);
                i++;
            }
        }
    }

    private void dayInfo(Day d, AnchorPane p) {
        Text t = new Text();
        t.setText(d.fDate());
        AnchorPane.setLeftAnchor(t, 5.0);
        AnchorPane.setTopAnchor(t, 5.0);
        p.getChildren().add(t);
    }

    @Override
    public void initView() {
        //Scroll to change change week view
        parent.setOnScroll(event -> {
            double dY = event.getDeltaY();
//            if (this.y + dY > ) {
//
//            }
            this.y += dY;
            System.out.println(y);
            visibleWeek.setLayoutY(y);
        });

        drawVWeek();
        ((AnchorPane) (parent)).getChildren().addAll(visibleWeek);
    }
}
