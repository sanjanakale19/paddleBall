
public class BallWorld extends World{

	private Score score;

	public BallWorld() {
		super();
		score = new Score();
		score.setX(350); score.setY(120);

		getChildren().add(score);
	}

	public Score getScore() {return score;}


	@Override
	public void act(long now) {
		
		
	}
	
	
}
