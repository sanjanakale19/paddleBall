import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Game extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Game");
		BorderPane rootNode = new BorderPane(); 
		BallWorld ballWorld = new BallWorld();
		ballWorld.setPrefSize(700, 500);
		rootNode.setCenter(ballWorld);
		
		Scene scene = new Scene(rootNode);
		Ball ball = new Ball();
		ballWorld.getChildren().add(ball);
		
		Paddle paddle = new Paddle();
		paddle.setX(225);
		paddle.setY(400);
		ballWorld.getChildren().add(paddle);
		
		ballWorld.start();
		
//		ballWorld.setOnMouseMoved(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				if (event.getX() > (paddle.getWidth() / 2) && event.getX() < ballWorld.getWidth() - (paddle.getWidth() / 2)) {
//					paddle.setX(event.getX() - (paddle.getWidth() / 2));
//				}
//				
//				
//			}
//			
//		});
		
		ballWorld.setOnKeyPressed(new EventHandler <KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				ballWorld.keyDown(event.getCode());
//				if (event.getCode() == KeyCode.RIGHT && paddle.getX() < ballWorld.getWidth() - (paddle.getWidth() + 5)) {
//					paddle.setX(paddle.getX() + 5);
//				}
//				if (event.getCode() == KeyCode.LEFT && paddle.getX() - 5 > 0) {
//					paddle.setX(paddle.getX() - 5);
//				}
			}
			
		});
		
		ballWorld.setOnKeyReleased(new EventHandler <KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				ballWorld.keyUp(event.getCode());
			}
			
		});
		
		
		
		stage.setScene(scene);
		
		stage.show();
		
		ballWorld.requestFocus();
	}

}
