package sk.ukf.zadanie3_du;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.Random;

public class Snowflake extends WeatherObject {
    private static final Random RANDOM = new Random();

    private final double speed = 1 + RANDOM.nextDouble();
    private double phase = RANDOM.nextDouble() * Math.PI * 2;

    public Snowflake(double x, double y) {
        super(x, y, buildGraphic());
    }

    private static Node buildGraphic() {
        Group g = new Group();
        for (int i = 0; i < 3; i++) {
            double a = Math.toRadians(i * 60);
            double dx = Math.cos(a) * 7;
            double dy = Math.sin(a) * 7;
            Line l = new Line(-dx, -dy, dx, dy);
            l.setStroke(Color.WHITE);
            l.setStrokeWidth(1.5);
            g.getChildren().add(l);
        }
        return g;
    }

    @Override
    protected void move(double areaWidth, double areaHeight) {
        y += speed;
        phase += 0.05;
        x += Math.sin(phase) * 0.6;
    }
}
