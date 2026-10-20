package sk.ukf.zadanie3_du;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WeatherController {
    @FXML private Pane canvas;
    @FXML private Button stopRainButton;

    private final List<WeatherObject> objects = new ArrayList<>();
    private final Random random = new Random();
    private boolean rainStopped = false;

    @FXML
    private void initialize() {
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(canvas.widthProperty());
        clip.heightProperty().bind(canvas.heightProperty());
        canvas.setClip(clip);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                tick();
            }
        }.start();
    }

    private void tick() {
        double w = canvas.getWidth();
        double h = canvas.getHeight();

        Iterator<WeatherObject> it = objects.iterator();
        while (it.hasNext()) {
            WeatherObject o = it.next();
            o.update(w, h);
            if (o.isExpired(w, h)) {
                canvas.getChildren().remove(o.getGraphic());
                it.remove();
            }
        }
    }

    private void add(WeatherObject o) {
        objects.add(o);
        canvas.getChildren().add(o.getGraphic());
    }

    private double randomX() {
        return random.nextDouble() * canvas.getWidth();
    }

    private double randomY() {
        return random.nextDouble() * canvas.getHeight();
    }

    @FXML
    private void onAddCloud() {
        add(new Cloud(randomX(), 5 + random.nextDouble() * 70));
    }

    @FXML
    private void onAddRaindrop() {
        Raindrop drop = new Raindrop(randomX(), randomY());
        drop.setMoving(!rainStopped);
        add(drop);
    }

    @FXML
    private void onAddSnowflake() {
        add(new Snowflake(randomX(), randomY()));
    }

    @FXML
    private void onToggleRain() {
        rainStopped = !rainStopped;
        for (WeatherObject o : objects) {
            if (o instanceof Raindrop) {
                o.setMoving(!rainStopped);
            }
        }
        stopRainButton.setText(rainStopped ? "Spustiť dážď" : "Zastaviť dážď");
    }

    @FXML
    private void onCloudsBlue() {
        for (WeatherObject o : objects) {
            if (o instanceof Cloud cloud) {
                cloud.setColor(Color.DODGERBLUE);
            }
        }
    }

    @FXML
    private void onShiftLeft() {
        for (WeatherObject o : objects) {
            o.moveBy(-20, 0);
        }
    }
}
