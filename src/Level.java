import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Level {
    private String name;
    private BorderPane rootNode;
    private Scene scene;
    private World world;
    private static final double width = 700;
    private static final double height = 500;

    public Level(String name, Scene selection) {
        this.name = name;
        rootNode = new BorderPane();
        scene = new Scene(rootNode);
        initializeLevel(selection);
    }

    private void initializeLevel(Scene prevScene) {

        world = new BallWorld();
        world.setPrefSize(width, height);
        rootNode.setCenter(world);

        Ball ball = new Ball(3, 2);
        ball.setX(175);
        ball.setY(350);
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

    public void endlessMode() {

    }

    void generateBrickRow() {

    }

    public static void setCloudTransition(Menu world) {
//        int num = 5;
//        ImageView[] leftClouds = new ImageView[num];
//        ImageView[] rightClouds = new ImageView[num];
//
//        Pane cloudPane = new BorderPane();
//        cloudPane.setMinSize(width, height);
//
//
//        int i = 0;
//        while (i < num) {
//            leftClouds[i] = new ImageView("resources/leftCloud.png");
//            rightClouds[i] = new ImageView("resources/rightCloud.png");
//
//            leftClouds[i].setFitWidth(60); leftClouds[i].setFitHeight(33);
//            rightClouds[i].setFitWidth(60); rightClouds[i].setFitHeight(33);
//
//            double width = leftClouds[i].getFitWidth();
//
//            leftClouds[i].setX(width * i * 2.5 + (width * 1.25)); rightClouds[i].setX(width * i * 2.5);
//            leftClouds[i].setY(140); rightClouds[i].setY(80);
//
//            cloudPane.getChildren().addAll(leftClouds[i]);
//            cloudPane.getChildren().addAll(rightClouds[i]);
//
//            i++;
//        }

        // two of the same object are needed so that they can "switch off" while traveling on/off the screen
        final Actor cloudScreen1;
        Actor cloudScreen2;


        cloudScreen1 = new Actor() {
            @Override
            public void act(long now) {
                System.out.println("executing...");
                // not working
                if (getY() > 0 && getY() < height) {
                    move(0, 2);
                } else if (getY() == height) {
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
                System.out.println("executing...");
                // not working
                if (cloudScreen1.getY() > 0 && cloudScreen1.getY() < height) {
                    move(0, 2);
                } else if (getY() == height) {
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
