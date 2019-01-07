package dataRecording;

import pacman.controllers.*;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Game;
import pacman.game.Constants.MOVE;

/**
 * The DataCollectorHumanController class is used to collect training data from playing PacMan.
 * Data about game state and what MOVE chosen is saved every time getMove is called.
 * @author andershh
 *
 */
public class DataCollectorController extends HumanController{

	private boolean training;

	public DataCollectorController(KeyBoardInput input, boolean training){
		super(input);
		this.training = training;
	}
	
	@Override
	public MOVE getMove(Game game, long dueTime) {
		MOVE move = super.getMove(game, dueTime);
		
		DataTuple data = new DataTuple(game, move);

		if (training) DataSaverLoader.SavePacManTrainingData(data);
		else DataSaverLoader.SavePacManTestData(data);

		return move;
	}

}
