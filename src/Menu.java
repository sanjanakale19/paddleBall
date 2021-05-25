import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu extends World {
    private Scene scene;
    private BorderPane rootNode;

    public Menu() {
        rootNode = new BorderPane();
        scene = new Scene(rootNode);
        rootNode.setCenter(this);
    }

    @Override
    public void act(long now) {

    }

    public Scene getMenuScene() {
        return scene;
    }

    public static Menu getOpeningScreen(Stage stage) {
        Menu screen = new Menu();
        screen.setPrefSize(700, 500);
        screen.setBackground(new Background(new BackgroundFill(Color.rgb(120, 191, 255), null, null)));

        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setPrefSize(700, 500);

        Label intro = new Label("Welcome to PaddleBall");
        intro.setAlignment(Pos.CENTER);
        intro.setFont(new Font("", 40));
        intro.setTextFill(Color.FLORALWHITE);
        intro.setVisible(true);

        Button start = new Button("START");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Menu selection = getSelectionScreen(stage);
                stage.setScene(selection.getMenuScene());
                stage.show();
            }
        });
        start.setVisible(true);
        start.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(4), new Insets(8))));
        start.setFont(new Font("", 14));
        start.setTextFill(Color.FLORALWHITE);
        start.setPadding(new Insets(70, 0, 0, 0));

        box.getChildren().addAll(intro, start);

        screen.getChildren().addAll(box);

        screen.start();

        return screen;
    }

    public static Menu getSelectionScreen(Stage stage) {
        Menu screen = new Menu();
        screen.setPrefSize(700, 500);

        screen.setBackground(new Background(new BackgroundFill(Color.rgb(120, 191, 255), null, null)));

        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setPrefSize(700, 500);

        Label label = new Label("Select a game mode");
        label.setVisible(true);
        label.setFont(new Font("", 30));
        label.setTextFill(Color.FLORALWHITE);
        label.setAlignment(Pos.TOP_CENTER);

        Button classic = new Button("Classic Mode");
        classic.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(4), new Insets(8))));
        classic.setTextFill(Color.FLORALWHITE);
        classic.setPadding(new Insets(70, 0, 0, 0));

        classic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Level level = new Level("Simple Game");
                stage.setScene(level.getScene(stage));
                stage.show();
            }
        });

        box.getChildren().addAll(label, classic);

        screen.getChildren().addAll(box);


        return screen;
    }

}
