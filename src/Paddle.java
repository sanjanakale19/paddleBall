import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Paddle extends Actor{

	public Paddle() {
		String path = getClass().getClassLoader().getResource("resources/paddle.png").toString();
		Image img = new Image(path);
		setImage(img);
	}
	
	@Override
	public void act(long now) {
		if (getWorld().isKeyDown(KeyCode.RIGHT) && getX() < getWorld().getWidth() - (getWidth() + 5)) {
			move(5, 0);
		}
		if (getWorld().isKeyDown(KeyCode.LEFT) && getX() - 5 > 0) {
			move(-5, 0);
		}
	}
	
}
