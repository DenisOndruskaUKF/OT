package sk.ukf.zadanie3_du;

import javafx.geometry.Bounds;
import javafx.scene.Node;

public abstract class WeatherObject {
    protected double x;
    protected double y;

    private final Node graphic;
    private boolean moving = true;

    protected WeatherObject(double x, double y, Node graphic) {
        this.x = x;
        this.y = y;
        this.graphic = graphic;
        syncGraphic();
    }

    public final void update(double areaWidth, double areaHeight) {
        if (moving) {
            move(areaWidth, areaHeight);
        }
        syncGraphic();
    }

    protected abstract void move(double areaWidth, double areaHeight);

    public void moveBy(double dx, double dy) {
        x += dx;
        y += dy;
        syncGraphic();
    }

    public boolean isExpired(double areaWidth, double areaHeight) {
        Bounds b = graphic.getBoundsInParent();
        return b.getMaxX() < 0 || b.getMinX() > areaWidth
                || b.getMaxY() < 0 || b.getMinY() > areaHeight;
    }

    public Node getGraphic() {
        return graphic;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void syncGraphic() {
        graphic.setLayoutX(x);
        graphic.setLayoutY(y);
    }
}
