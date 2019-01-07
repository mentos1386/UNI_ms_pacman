package pacman.id3;

import pacman.controllers.examples.StarterPacMan;
import pacman.game.Constants;
import pacman.game.Game;

public class ID3AutomatedDataCollectorController extends StarterPacMan {

    private boolean training;

    public ID3AutomatedDataCollectorController(boolean training){
        super();
        this.training = training;
    }

    @Override
    public Constants.MOVE getMove(Game game, long dueTime) {
        Constants.MOVE move = super.getMove(game, dueTime);

        ID3DataTuple data = new ID3DataTuple(game, move);

        if (training) ID3DataSaverLoader.SavePacManTrainingData(data);
        else ID3DataSaverLoader.SavePacManTestData(data);

        return move;
    }
}
