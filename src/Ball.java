import javafx.scene.image.Image;

public class Ball extends Actor{
	
	private int dx;
	private int dy;
	
	public Ball() {
		String path = getClass().getClassLoader().getResource("resources/ball.png").toString();
		Image img = new Image(path);
		setImage(img);
		dx = 1;
		dy = 1;

	}
	
	@Override
	public void act(long now) {
		move(dx, dy);
		if (getOneIntersectingObject(Paddle.class) != null) {
			dy = -dy;
		}
		
		if (getOneIntersectingObject(Brick.class) != null) {
			double brickX = getOneIntersectingObject(Brick.class).getX();
			double brickY = getOneIntersectingObject(Brick.class).getY();
			double brickWidth = getOneIntersectingObject(Brick.class).getWidth();
			double brickHeight = getOneIntersectingObject(Brick.class).getHeight();
			
			if (getX() >= brickX && getX() <= (brickX + brickWidth)) {
				dy = -dy;
			}else if (getY() >= brickY && getY() <= (brickY + brickHeight)) {
				dx = -dx;
			}else {
				dx = -dx;
				dy = -dy;
			}
			getWorld().remove(getOneIntersectingObject(Brick.class));
		}
		
		if (getX() < 0) {
			dx = -dx;
			setX(0);
		}else if (getX() + (getWidth()) > getWorld().getWidth()) {
			dx = -dx;
			setX(getWorld().getWidth() - (getWidth()));
		}else if (getY() < 0) {
			dy = -dy;
			setY(0);
		}else if (getY() + (getWidth()) > getWorld().getHeight()) {
			dy = -dy;
			setY(getWorld().getHeight() - (getHeight()));

			Score current = ((BallWorld)getWorld()).getScore();
			current.setValue(current.getValue() - 1000);
		}		
		
	}
	
	
	
}
