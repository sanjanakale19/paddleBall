import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class Level {
    private String name;
    private BorderPane rootNode;
    private Scene scene;
    private Scene prevScene;
    private World world;
    private static final double width = 700;
    private static final double height = 500;

    public Level(String name, Scene selection) {
        this.name = name;
        rootNode = new BorderPane();
        scene = new Scene(rootNode);
        prevScene = selection;
        world = new BallWorld();
        world.setBackground(new Background(new BackgroundFill(Color.rgb(120, 191, 255), null, null)));
        world.setPrefSize(width, height);
        rootNode.setCenter(world);
    }

    public World getWorld() {
        return world;
    }

    public Scene getPrevScene() {
        return prevScene;
    }

    public void initializeLevel(Stage stage) {



        Ball ball = new Ball(3, 2);
        ball.setX(175);
        ball.setY(350);

        world.getChildren().add(ball);

        Paddle paddle = new Paddle();
        paddle.setX(225);
        paddle.setY(400);
        world.getChildren().add(paddle);


        Button btn = Menu.getBackBtn(stage, prevScene);
//
//        btn.setVisible(true);
//        world.getChildren().add(btn);

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

    public void endlessMode(Stage stage) {
        Level.setCloudTransition(world);

        Stop[] stops = {new Stop(0, Color.TRANSPARENT), new Stop(1, Color.rgb(120, 191, 255))};
        LinearGradient grad = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);

        Pane pane = new Pane();
        pane.setMinSize(width, height);
        pane.setBackground(new Background(new BackgroundFill(grad, null, null)));

        world.getChildren().add(pane);

        initializeLevel(stage);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (l * 10e9 % 100 == 0) {
                    generateBrickRow();
                }
            }
        };
        timer.start();



    }

    void generateBrickRow() {
        MovingBrick brick = new MovingBrick();

        double randX = (Math.random() * (width - brick.getWidth()));

        brick.setX(randX); brick.setY(-brick.getHeight());


        world.getChildren().add(brick);
    }

    public static void setCloudTransition(World world) {
        // two of the same object are needed so that they can "switch off" while traveling on/off the screen
        final Actor cloudScreen1;
        Actor cloudScreen2;


        cloudScreen1 = new Actor() {
            @Override
            public void act(long now) {
                if (getY() >= 0 && getY() < height || getY() < 0) {
                    move(0, 2);
                } else if (getY() >= height) {
                    setY(-height);
                }
            }
        };
        cloudScreen1.setImage(new Image("resources/clouds.png"));
        cloudScreen1.setFitWidth(width); cloudScreen1.setFitHeight(height);
        cloudScreen1.setX(0); cloudScreen1.setY(0);

        cloudScreen2 = new Actor() {
            @Override
            public void act(long now) {
                if (getY() >= 0 && getY() < height || getY() < 0) {
                    move(0, 2);
                }
                else if (getY() >= height) {
                    setY(-height);
                }
            }
        };
        cloudScreen2.setImage(new Image("resources/clouds.png"));
        cloudScreen2.setFitWidth(width); cloudScreen2.setFitHeight(height);
        cloudScreen2.setX(0); cloudScreen2.setY(-height);

        world.getChildren().addAll(cloudScreen1, cloudScreen2);

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
