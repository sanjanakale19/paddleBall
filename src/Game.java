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
		Level level = new Level("Simple Game");

		Menu menu = Menu.getOpeningScreen();

		stage.setScene(menu.getMenuScene());
		stage.show();
	}

}
