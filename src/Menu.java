import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Menu extends World {
    private Scene scene;
    private Scene prevScene;
    private BorderPane rootNode;
    private Color color;

    public Menu() {
        rootNode = new BorderPane();
        scene = new Scene(rootNode);
        rootNode.setCenter(this);
        prevScene = null;
        color = Color.WHITE;
    }

    @Override
    public void act(long now) {

    }

    public Scene getMenuScene() {
        return scene;
    }

    public static Menu getOpeningScreen(Stage stage) {
        Menu screen = new Menu();
        screen.color = Color.rgb(120, 191, 255);
        screen.setPrefSize(700, 500);
        screen.setBackground(new Background(new BackgroundFill(screen.color, null, null)));

        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setPrefSize(700, 500);
        box.setSpacing(70);

        Label intro = new Label("Welcome to PaddleBall");
        intro.setAlignment(Pos.CENTER);
        intro.setFont(new Font("", 40));
        intro.setTextFill(Color.FLORALWHITE);
        intro.setVisible(true);

        Button start = new Button("START");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Menu selection = getSelectionScreen(stage, screen);
                stage.setScene(selection.getMenuScene());
                stage.show();
            }
        });
        setButtonBrightness(start, screen.color);

        start.setVisible(true);
        start.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(4), new Insets(8))));
        start.setFont(new Font("", 14));
        start.setTextFill(Color.FLORALWHITE);
        start.setPadding(new Insets(10, 10, 10, 10));

        box.getChildren().addAll(intro, start);

        screen.getChildren().addAll(box);

        screen.start();

        return screen;
    }

    public static Menu getSelectionScreen(Stage stage, Menu prevMenu) {
        Menu screen = new Menu();
        screen.color = Color.rgb(120, 191, 255);
        screen.prevScene = prevMenu.getMenuScene();
        screen.setMaxSize(700, 500);
        screen.setMinSize(700, 500);

        screen.setBackground(new Background(new BackgroundFill(screen.color, null, null)));

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(30));
        pane.setPrefSize(700, 500);

        Button backBtn = getBackBtn(stage, prevMenu.getMenuScene());
        setButtonBrightness(backBtn, screen.color);


        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setPrefSize(200, 200);
        box.setSpacing(70);

        Label label = new Label("Select a game mode");
        label.setVisible(true);
        label.setFont(new Font("", 30));
        label.setTextFill(Color.FLORALWHITE);
        label.setAlignment(Pos.TOP_CENTER);

        Button classic = new Button("Classic Mode");
        classic.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(4), new Insets(8))));
        classic.setTextFill(Color.FLORALWHITE);
        classic.setPadding(new Insets(10, 10, 10, 10));

        setButtonBrightness(classic, screen.color);

        classic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Level level = new Level("Simple Game", screen.getMenuScene());

                stage.setScene(level.getScene(stage));
                stage.show();
            }
        });

        box.getChildren().addAll(label, classic);
        pane.setCenter(box);
        pane.setLeft(backBtn);

        screen.getChildren().addAll(pane);


        return screen;
    }

    static void setButtonBrightness(Button btn, Color background) {
        btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn.setBackground(new Background(new BackgroundFill(background.desaturate(), new CornerRadii(4), new Insets(8))));
            }
        });
        btn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(4), new Insets(8))));
            }
        });
    }

    static Button getBackBtn(Stage stage, Scene prevScene) {
        Button btn = new Button("←");
        btn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(4), new Insets(8))));
        btn.setPadding(new Insets(5, 10, 5, 10));
        btn.setTextFill(Color.FLORALWHITE);
        btn.setTooltip(new Tooltip("BACK"));
        btn.getTooltip().setFont(new Font("", 10));
        btn.setFont(new Font("", 20));


        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent mouseEvent) {
                stage.setScene(prevScene);
                stage.show();
            }
        });

        return btn;
    }

}
