package BubbleTank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class FileWorker {

  private final static String CHECK = "BubbleTank";

  public static void writeToFile(File file, int numWave, ArrayList<Double> statementPlayer,
      ArrayList<Double> statementEnemy) throws IOException {
    try {

      if (!file.exists()) {
        file.createNewFile();
      }
      PrintWriter out = new PrintWriter(file.getAbsoluteFile());
      try {
        out.println(CHECK);
        out.println(numWave);
        out.println(statementPlayer.size());
        for (int i = 0; i < statementPlayer.size(); i++) {
          out.println(statementPlayer.get(i));
        }
        out.println(statementEnemy.size());
        for (int i = 0; i < statementEnemy.size(); i++) {
          out.println(statementEnemy.get(i));
        }
      } finally {
        out.close();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static int readFromFile(String filename) throws FileNotFoundException {
    int waveNum;
    try {
      BufferedReader in = new BufferedReader(new FileReader(filename));
      try {
        String information;
        information = in.readLine();
        if (!information.equals(CHECK)) {
          throw new IOException();
        }
        information = in.readLine();
        waveNum = Integer.valueOf(information);
        information = in.readLine();
        int shagiPlayer = Integer.valueOf(information);
        for (int i = 0; i < shagiPlayer; i++) {
          information = in.readLine();
          GamePanel.statementPlayer.add(Double.valueOf(information));
        }
        information = in.readLine();
        int shagiEnemy = Integer.valueOf(information);
        for (int i = 0; i < shagiEnemy; i++) {
          information = in.readLine();
          GamePanel.statementEnemy.add(Double.valueOf(information));
        }
      } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "error: IO error, cannot read");
        return 0;
      }

      finally {
        in.close();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return waveNum;
  }
}
