package pacman.id3;

import pacman.controllers.*;
import pacman.game.Game;
import pacman.game.Constants.MOVE;

/**
 * The DataCollectorHumanController class is used to collect training data from playing PacMan.
 * Data about game state and what MOVE chosen is saved every time getMove is called.
 * @author andershh
 *
 */
public class ID3DataCollectorController extends HumanController{

    private boolean training;

    public ID3DataCollectorController(KeyBoardInput input, boolean training){
        super(input);
        this.training = training;
    }

    @Override
    public MOVE getMove(Game game, long dueTime) {
        MOVE move = super.getMove(game, dueTime);

        ID3DataTuple data = new ID3DataTuple(game, move);

        if (training) ID3DataSaverLoader.SavePacManTrainingData(data);
        else ID3DataSaverLoader.SavePacManTestData(data);

        return move;
    }

}
