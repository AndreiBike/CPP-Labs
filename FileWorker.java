package BubbleTank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class FileWorker implements Constants {

  private final static String CHECK = "BubbleTank";
  private static int tmp = 1;


  public static String[] numberOfFiles() {
    File listFile = new File(FILENAME);
    File exportFiles[] = listFile.listFiles();
    String[] names = new String[exportFiles.length];
    for (int i = 0; i < names.length; i++)
      names[i] = exportFiles[i].getName();
    return names;
  }

  public static void writeToFile(File file, int numWave, ArrayList<Double> statementPlayer,
      ArrayList<Double> statementEnemy, boolean status) throws IOException {

    try {
      if (!status) {
        file = new File(FILENAME);
        file.mkdirs();
        do {
          file = new File(FILENAME + "generate_replay_" + String.valueOf(tmp));
          tmp++;
        } while (file.exists());
      }
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
    tmp = 1;
  }

  public static int readFromFile(String filename, Movement doing) 
      throws FileNotFoundException {
    int waveNum;
    int movePlayer;
    int moveEnemy;
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
        movePlayer = Integer.valueOf(information);
        for (int i = 0; i < movePlayer; i++) {
          information = in.readLine();
          GamePanel.statementPlayer.add(Double.valueOf(information));
        }
        information = in.readLine();
        moveEnemy = Integer.valueOf(information);
        for (int i = 0; i < moveEnemy; i++) {
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
    switch (doing) {
      case level:
        clearData();
        return waveNum;
      case movement:
        clearData();
        return movePlayer;
      case enemy:
        clearData();
        return moveEnemy / 2;
      default:
        return waveNum;
    }
  }

  private static void clearData() {
    GamePanel.statementEnemy.clear();
    GamePanel.statementPlayer.clear();
  }

  public static void saveBestAndWorstGame(String filename,
     boolean bestOrWorst, String move)
      throws IOException {
    try {
      File oldFile = new File(filename);
      File newFile;
      if (bestOrWorst) {
        newFile = new File(BEST_GAME + move);
        if (newFile.exists()) {
          newFile.delete();
          newFile = new File(BEST_GAME + move);
        }
      } else {
        newFile = new File(WORST_GAME + move);
        if (newFile.exists()) {
          newFile.delete();
          newFile = new File(WORST_GAME + move);
        }
      }
      Files.copy(oldFile.toPath(), newFile.toPath());
    } catch (IOException ex) {
      System.out.printf("mistake");
    }
  }
}
