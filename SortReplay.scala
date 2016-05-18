package BubbleTank

import scala.collection.mutable.Buffer
import scala.collection.Map
import scala.collection.immutable.ListMap
import scala.collection.immutable.List
import scala.collection.mutable.LinkedHashMap
import scala.collection.JavaConverters._
import scala.collection.mutable.MutableList

class SortReplay {

  var sortObject = 0;
  var worstGame = "worstGame"
  var bestGame = "bestGame"
  val directory = "E:/replays/"
  var move = "move"
  var step = "step"

  def sort(operation: Movement) = {
    var i = 0
    var fileNames: Array[String] = new Array[String](0)
    fileNames = FileWorker.numberOfFiles()
    val map = scala.collection.mutable.Map[String, Int]()
    for (i <- 0 until fileNames.length) {
      var filename = directory + fileNames(i)
      sortObject = FileWorker.readFromFile(filename, operation)
      map += filename -> sortObject
    }
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

  def mergeSort(xs: List[(String, Int)]): List[(String, Int)] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge(xs: List[(String, Int)], ys: List[(String, Int)]): 
      List[(String, Int)] = (xs, ys) match {
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
}