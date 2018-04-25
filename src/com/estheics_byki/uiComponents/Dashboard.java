package com.estheics_byki.uiComponents;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Dashboard extends UIComponent {
    private AnchorPane dashboard = new AnchorPane();

    public Dashboard(Scene scene, Parent parent) {
        super(scene, parent);
    }

    public void initView() {
        dashboard.setId("dashboard");
        ((AnchorPane)(parent)).getChildren().addAll(dashboard);

        double dashEnd = scene.getWidth() / 4;
        AnchorPane.setLeftAnchor(dashboard, -5.0);
        AnchorPane.setBottomAnchor(dashboard, -5.0);
        AnchorPane.setTopAnchor(dashboard, -5.0);
        dashboard.setMaxWidth(dashEnd);
        dashboard.setPrefWidth(dashEnd);
    }

    public AnchorPane getDashboard() {
        return dashboard;
    }
}
