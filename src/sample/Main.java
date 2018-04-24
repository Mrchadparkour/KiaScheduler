package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.uiComponents.Backdrop;
import sample.uiComponents.Dashboard;
import sample.uiComponents.WeekView;

public class Main extends Application {

    private static AnchorPane outerAp = new AnchorPane();
    private static Scene scene = new Scene(outerAp, 1600, 900);
    private static Dashboard dash = new Dashboard(scene, outerAp);
    private static WeekView wv = new WeekView(scene, outerAp);
    private static Backdrop bd = new Backdrop(scene, outerAp);

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Handle window resize
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            double margin = scene.getWidth() / 20;
            double newDashEnd = scene.getWidth() /4;
            AnchorPane dashNode = dash.getDashboard();
            outerAp.setMaxHeight(scene.getHeight());
            outerAp.setPrefHeight(scene.getHeight());
            dashNode.setMaxWidth(newDashEnd);
            dashNode.setPrefWidth(newDashEnd);
            outerAp.getChildren().forEach(node -> {
                node.setLayoutX(scene.getWidth() / 4 + margin);
                node.setLayoutY(margin);
                ((AnchorPane)(node)).setPrefHeight(scene.getHeight() - (margin * 2));
                ((AnchorPane)(node)).setPrefWidth(scene.getWidth() - (scene.getWidth() / 4 + (margin * 2)));
            });
        });

        //load css
        scene.getStylesheets().add(getClass().getResource("assets/css/main.css").toExternalForm());
        //set stage and run ui
        primaryStage.setScene(scene);

        //initialize components
        dash.initView();
        wv.initView();
//        bd.initView();
        outerAp.setId("backdrop");

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
