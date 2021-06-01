public class MovingBrick extends Brick {
    private int dx;
    private int dy;

    MovingBrick(int dx, int dy) {
        super();
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void act(long now) {
        if (getY() >= 400 - getHeight()) {
            getWorld().remove(this);
        }
        move(dx, dy);

    }

}
