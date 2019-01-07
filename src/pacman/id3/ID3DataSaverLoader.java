package pacman.id3;

import pacman.game.util.IO;

public class ID3DataSaverLoader {

    public static void SavePacManTrainingData(ID3DataTuple data) {
        IO.saveFile("trainingData.txt", data.getSaveString(), true);
    }

    public static void SavePacManTestData(ID3DataTuple data) {
        IO.saveFile("testData.txt", data.getSaveString(), true);
    }

    public static ID3DataTuple[] LoadPacManTestData() {
        String data = IO.loadFile("testData.txt");
        return LoadPacManData(data);
    }

    public static ID3DataTuple[] LoadPacManTrainingData() {
        String data = IO.loadFile("trainingData.txt");
        return LoadPacManData(data);
    }

    private static ID3DataTuple[] LoadPacManData(String data) {
        String[] dataLine = data.split("\n");
        ID3DataTuple[] dataTuples = new ID3DataTuple[dataLine.length];

        for (int i = 0; i < dataLine.length; i++) {
            dataTuples[i] = new ID3DataTuple(dataLine[i]);
        }

        return dataTuples;
    }
}