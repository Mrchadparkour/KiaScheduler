package com.estheics_byki.uiComponents;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

abstract class UIComponent {
    Scene scene;
    Parent parent;

    abstract void initView();
    abstract void handleResize();

    UIComponent(Scene scene, Parent parent) {
        this.scene = scene;
        this.parent = parent;
    }
}
