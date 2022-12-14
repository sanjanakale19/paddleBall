import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.Console;
import java.util.Timer;

public class Level {
    private String name;
    private BorderPane rootNode;
    private Scene scene;
    private Scene prevScene;
    private final BallWorld world;
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

    public BallWorld getWorld() {
        return world;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
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

        BorderPane pane = new BorderPane();
        pane.setMinSize(width, height);

        Button pause = Menu.getPauseBtn(stage, this, Color.rgb(120, 191, 255), false);
        pane.setRight(pause);

        world.getChildren().add(pane);

        initializeLevel(stage);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!world.isStopped()) {
                    if (l * 10e9 % 100 == 0) {
                        generateBrickRow();

                    }
                }
            }
        };
        timer.start();



    }

    void generateBrickRow() {
        MovingBrick brick = new MovingBrick(0, 2);

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


        Stop[] stops = {new Stop(0, Color.TRANSPARENT), new Stop(1, Color.rgb(120, 191, 255))};
        LinearGradient grad = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);

        Pane pane2 = new Pane();
        pane2.setMinSize(700, 500);
        pane2.setBackground(new Background(new BackgroundFill(grad, null, null)));

        world.getChildren().add(pane2);

        world.start();
    }

    public static Level[] getClassicLevels(Stage stage) {
        Level[] levels = new Level[5];

        for (int i = 0; i < levels.length; i++) {

            levels[i] = new Level("Level " + (i+1), null);
            BorderPane pane = new BorderPane();
            Level lvl = levels[i];

            pane.setRight(Menu.getBtnPanel3(stage, lvl, true));
            pane.setMinSize(700, 500);
            pane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));

            lvl.getWorld().getChildren().add(pane);
            lvl.initializeLevel(stage);

            HBox box = Menu.getBtnPanel2(stage, true);
            lvl.getWorld().getChildren().add(box);

//            Brick b = new MovingBrick(0, 0);
//            b.setX(200); b.setY(100);
//
//            lvl.getWorld().add(b);

        }

        // level 1
        Level lvl = levels[0];
        for (int i = 100; i < 600;) {
            Brick brick = new MovingBrick(0, 0);
            brick.setX(i); brick.setY(180);
            lvl.getWorld().add(brick);

            Brick brick2 = new MovingBrick(0, 0);
            brick2.setX(60); brick2.setY(i - 50);
            lvl.getWorld().add(brick2);

            i += brick.getWidth() + 20;
        }

        // level 2
        lvl = levels[1];
        for (int i = 0; i < 7; i++) {
            for (int j = 7; j > i; j--) {
                Brick brick = new MovingBrick(0, 0);
                brick.setX(i * 80 + 30); brick.setY(j * 30 + 40);
                lvl.getWorld().add(brick);
            }
        }

        // level 3
        lvl = levels[2];
        for (int rows = 1, incr = 1, i = 0; i < 9; i++) {
            for (int j = 0; j < rows; j++) {
                Brick brick = new MovingBrick(0, 0);
                brick.setX(i * 75 + 25); brick.setY(j * 45 + 40);
                lvl.getWorld().add(brick);
            }
            rows += incr;
            if (rows == 5) {
                incr = -1;
            }
        }

        // level 4
        lvl = levels[3];
        for (int rows = 8, incr = -2, i = 0; i < 9; i++) {
            for (int j = rows; j > 0; j--) {
                Brick brick = new MovingBrick(0, 0);
                brick.setX(i * 75 + 30); brick.setY(j * 30 + 40);
                lvl.getWorld().add(brick);
            }
            rows += incr;
            if (rows == 0) {
                incr = 2;
            }
        }

        // level 5
        lvl = levels[4];
        for (int k = 0; k < 2; k++) {
            for (int rows = 1, incr = 2, i = 0; i < 5; i++) {
                for (int j = 0; j < rows; j++) {
                    Brick brick = new MovingBrick(0, 0);
                    brick.setX(i * 65 + 25 + k*350);
                    brick.setY(j * 45 + 40);
                    lvl.getWorld().add(brick);
                }
                rows += incr;
                if (rows == 5) {
                    incr = -2;
                }
            }
        }

        return levels;
    }

    public static Level[] getEndlessLevels(Stage stage) {
        Level[] levels = new Level[2];

        for (int i = 0; i < levels.length; i++) {

            levels[i] = new Level(i == 0 ? "Timed Mode" : "Peaceful Mode", null);
            Level lvl = levels[i];

            BorderPane pane = new BorderPane();
            lvl.endlessMode(stage);

            pane.setRight(Menu.getBtnPanel3(stage, lvl, false));
            pane.setMinSize(700, 500);
            pane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));

            lvl.getWorld().getChildren().add(pane);


            HBox box = Menu.getBtnPanel2(stage, false);

            lvl.getWorld().getChildren().add(box);

        }

        return levels;
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
