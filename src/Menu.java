import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Menu extends World {
    private Scene scene;
    private BorderPane rootNode;

    private static final Menu openingScreen = new Menu();


    public Menu() {
        rootNode = new BorderPane();
        scene = new Scene(rootNode);

    }

    @Override
    public void act(long now) {

    }

    public Scene getMenuScene() {
        return scene;
    }

    public static Menu getOpeningScreen() {
        openingScreen.setMinSize(700, 500);
        openingScreen.rootNode.setCenter(openingScreen);

        BorderPane pane = openingScreen.rootNode;
        VBox box = new VBox();
        Label intro = new Label("Welcome to PaddleBall.");
        intro.setFont(new Font("", 40));
        intro.setVisible(true);
        box.getChildren().addAll(intro);

        pane.setCenter(box);
        openingScreen.start();

        return openingScreen;
    }

}
