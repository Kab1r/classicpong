package me.kwatra.classicpong;


import com.sun.javaws.exceptions.InvalidArgumentException;

import static me.kwatra.classicpong.GameEnvironment.Action;

public interface Agent {
    Action getAction(GameEnvironment env, int paddleNumber) throws InvalidArgumentException;
}
