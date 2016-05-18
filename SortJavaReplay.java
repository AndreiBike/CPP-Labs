package BubbleTank;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;



public class SortJavaReplay implements Constants {

  public static String[] sorting(Movement operation)  {
    String[] fileNames = null;
    fileNames = FileWorker.numberOfFiles();
    LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
    LinkedHashMap<String, Integer> resultMap = new LinkedHashMap<String, Integer>();
    for (int i = 0; i < fileNames.length; i++) {
      String filename = FILENAME + fileNames[i];
      int sortObject = 0;
      try {
        sortObject = FileWorker.readFromFile(filename, operation);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      map.put(filename, sortObject);
    }
    
    String[] strList = new String[fileNames.length];
    resultMap = (LinkedHashMap<String, Integer>) SortJavaReplay.sortByValue(map);

   
    synchronized (resultMap) {
      Iterator<String> iterator = resultMap.keySet().iterator();
      try {
        int tmp = 0;
        while (iterator.hasNext()) {
          strList[tmp] = iterator.next();
          tmp++;
        }
      } catch (Exception e) {
        System.out.println("Iterator problems");
      }
    }
    String move = null;
    switch(operation){
      case level:
        move = "_level";
        break;
      case movement:
        move = "_movement";
        break;
      case enemy:
        move = "_enemy";
        break;
      default:
        break;
    }
    
    try {
      FileWorker.saveBestAndWorstGame(strList[0], true, move);
      FileWorker.saveBestAndWorstGame(strList[strList.length - 1], false, move);
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    
    return strList;
  }

  public static <K, V extends Comparable<? super V>> Map<K, V>
  sortByValue(Map<K, V> map) {
    Map<K, V> result = new LinkedHashMap<>();
    Stream<Map.Entry<K, V>> st = map.entrySet().stream();
    st.sorted(Map.Entry.comparingByValue())
        .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
    return result;
  }
}
