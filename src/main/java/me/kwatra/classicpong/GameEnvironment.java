package me.kwatra.classicpong;

import javafx.util.Pair;

public class GameEnvironment {
    public static final double HEIGHT = 512.0;
    public static final double WIDTH = 1024.0;
    public boolean isActive;
    private MainApp superApp;
    private Paddle paddle0;
    private Paddle paddle1;
    private Ball ball;

    public GameEnvironment(MainApp superApp) {
        this.superApp = superApp;
        reset();
    }

    public Paddle getPaddle0() {
        return paddle0;
    }

    public Paddle getPaddle1() {
        return paddle1;
    }

    public Ball getBall() {
        return ball;
    }

    public void reset() {
        Pair<Double, Double> bSpeed = getInitBallVelocity();
        paddle0 = new Paddle(0, HEIGHT / 2);
        paddle1 = new Paddle(WIDTH, HEIGHT / 2);
        ball = new Ball(WIDTH / 2, HEIGHT / 2, bSpeed.getKey(), bSpeed.getValue());
        isActive = true;
    }

    private Pair<Double, Double> getInitBallVelocity() {
        double angle;
        do {
            angle = Math.random() * 2 * Math.PI;
        } while (angle % Math.PI > Math.PI / 4 || angle == 0.0);

        double x = 2 * Math.cos(angle);
        double y = 2 * Math.sin(angle);
        return new Pair<>(x, y);
    }

    public Pair<Double, Double> getPlayer0Pos() {
        return new Pair<>(paddle0.getXPos(), paddle0.getYPos());
    }

    public Pair<Double, Double> getPlayer1Pos() {
        return new Pair<>(paddle1.getXPos(), paddle1.getYPos());
    }

    public Pair<Double, Double> getBallPos() {
        return new Pair<>(ball.getXPos(), ball.getYPos());
    }

    public MainApp getSuperApp() {
        return superApp;
    }


    public int step(Action player0Action, Action player1Action) {
        if (!isActive) {
            System.err.println("Environment is inactive. Reset to reactivate");
            return 0;
        }
        movePaddle(player0Action, paddle0);
        movePaddle(player1Action, paddle1);
        ball.move();
        bounceWalls();
        bouncePaddles();
        ball.incrementSpeed();

        return score();
    }

    private boolean ballIntersectsWithPaddle0() {
        return ball.getXPos() - ball.getRadius() < paddle0.getXPos() && ball.getYPos() > paddle0.getYPos() && ball.getYPos() < paddle0.getYRange();
    }

    private boolean ballIntersectsWithPaddle1() {
        return ball.getXPos() + ball.getRadius() > paddle1.getXPos() - paddle1.getPaddleWidth() && ball.getYPos() > paddle1.getYPos() && ball.getYPos() < paddle1.getYRange();
    }

    private void bouncePaddles() {
        if (ballIntersectsWithPaddle0() || ballIntersectsWithPaddle1()) {
            ball.setXVelocity(-1 * ball.getXVelocity());
            forceMoveBall();
        }
    }

    private int score() {
        if (ball.getXPos() < paddle0.getXPos()) {
            isActive = false;
            return -1;
        } else if (ball.getXPos() + ball.getRadius() > paddle1.getXPos()) {
            isActive = false;
            return 1;
        } else return 0;
    }

    private void bounceWalls() {
        if (ball.getYPos() + ball.getRadius() >= HEIGHT || ball.getYPos() < 0) {
            ball.setYVelocity(-1 * ball.getYVelocity());
            forceMoveBall();
        }
    }

    private void forceMoveBall() {
        for (int i = 0; i < 3; i++)
            ball.move();

    }

    private void movePaddle(Action player0Action, Paddle paddle) {
        switch (player0Action) {
            case UP:
                paddle.move(-1.0);
                break;
            case DOWN:
                paddle.move(1.0);
                break;
        }
        if (paddle.getYPos() < 0) paddle.moveTo(0);
        if (paddle.getYRange() > HEIGHT) paddle.moveTo(HEIGHT - paddle.getPaddleHeight());
    }

    public enum Action {
        NOTHING,
        UP,
        DOWN
    }
}
