import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class World extends Pane{
	
	private AnimationTimer timer;
	
	public World() {
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				act(now);
				for (int i = 0; i < getObjects(Actor.class).size(); i++) {
					getObjects(Actor.class).get(i).act(now);
				}
				
			}
		
		};
	}
	
	public void add(Actor actor) {
		getChildren().add(actor);
	}
	
	public void remove(Actor actor) {
		getChildren().remove(actor);
	}
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
	
	public <A extends Actor> java.util.List<A> getObjects(java.lang.Class<A> cls){
		ArrayList<A> arr = new ArrayList<>();
		for (int i = 0; i < getChildren().size(); i++) {
			if (getChildren().get(i).getClass().isInstance(cls)) {
				arr.add(cls.cast(getChildren().get(i)));
			}
		}
		
		return arr;
	
	}
	
	public abstract void act(long now);
}