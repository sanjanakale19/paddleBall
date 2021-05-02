import javafx.application.Application;
import javafx.scene.Scene;
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
		ballWorld.start();
		
		stage.setScene(scene);
		
		stage.show();
	}

}
