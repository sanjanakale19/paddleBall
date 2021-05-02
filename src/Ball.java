import javafx.scene.image.Image;

public class Ball extends Actor{
	
	private int dx;
	private int dy;
	
	public Ball() {
		setImage(new Image ("ball.png"));
		dx = 5;
		dy = 5;
		
	}
	
	@Override
	public void act(long now) {
		move(dx, dy);
		if (getX() <= getWidth() / 2) {
			dx = -dx;
			setX(getWidth() / 2);
		}else if (getX() >= getWorld().getWidth() - getWidth() / 2) {
			dx = -dx;
			setX(getWorld().getWidth() - getWidth() / 2);
		}
		if (getY() <= getHeight() / 2) {
			dy = -dy;
			setY(getHeight() / 2);
		}else if (getY() >= getWorld().getHeight() - getHeight() / 2) {
			dy = -dy;
			setY(getWorld().getHeight() - getHeight() / 2);
		}
		
	}
	
	
	
}
