package me.kwatra.classicpong;

public class ImpossibleEnvironment extends GameEnvironment {

    public ImpossibleEnvironment(MainApp superApp) {
        super(superApp);
    }

    @Override
    public int step(Action player0Action, Action player1Action) {
        if (getBallPos().getValue() < getPlayer1Pos().getValue() + getPaddle1().getPaddleHeight() / 2)
            player1Action = Action.UP;
        else if (getBallPos().getValue() > getPlayer1Pos().getValue() + getPaddle1().getPaddleHeight() / 2)
            player1Action = Action.DOWN;
        else player1Action = Action.NOTHING;
        return super.step(player0Action, player1Action);
    }
}
