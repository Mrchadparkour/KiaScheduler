package sample.uiComponents;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;

public class Backdrop extends UIComponent {
    public Backdrop(Scene scene, Parent parent) {
        super(scene, parent);
    }

    @Override
    public void initView() {
        AnchorPane bd = new AnchorPane();
        bd.setId("backdrop");
        bd.setEffect(new GaussianBlur());
        AnchorPane.setTopAnchor(bd, 0.0);
        AnchorPane.setBottomAnchor(bd, 0.0);
        AnchorPane.setRightAnchor(bd, 0.0);
        AnchorPane.setLeftAnchor(bd, scene.getWidth() / 4);
        ((AnchorPane)(parent)).getChildren().addAll(bd);
    }
}
