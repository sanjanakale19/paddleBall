import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Ball extends Actor{
	
	private int dx;
	private int dy;
	private boolean hitPaddle = false;
	
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
		if (!hitPaddle && getOneIntersectingObject(Paddle.class) != null) {
			Paddle paddle = getOneIntersectingObject(Paddle.class);
			
			if (getWorld().isKeyDown(KeyCode.LEFT) || getWorld().isKeyDown(KeyCode.RIGHT)) {
				if (getX() > paddle.getX() - paddle.getWidth()/6 && getX() < paddle.getX() + paddle.getWidth()/6) {
					setY(paddle.getY() - paddle.getHeight()/2 - getHeight()/2);
					dy = -dy;
				} else if (getX() < paddle.getX() - paddle.getWidth()/2) {
					setX(paddle.getX() - paddle.getWidth()/2 - getWidth()/2);
					dy = -dy;
					dx = -Math.abs(dx) - 1;
				} else if (getX() > paddle.getX() + paddle.getWidth()/2) {
					setX(paddle.getX() + paddle.getWidth()/2 + getWidth()/2);
					dy = -dy;
					dx = Math.abs(dx) + 1;
				}  else if (getWorld().isKeyDown(KeyCode.LEFT) && getX() > paddle.getX() - paddle.getWidth()/2 && getX() < paddle.getX() - paddle.getWidth()/6) {
					setY(paddle.getY() - paddle.getHeight()/2 - getHeight()/2);
					dy = -dy;
					dx = -Math.abs(dx);
				} else if (getWorld().isKeyDown(KeyCode.RIGHT) && getX() > paddle.getX() + paddle.getWidth()/6 && getX() < paddle.getX() + paddle.getWidth()/2) {
					setY(paddle.getY() - paddle.getHeight()/2 - getHeight()/2);
					dy = -dy;
					dx = Math.abs(dx);
				}
			} else if (getX() > paddle.getX() - paddle.getWidth()/2 && getX() < paddle.getX() + paddle.getWidth()/2) {
				setY(paddle.getY() - paddle.getHeight()/2 - getHeight()/2);
				dy = -dy;
			} else if (getX() < paddle.getX() - paddle.getWidth()/2) {
				setX(paddle.getX() - paddle.getWidth()/2 - getWidth()/2);
				dy = -dy;
				dx = -Math.abs(dx) - 1;
			} else if (getX() > paddle.getX() + paddle.getWidth()/2) {
				setX(paddle.getX() + paddle.getWidth()/2 + getWidth()/2);
				dy = -dy;
				dx = Math.abs(dx) + 1;
			} 
			System.out.println(dx + ", " + dy);
			move(dx, dy);
			hitPaddle = true;
		} else {
			hitPaddle = false;
		}
		
		if (getOneIntersectingObject(Brick.class) != null) {
			Brick brick = getOneIntersectingObject(Brick.class);
			double brickX = brick.getX();
			double brickY = brick.getY();
			double brickWidth = brick.getWidth();
			double brickHeight = brick.getHeight();
			
			if (getX() >= brickX && getX() <= (brickX + brickWidth)) {
				dy = -dy;
			}else if (getY() >= brickY && getY() <= (brickY + brickHeight)) {
				dx = -dx;
			}else {
				dx = -dx;
				dy = -dy;
			}
			getWorld().remove(getOneIntersectingObject(Brick.class));

			Score current = ((BallWorld)getWorld()).getScore();
			current.setValue(current.getValue() + 100);
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
