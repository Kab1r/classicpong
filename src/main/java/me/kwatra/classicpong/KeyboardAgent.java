package me.kwatra.classicpong;

import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class KeyboardAgent implements Agent {
    private GameEnvironment.Action currentAction0 = GameEnvironment.Action.NOTHING;
    private GameEnvironment.Action currentAction1 = GameEnvironment.Action.NOTHING;
    private GameEnvironment env;

    public KeyboardAgent(Canvas node, GameEnvironment env) {
        this.env = env;
        System.out.println("CONSTRUCT");
        node.setFocusTraversable(true);
        node.setOnKeyPressed(this::setAction);
        node.setOnKeyReleased(this::resetAction);
    }

    @Override
    public GameEnvironment.Action getAction(GameEnvironment env, int paddleNumber) throws InvalidArgumentException {
        if (paddleNumber == 0)
            return currentAction0;
        else if (paddleNumber == 1)
            return currentAction1;
        else throw new InvalidArgumentException(new String[]{"Paddle number must be in range [0, 1]"});
    }

    private void setAction(KeyEvent event) {
        KeyCode code = event.getCode();
        if (code.equals(KeyCode.SPACE) && !env.isActive)
            env.reset();
        if (code.equals(KeyCode.ESCAPE))
            env.getSuperApp().startMenu();

        if (code.equals(KeyCode.W))
            currentAction0 = GameEnvironment.Action.UP;
        if (code.equals(KeyCode.S))
            currentAction0 = GameEnvironment.Action.DOWN;
        if (code.equals(KeyCode.UP))
            currentAction1 = GameEnvironment.Action.UP;
        if (code.equals(KeyCode.DOWN))
            currentAction1 = GameEnvironment.Action.DOWN;
    }

    private void resetAction(KeyEvent event) {
        KeyCode code = event.getCode();
        if (code.equals(KeyCode.W))
            currentAction0 = GameEnvironment.Action.NOTHING;
        if (code.equals(KeyCode.S))
            currentAction0 = GameEnvironment.Action.NOTHING;
        if (code.equals(KeyCode.UP))
            currentAction1 = GameEnvironment.Action.NOTHING;
        if (code.equals(KeyCode.DOWN))
            currentAction1 = GameEnvironment.Action.NOTHING;
    }
}
