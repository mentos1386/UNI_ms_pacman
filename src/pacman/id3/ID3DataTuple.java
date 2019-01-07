package pacman.id3;

import dataRecording.DataTuple;
import pacman.game.Constants;
import pacman.game.Game;

public class ID3DataTuple extends DataTuple {

    public ID3DataTuple(Game game, Constants.MOVE move) {
        super(game, move);
    }

    public ID3DataTuple(String data) {
        super(data);
    }
}
