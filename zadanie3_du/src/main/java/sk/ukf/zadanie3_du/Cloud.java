package sk.ukf.zadanie3_du;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class Cloud extends WeatherObject {
    private static final double SPEED = 0.4;

    public Cloud(double x, double y) {
        super(x, y, buildGraphic());
    }

    private static Node buildGraphic() {
        Group g = new Group(
                new Ellipse(55, 40, 48, 16),
                new Circle(28, 30, 22),
                new Circle(58, 22, 28),
                new Circle(86, 32, 20));
        for (Node n : g.getChildren()) {
            ((Shape) n).setFill(Color.LIGHTGRAY);
        }
        return g;
    }

    @Override
    protected void move(double areaWidth, double areaHeight) {
        x += SPEED;
        if (x > areaWidth) {
            x = -getGraphic().getBoundsInLocal().getWidth();
        }
    }

    @Override
    public boolean isExpired(double areaWidth, double areaHeight) {
        return false;
    }

    public void setColor(Color color) {
        Group g = (Group) getGraphic();
        for (Node n : g.getChildren()) {
            ((Shape) n).setFill(color);
        }
    }
}
