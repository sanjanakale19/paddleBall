import java.util.ArrayList;
import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class World extends Pane {

	private AnimationTimer timer;
	private HashSet<KeyCode> keysDown;
	
	public World() {
		keysDown = new HashSet<KeyCode>();
		timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				act(now);
				for (int i = 0; i < getChildren().size(); i++) {
					Node node = getChildren().get(i);
					if (node instanceof Actor) {
						((Actor)node).act(now);
					}
				}
				
			}
		
		};
	}
	
	public void keyDown(KeyCode keyCode) {
		keysDown.add(keyCode);
	}
	
	public void keyUp(KeyCode keyCode) {
		keysDown.remove(keyCode);
	}
	
	public boolean isKeyDown(KeyCode keyCode) {
		return keysDown.contains(keyCode);
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
			if (getChildren().get(i).getClass().isAssignableFrom(cls)) {
				arr.add(cls.cast(getChildren().get(i)));
			}
		}
		
		return arr;
	
	}
	
	public abstract void act(long now);
}