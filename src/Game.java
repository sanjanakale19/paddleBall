import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Game extends Application{
	private Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage myStage) {
		this.stage = myStage;

		Menu menu = Menu.getOpeningScreen(myStage);
		stage.setScene(menu.getMenuScene());

		stage.show();
	}

}
