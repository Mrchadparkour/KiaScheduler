package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.uiComponents.Dashboard;
import sample.uiComponents.WeekView;

public class Main extends Application {

    private static AnchorPane outerAp = new AnchorPane();
    private static Scene scene = new Scene(outerAp, 1600, 900);
    private static Dashboard dash = new Dashboard(scene, outerAp);
    private static WeekView wv = new WeekView(scene, outerAp);

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Handle window resize
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newDashEnd = scene.getWidth() /4;
            AnchorPane dashNode = dash.getDashboard();
            outerAp.setMaxHeight(scene.getHeight());
            outerAp.setPrefHeight(scene.getHeight());
            dashNode.setMaxWidth(newDashEnd);
            dashNode.setPrefWidth(newDashEnd);
        });

        //load css
        scene.getStylesheets().add(getClass().getResource("assets/css/main.css").toExternalForm());
        //set stage and run ui
        primaryStage.setScene(scene);

        //initialize components
        dash.initView();
        wv.initView();


        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
