package com.estheics_byki;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.estheics_byki.uiComponents.Dashboard;
import com.estheics_byki.uiComponents.WeekView;

public class Main extends Application {

    private static AnchorPane outerAp = new AnchorPane();
    private static Scene scene = new Scene(outerAp, 1600, 900);
    private static Dashboard dash = new Dashboard(scene, outerAp);
    private static WeekView wv = new WeekView(scene, outerAp);

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Handle window resize
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            outerAp.setMaxHeight(scene.getHeight());
            outerAp.setPrefHeight(scene.getHeight());
            dash.handleResize();
            wv.handleResize();
        });

        //load css
        scene.getStylesheets().add(getClass().getResource("assets/css/main.css").toExternalForm());
        //set stage and run ui
        primaryStage.setScene(scene);

        //initialize components
        dash.initView();
        wv.initView();

        outerAp.setId("backdrop");

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}