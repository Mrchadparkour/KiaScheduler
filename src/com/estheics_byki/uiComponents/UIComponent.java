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

    Node clone(Node node) {
        AnchorPane pane = new AnchorPane();
        pane.setLayoutX(node.getLayoutX());
        pane.setLayoutY(node.getLayoutY());
        pane.setPrefWidth(((Pane)(node)).getPrefWidth());
        pane.setPrefHeight(((Pane)(node)).getPrefHeight());
        pane.setStyle(node.getStyle());
        return pane;
    }
}
