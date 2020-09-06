package me.kwatra.classicpong;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Menu extends Pane {

    public Menu(MainApp superApp) {
        super();

        superApp.getPrimaryStage().setHeight(200);
        superApp.getPrimaryStage().setWidth(300);

        Button twoPlayer = new Button("Two-Player");
        Button impossible = new Button("Impossible");

        twoPlayer.setMaxWidth(100);
        twoPlayer.setMinWidth(100);
        impossible.setMaxWidth(100);
        impossible.setMinWidth(100);

        ImageView logoView = new ImageView();
        logoView.setImage(new Image("https://i.imgur.com/MxLmf5G.jpg", true));
        logoView.setFitWidth(80);
        logoView.setFitHeight(80);

        logoView.setPreserveRatio(true);

        twoPlayer.setOnAction(event -> superApp.startTwoPlayer());
        impossible.setOnAction(event -> superApp.startImpossible());

        this.getChildren().add(twoPlayer);
        this.getChildren().add(impossible);
        this.getChildren().add(logoView);

        logoView.relocate(110, 0);
        double width = superApp.getPrimaryStage().getWidth();
        double height = superApp.getPrimaryStage().getHeight();
        twoPlayer.relocate(width / 2 - 50, height / 2);
        impossible.relocate(width / 2 - 50, height / 2 + 30);
    }
}
