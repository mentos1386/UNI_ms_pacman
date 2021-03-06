package pacman.id3;

import dataRecording.DataTuple;
import pacman.game.Constants;
import pacman.game.Game;

public class ID3DataTuple extends DataTuple {

    public Constants.MOVE directionToClosestPil;
    public Constants.MOVE directionToClosestPowerPil;
    public Constants.MOVE getMoveAwayFromThreat;
    public boolean isGhostClose;
    public boolean isPowerPilClose;
    public int distanceToClosestGhost;
    public ID3Label label;
    public boolean isClosestGhostEdible;
    public int howManyGhostsAreClose;
    public boolean ghostWasEaten;

    public ID3DataTuple(Game game, Constants.MOVE move, ID3Label label) {
        this(game, move);
        this.label = label;
    }

    public ID3DataTuple(Game game, Constants.MOVE move) {
        super(game, move);

        this.directionToClosestPil = game.directionToClosestPill();
        this.directionToClosestPowerPil = game.directionToClosestPowerPill();
        this.getMoveAwayFromThreat = game.getMoveAwayFromThreat(game.closestGhost());
        this.isGhostClose = game.isGhostClose(ID3Constants.DISTANCE_TOLERANCE_GHOSTS);
        this.isPowerPilClose = game.isPowerPillClose(ID3Constants.DISTANCE_TOLERANCE);
        this.distanceToClosestGhost = game.distanceToClosestGhost();
        this.isClosestGhostEdible = game.isGhostEdible(game.closestGhost());
        this.howManyGhostsAreClose = game.howManyGhostsAreClose(ID3Constants.DISTANCE_TOLERANCE_GHOSTS);
        this.ghostWasEaten = game.wasAnyGhostEaten();
    }

    public ID3DataTuple(String data) {

        super(data);

        String[] dataSplit = data.split(";");
        this.directionToClosestPil = Constants.MOVE.valueOf(dataSplit[25]);
        this.directionToClosestPowerPil = Constants.MOVE.valueOf(dataSplit[26]);
        this.getMoveAwayFromThreat = Constants.MOVE.valueOf(dataSplit[27]);
        this.isGhostClose = Boolean.parseBoolean(dataSplit[28]);
        this.isPowerPilClose = Boolean.parseBoolean(dataSplit[29]);
        this.distanceToClosestGhost = Integer.parseInt(dataSplit[30]);
        this.label = ID3Label.valueOf(dataSplit[31]);
        this.isClosestGhostEdible = Boolean.parseBoolean(dataSplit[32]);
        this.howManyGhostsAreClose = Integer.parseInt(dataSplit[33]);
        this.ghostWasEaten = Boolean.parseBoolean(dataSplit[34]);
    }


    public String getSaveString() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(super.getSaveString());

        stringbuilder.append(this.directionToClosestPil).append(";");
        stringbuilder.append(this.directionToClosestPowerPil).append(";");
        stringbuilder.append(this.getMoveAwayFromThreat).append(";");
        stringbuilder.append(this.isGhostClose).append(";");
        stringbuilder.append(this.isPowerPilClose).append(";");
        stringbuilder.append(this.distanceToClosestGhost).append(";");
        stringbuilder.append(this.label).append(";");
        stringbuilder.append(this.isClosestGhostEdible).append(";");
        stringbuilder.append(this.howManyGhostsAreClose).append(";");
        stringbuilder.append(this.ghostWasEaten).append(";");

        return stringbuilder.toString();
    }
}
