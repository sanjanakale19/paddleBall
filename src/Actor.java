import java.util.ArrayList;

import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView{

	public abstract void act(long now);
	
	public void move(double dx, double dy) {
		setX(dx + getX());
		setY(dy + getY());
	}
	
	public World getWorld() {
		return (World)getParent();
	}
	
	public double getHeight() {
		return getBoundsInParent().getHeight();
	}
	
	public double getWidth() {
		return getBoundsInParent().getWidth();
	}
	
	public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
		ArrayList<A> arr = new ArrayList<>();
		for (A actor : getWorld().getObjects(cls)) {
			if (this != actor && actor.intersects(getX(), getY(), getWidth(), getHeight())) {
				arr.add(actor);
			}
		}	
		return arr;
	}
	
	public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
		for (A actor : getWorld().getObjects(cls)) {
			if (this != actor && actor.intersects(getX(), getY(), getWidth(), getHeight())) {
				return actor;
			}
		}	
		return null;
	}


}
