package pacman.id3;

import dataRecording.DataTuple;
import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.Ghost;

public class ID3DataTuple extends DataTuple {

    public Constants.MOVE directionToClosestPil;
    public Constants.MOVE directionToClosestPowerPil;
    public Constants.MOVE getMoveAwayFromThreat;

    public ID3DataTuple(Game game, Constants.MOVE move) {
        super(game, move);

        this.directionToClosestPil = game.directionToClosesPill();
        this.directionToClosestPowerPil = game.directionToClosestPowerPill();
        this.getMoveAwayFromThreat = game.getMoveAwayFromThreat(game.closestGhost());
    }

    public ID3DataTuple(String data) {

        super(data);

        String[] dataSplit = data.split(";");
        this.directionToClosestPil = Constants.MOVE.valueOf(dataSplit[25]);
        this.directionToClosestPowerPil = Constants.MOVE.valueOf(dataSplit[26]);
        this.getMoveAwayFromThreat = Constants.MOVE.valueOf(dataSplit[27]);

    }


    public String getSaveString() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(super.getSaveString());

        stringbuilder.append(this.directionToClosestPil).append(";");
        stringbuilder.append(this.directionToClosestPowerPil).append(";");
        stringbuilder.append(this.getMoveAwayFromThreat).append(";");

        return stringbuilder.toString();
    }
}
