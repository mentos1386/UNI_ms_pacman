package dataRecording;

import pacman.game.util.*;

/**
 * This class uses the IO class in the PacMan framework to do the actual saving/loading of
 * training data.
 * @author andershh
 *
 */
public class DataSaverLoader {
	
	public static void SavePacManTrainingData(DataTuple data)
	{
		IO.saveFile("trainingData.txt", data.getSaveString(), true);
	}

	public static void SavePacManTestData(DataTuple data)
	{
		IO.saveFile("testData.txt", data.getSaveString(), true);
	}

	public static DataTuple[] LoadPacManTestData() {
		String data = IO.loadFile("testData.txt");
		return LoadPacManData(data);
	}

	public static DataTuple[] LoadPacManTrainingData()
	{
		String data = IO.loadFile("trainingData.txt");
		return LoadPacManData(data);
	}

	private static DataTuple[] LoadPacManData(String data) {
		String[] dataLine = data.split("\n");
		DataTuple[] dataTuples = new DataTuple[dataLine.length];

		for(int i = 0; i < dataLine.length; i++)
		{
			dataTuples[i] = new DataTuple(dataLine[i]);
		}

		return dataTuples;
	}
}
