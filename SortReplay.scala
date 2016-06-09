package BubbleTank

import scala.collection.mutable.Buffer
import scala.collection.Map
import scala.collection.immutable.ListMap
import scala.collection.immutable.List
import scala.collection.mutable.LinkedHashMap
import scala.collection.JavaConverters._
import scala.collection.mutable.MutableList

class SortReplay {

  var level = 0;
  var enemy = 0;
  var moves = 0;
  var sortObject = 0;
  var worstGame = "worstGame"
  var bestGame = "bestGame"
  val directory = "E:/replays/"
  var move = "move"
  var step = "step"
  var levelStatistic = 0
  var enemyStatistic = 0
  var moveStatistic = 0
  var fileNames: Array[String] = new Array[String](0)

  def sort(operation: Movement) = {
    var i = 0
   
    fileNames = FileWorker.numberOfFiles()
    val map = scala.collection.mutable.Map[String, Int]()
    for (i <- 0 until fileNames.length) {
      var filename = directory + fileNames(i)
      if (operation != Movement.statistic) {
        sortObject = FileWorker.readFromFile(filename, operation)
        map += filename -> sortObject

      } else {
        levelStatistic += FileWorker.readFromFile(filename, Movement.level);
        enemyStatistic += FileWorker.readFromFile(filename, Movement.enemy);
        moveStatistic += FileWorker.readFromFile(filename, Movement.movement);
      }
    }

    if (operation != Movement.statistic) {
      var resultList = mergeSort(map.toList)

      (operation: @unchecked) match {
        case (Movement.level) => {
          for (i <- 0 until fileNames.length) {
            print("Sort by start level" + resultList(i).toString() + "\n")
          }
          move = "_level"
        }
        case Movement.movement => {
          for (i <- 0 until fileNames.length) {
            print("Sort by movements" + resultList(i).toString() + "\n")
          }
          move = "_movement"
        }
        case Movement.enemy => {
          for (i <- 0 until fileNames.length) {
            print("Sort by enemies" + resultList(i).toString() + "\n")
          }
          move = "_enemy"
        }
      }

      step = resultList(0).toString()
      worstGame = step.substring(1, step.indexOf(','))
      print("Worst Replay: " + worstGame + "\n")
      FileWorker.saveBestAndWorstGame(worstGame, false, move)

      step = resultList(fileNames.length - 1).toString()
      bestGame = step.substring(1, step.indexOf(','))
      print("Best Replay: " + bestGame + "\n")
      FileWorker.saveBestAndWorstGame(bestGame, true, move)
    }
  }

  def getSegOfOneChar (xs: Array[Char], toFind: Char): IndexedSeq[Char] =
    for (i<- 0 until xs.length if xs(i) == toFind) yield xs(i)
  
  def getEnemyStatistic() = {
    enemyStatistic/fileNames.length
  }

  def getMoveStatistic() = {
    moveStatistic/fileNames.length
  }

  def getLevelStatistic() = {
    levelStatistic
  }

  def mergeSort(xs: List[(String, Int)]): List[(String, Int)] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge(xs: List[(String, Int)], ys: List[(String, Int)]): List[(String, Int)] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (x._2 < y._2) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
      val (left, right) = xs splitAt (n)
      merge(mergeSort(left), mergeSort(right))
    }
  }

  def chooser(value: Any): String = {
    value match {
      case ("BubbleTank") => "Identificator" + value.toString();
      case value: Int => "Start Level"
      case "1.0" => "Fire"
      case "0.0" => "No fire"
      case value: String => "Move to coordinate " + value.toString()
      case value: Double => "Enemy start coordinates " + value.toString()
    }
  }
}