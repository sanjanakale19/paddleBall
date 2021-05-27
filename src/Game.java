import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application{
	private Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage myStage) {
		this.stage = myStage;

		BorderPane rootNode = new BorderPane();
		Scene scene = new Scene(rootNode);



		Menu menu = Menu.getOpeningScreen(myStage);
//		Level.setCloudTransition(menu);

		stage.setScene(menu.getMenuScene());

		stage.show();
	}

}
