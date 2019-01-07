package pacman.id3;

import dataRecording.DataTuple;
import pacman.game.Constants;
import pacman.game.Game;

public class ID3DataTuple extends DataTuple {

    public Constants.MOVE directionToClosestPil;
    public Constants.MOVE directionToClosestPowerPil;

    public ID3DataTuple(Game game, Constants.MOVE move) {
        super(game, move);

        this.directionToClosestPil = game.directionToClosesPill();
        this.directionToClosestPowerPil = game.directionToClosestPowerPill();
    }

    public ID3DataTuple(String data) {
        super(data);

        String[] dataSplit = data.split(";");

        this.directionToClosestPil = Constants.MOVE.valueOf(dataSplit[25]);
        this.directionToClosestPowerPil = Constants.MOVE.valueOf(dataSplit[26]);
    }


    public String getSaveString() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(super.getSaveString());

        stringbuilder.append(this.directionToClosestPil).append(";");
        stringbuilder.append(this.directionToClosestPowerPil).append(";");

        return stringbuilder.toString();
    }
}
