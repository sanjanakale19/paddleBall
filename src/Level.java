import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Level {
    private String name;
    private BorderPane rootNode;
    private Scene scene;
    private World world;

    public Level(String name, Scene selection) {
        this.name = name;
        rootNode = new BorderPane();
        scene = new Scene(rootNode);
        initializeLevel(selection);
    }

    private void initializeLevel(Scene prevScene) {

        world = new BallWorld();
        world.setPrefSize(700, 500);
        rootNode.setCenter(world);

        Ball ball = new Ball(2, 2);
        ball.setX(30);
        world.getChildren().add(ball);

        Paddle paddle = new Paddle();
        paddle.setX(225);
        paddle.setY(400);
        world.getChildren().add(paddle);


        world.start();

        world.setOnKeyPressed(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent event) {
                world.keyDown(event.getCode());
            }

        });

        world.setOnKeyReleased(new EventHandler <KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                world.keyUp(event.getCode());
            }

        });

        world.requestFocus();
    }

    public String getName() {
        return name;
    }

    public BorderPane getRootNode() {
        return rootNode;
    }

    public Scene getScene(Stage stage) {
        stage.setTitle(name);
        return scene;
    }
}
