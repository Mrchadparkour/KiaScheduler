package sample.uiComponents;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

abstract class UIComponent {
    protected Scene scene;
    protected Parent parent;

    abstract void initView();

    protected UIComponent(Scene scene, Parent parent) {
        this.scene = scene;
        this.parent = parent;
    }

    protected Node clone(Node node) {
        AnchorPane pane = new AnchorPane();
        pane.setLayoutX(node.getLayoutX());
        pane.setLayoutY(node.getLayoutY());
        pane.setPrefWidth(((Pane)(node)).getPrefWidth());
        pane.setPrefHeight(((Pane)(node)).getPrefHeight());
        pane.setStyle(node.getStyle());
        return pane;
    }
}
