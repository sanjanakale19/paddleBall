import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Menu extends World {
    private Scene scene;
    private Scene prevScene;
    private BorderPane rootNode;
    private Color color;
    private Color textColor;
    private static final BorderPane pausePane = new BorderPane();

    public Menu() {
        rootNode = new BorderPane();
        scene = new Scene(rootNode);
        rootNode.setCenter(this);
        prevScene = null;
        color = Color.WHITE;
        textColor = Color.ROYALBLUE;
    }

    @Override
    public void act(long now) {

    }

    public Scene getMenuScene() {
        return scene;
    }

    public static Menu getOpeningScreen(Stage stage) {
        Menu screen = new Menu();
        Level.setCloudTransition(screen);

        Stop[] stops = {new Stop(0, Color.TRANSPARENT), new Stop(1, Color.rgb(120, 191, 255))};
        LinearGradient grad = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);

        Pane pane2 = new Pane();
        pane2.setMinSize(700, 500);
        pane2.setBackground(new Background(new BackgroundFill(grad, null, null)));

        screen.getChildren().add(pane2);

        screen.start();
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
        intro.setTextFill(screen.textColor);
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
        start.setTextFill(screen.textColor);
        start.setPadding(new Insets(10, 10, 10, 10));

        box.getChildren().addAll(intro, start);

        screen.getChildren().addAll(box);

        screen.start();

        return screen;
    }

    public static Menu getSelectionScreen(Stage stage, Menu prevMenu) {
        Menu screen = new Menu();
        Level.setCloudTransition(screen);

        Stop[] stops = {new Stop(0, Color.TRANSPARENT), new Stop(1, Color.rgb(120, 191, 255))};
        LinearGradient grad = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);

        Pane pane2 = new Pane();
        pane2.setMinSize(700, 500);
        pane2.setBackground(new Background(new BackgroundFill(grad, null, null)));

        screen.getChildren().add(pane2);

        screen.start();

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
        label.setTextFill(screen.textColor);
        label.setAlignment(Pos.TOP_CENTER);

        HBox buttonPanel = new HBox();
        buttonPanel.setSpacing(30);
        buttonPanel.setAlignment(Pos.CENTER);


        Button classic = new Button("Classic Mode");
        classic.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(4), new Insets(8))));
        classic.setTextFill(screen.textColor);
        classic.setPadding(new Insets(10, 10, 10, 10));

        setButtonBrightness(classic, screen.color);

        classic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Level level = new Level("Simple Game", screen.getMenuScene());
                BorderPane pane = new BorderPane();

                pane.setRight(getPauseBtn(level, screen.color));
                pane.setMinSize(700, 500);
                pane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));

                level.getWorld().getChildren().add(pane);
                level.initializeLevel(stage);
                Button btn = getBackBtn(stage, screen.getMenuScene());
                setButtonBrightness(btn, screen.color);
                level.getWorld().getChildren().add(btn);
                stage.setScene(level.getScene(stage));
                stage.show();
            }
        });

        Button endless = new Button("Endless Mode");
        endless.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(4), new Insets(8))));
        endless.setTextFill(screen.textColor);
        endless.setPadding(new Insets(10, 10, 10, 10));

        setButtonBrightness(endless, screen.color);

        endless.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Level level = new Level("Simple Game", screen.getMenuScene());
                level.endlessMode(stage);

                Button btn = getBackBtn(stage, screen.getMenuScene());
                setButtonBrightness(btn, screen.color);
                level.getWorld().getChildren().add(btn);
                stage.setScene(level.getScene(stage));
                stage.show();
            }
        });

        buttonPanel.getChildren().addAll(classic, endless);

        box.getChildren().addAll(label, buttonPanel);
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

    public static Button getBackBtn(Stage stage, Scene prevScene) {
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

    public static Button getPauseBtn(Level level, Color background) {
        Button btn = new Button();
        ImageView pauseGraphic = new ImageView("resources/pause.png");
        pauseGraphic.setFitWidth(25); pauseGraphic.setFitHeight(25);
        btn.setGraphic(pauseGraphic);
//        Rotate rot = new Rotate(90, Rotate.Z_AXIS);
//        btn.getTransforms().add(rot);
        btn.setMinSize(14.14213562,  14.14213562);

        btn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(20), null)));
        btn.setAlignment(Pos.TOP_RIGHT);
        btn.setPadding(new Insets(5, 10, 5, 10));
        btn.setTextFill(Color.FLORALWHITE);
        btn.setTooltip(new Tooltip("PAUSE"));
        btn.getTooltip().setFont(new Font("", 10));
        btn.setFont(new Font("", 20));

        setButtonBrightness(btn, background);

        pausePane.setPrefSize(level.getWidth(), level.getHeight());
        Color c = background.desaturate();
        pausePane.setBackground(new Background(new BackgroundFill(Color.rgb((int)c.getRed(), (int)c.getGreen(), (int)c.getBlue(), 0.5), null, null)));

        Label pause = new Label("~ PAUSED ~");
        pause.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        pause.setFont(new Font("", 50));
        pause.setTextFill(Color.FLORALWHITE);


        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                level.getWorld().stop();
                level.getWorld().getChildren().remove(btn);

                Button playBtn = getPlayBtn(level, btn, pausePane, background);

                pausePane.setRight(playBtn);
                pausePane.setCenter(pause);

                level.getWorld().getChildren().add(pausePane);
            }
        });

        return btn;
    }

    public static Button getPlayBtn(Level level, Button pause, BorderPane pausePane, Color background) {
        Button btn = new Button();
        ImageView play = new ImageView("resources/play.png");
        play.setFitHeight(25); play.setFitWidth(25);
        btn.setGraphic(play);

        btn.setMaxSize(20, 20);
//        Rotate rot = new Rotate(90, Rotate.Z_AXIS);
//        btn.getTransforms().add(rot);

        btn.setPrefWidth(14.14213562); btn.setPrefHeight(14.14213562);
        btn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(20), null)));
        btn.setAlignment(Pos.TOP_RIGHT);

        setButtonBrightness(btn, background);

        btn.setPadding(new Insets(5, 10, 5, 10));
        btn.setTextFill(Color.FLORALWHITE);
        btn.setTooltip(new Tooltip("RESUME"));
        btn.getTooltip().setFont(new Font("", 10));
        btn.setFont(new Font("", 20));



        Label resume = new Label("RESUME IN");
        resume.setFont(new Font("", 20));
        resume.setTextFill(Color.FLORALWHITE);
        resume.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));

        Label count = new Label("");
        count.setTextFill(Color.FLORALWHITE);
        count.setFont(new Font("", 50));
        count.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        count.setText("HELLO");


        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VBox box = new VBox();
                box.setAlignment(Pos.CENTER);
                box.setSpacing(25);

                box.getChildren().addAll(resume, count);
                pausePane.setCenter(box);


                // to be run on background thread
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        int i = 3;
                        try {
                            while (i >= 0) {
                                int finalI = i;
                                // the following to be run on main (UI) thread
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        count.setText(finalI + "");
                                        if (finalI == 0) {
                                            level.getWorld().getChildren().remove(pausePane);
                                            level.getWorld().start();
                                            level.getWorld().getChildren().add(pause);
                                            level.getWorld().requestFocus();
                                        }
                                    }
                                });
                                Thread.sleep(1000);
                                i--;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                Thread thread = new Thread(r);
                thread.start();
            }
        });


        return btn;

    }
}
