package sk.ukf.zadanie3_du;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import java.util.Random;

public class Raindrop extends WeatherObject {
    private static final Random RANDOM = new Random();
    private final double speed = 5 + RANDOM.nextDouble() * 3;

    public Raindrop(double x, double y) {
        super(x, y, buildGraphic());
    }

    private static Node buildGraphic() {
        Polygon tip = new Polygon(0, -9, -4, 0, 4, 0);
        Circle body = new Circle(0, 2, 4);
        Group g = new Group(tip, body);
        tip.setFill(Color.CORNFLOWERBLUE);
        body.setFill(Color.CORNFLOWERBLUE);
        return g;
    }

    @Override
    protected void move(double areaWidth, double areaHeight) {
        y += speed;
    }
}
