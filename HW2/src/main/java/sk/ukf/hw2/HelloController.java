package sk.ukf.hw2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class HelloController {

    @FXML
    private Pane root;

    private static final double OFFSET = 50;
    private static final double FIELD = 400;
    private static final double BODY_SIZE = 40;
    private static final double STEP = 10;
    private static final double EYE_SIZE = 10;

    private static final KeyCode[] DIRECTION_KEYS = {
            KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D
    };

    private boolean autoMode = false;
    private final Random rng = new Random();

    private Group robotNode;
    private Rectangle body;
    private Rectangle leftEye;
    private Rectangle rightEye;

    @FXML
    protected void initialize() {
        createField();
        createRobot();
        setupKeyboard();
        setupAutoMovement();
    }

    private void createField() {
        Rectangle field = new Rectangle(OFFSET, OFFSET, FIELD, FIELD);
        field.setFill(null);
        field.setStroke(Color.BLACK);
        field.setStrokeWidth(3);
        root.getChildren().add(field);
    }

    private void createRobot() {
        double startX = OFFSET + 180;
        double startY = OFFSET + 180;

        body = new Rectangle(startX, startY, BODY_SIZE, BODY_SIZE);
        body.setFill(Color.GRAY);

        leftEye = new Rectangle(startX + 5, startY, EYE_SIZE, EYE_SIZE);
        leftEye.setFill(Color.RED);

        rightEye = new Rectangle(startX + 25, startY, EYE_SIZE, EYE_SIZE);
        rightEye.setFill(Color.RED);

        robotNode = new Group(body, leftEye, rightEye);
        root.getChildren().add(robotNode);
    }

    private void setupKeyboard() {
        root.setFocusTraversable(true);
        root.setOnKeyPressed(e -> handleKey(e.getCode()));
    }

    private void setupAutoMovement() {
        Timeline timer = new Timeline(
                new KeyFrame(Duration.seconds(0.5), e -> {
                    if (autoMode) {
                        KeyCode key = DIRECTION_KEYS[rng.nextInt(DIRECTION_KEYS.length)];
                        handleKey(key);
                    }
                })
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void handleKey(KeyCode key) {
        switch (key) {
            case W -> {
                robotNode.setRotate(0);
                tryMove(0, -STEP);
            }
            case A -> {
                robotNode.setRotate(-90);
                tryMove(-STEP, 0);
            }
            case S -> {
                robotNode.setRotate(180);
                tryMove(0, STEP);
            }
            case D -> {
                robotNode.setRotate(90);
                tryMove(STEP, 0);
            }
            default -> { }
        }
    }

    private void tryMove(double dx, double dy) {
        double newX = body.getX() + dx;
        double newY = body.getY() + dy;

        boolean insideX = newX >= OFFSET && newX + BODY_SIZE <= OFFSET + FIELD;
        boolean insideY = newY >= OFFSET && newY + BODY_SIZE <= OFFSET + FIELD;

        if (!insideX || !insideY) {
            return;
        }

        for (Rectangle part : new Rectangle[]{body, leftEye, rightEye}) {
            part.setX(part.getX() + dx);
            part.setY(part.getY() + dy);
        }
    }

    @FXML
    private void handleButton() {
        autoMode = !autoMode;
    }
}