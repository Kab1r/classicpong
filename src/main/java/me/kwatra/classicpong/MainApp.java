package me.kwatra.classicpong;

import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import static me.kwatra.classicpong.GameEnvironment.*;

public class MainApp extends Application {

    private GameEnvironment env;
    private GraphicsContext gc;
    private Canvas canvas;
    private Stage primaryStage;
    private Agent keyboardAgent;
    private Score score;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Pong!");
        startMenu();

        primaryStage.show();

    }

    public void startMenu() {
        primaryStage.setScene(new Scene(new Menu(this)));
    }

    public void startTwoPlayer() {
        env = new GameEnvironment(this);

        startGame();
    }

    public void startImpossible() {
        env = new ImpossibleEnvironment(this);

        startGame();
    }

    private void startGame() {
        canvas = new Canvas(WIDTH, HEIGHT);
        primaryStage.setScene(new Scene(new StackPane(canvas)));
        primaryStage.setWidth(WIDTH + 16);
        primaryStage.setHeight(HEIGHT + 38);
        gc = canvas.getGraphicsContext2D();

        score = new Score();

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(16), event -> frame()));
        timeline.setCycleCount(Timeline.INDEFINITE);

        keyboardAgent = new KeyboardAgent(canvas, env);
        timeline.play();
    }


    private void frame() {
        if (env.isActive) {
            Action player0Action;
            Action player1Action;
            try {
                player0Action = keyboardAgent.getAction(env, 0);
                player1Action = keyboardAgent.getAction(env, 1);
                int delta = env.step(player0Action, player1Action);
                updateScore(delta);
                refreshCanvas();
            } catch (InvalidArgumentException ignored) {
            }
        } else drawStart();

    }

    private void drawStart() {
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("SPACE TO START", WIDTH / 2, HEIGHT / 2);
    }

    private void updateScore(int delta) {
        if (delta == -1)
            score.incrementScore0();
        else if (delta == 1)
            score.incrementScore1();
    }

    private void refreshCanvas() {
        setBackground();
        gc.setFill(Color.WHITE);
        env.getBall().draw(gc);
        env.getPaddle0().draw(gc);
        env.getPaddle1().draw(gc);

        drawScore();
    }

    private void drawScore() {
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText(score.getScore0() + " : " + score.getScore1(), WIDTH / 2, HEIGHT / 6);
    }

    private void setBackground() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
