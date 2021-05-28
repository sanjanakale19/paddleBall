public class MovingBrick extends Brick {

    MovingBrick() {
        super();
    }

    @Override
    public void act(long now) {
        if (getY() >= 400 - getHeight()) {
            getWorld().remove(this);
        }
        move(0, 2);

    }

}
