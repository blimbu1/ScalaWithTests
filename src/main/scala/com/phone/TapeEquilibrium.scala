package com.phone

import scala.math.abs
import scala.annotation.tailrec

object TapeEquilibrium {
  def solnDiss(a:Array[Int]):Int={
    val x = a.length
    val len = (1 until x).toList
    val smallest = len.foldLeft(10000){(compare,i) =>
      val arrayOne = a.slice(0,i).reduce(_+_)
      val arrayTwo = a.slice(i,x).reduce(_+_)
      val diff = abs(arrayOne - arrayTwo)
      println(diff)
      val newVal = if (diff < compare) diff else compare
      newVal
    }
    smallest
  }

  //second solution using reduce

  // Don't think it will work as reduce takes first two elements from the list.
  def solnOne(a:Array[Int]):Int = {
    val x = a.length
    val len = (1 until x).toList
    val smallest = len.reduce{(compare,i) => // <----------------here
      val arrayOne = a.slice(0,i).reduce(_+_)
      val arrayTwo = a.slice(i,x).reduce(_+_)
      val diff = abs(arrayOne - arrayTwo)
      println(diff)
      val newVal = if (diff < compare) diff else compare
      newVal
    }
    smallest
  }

  //Using Felipe's technique but rewriting it myself
  def solnFelipe(a:Array[Int]):Int = {
    if (a.length<2 || a.length >100000) println("error")
    val total = a.reduce(_+_).toLong
    val lastElem = a.size - 1
    println(lastElem)
    val mins: (Int, Long, Long) = a.foldLeft(-1,0l,0l){ (t, i) =>
      val newIndex = t._1 + 1
      //println(newIndex)
      //val minDiff = t._2
      val leftTotal = t._3 + i.toLong
      val rightTotal = total - leftTotal
      val diff = abs(leftTotal - rightTotal)
      println(diff)
      val minDiff: Long = newIndex match {
        case 0 => diff
        //case (a.length-1) => t._2
        case _ => if (diff < t._2) diff else t._2
      }
      println(minDiff)
      (newIndex,minDiff,leftTotal)
    }
    mins._2.toInt
  }

  //using David's tail recursion
  def soln(a:Array[Int]):Int = {

    @tailrec
    def calculateMin(x:Array[Int],leftSum:Long, rightSum:Long,min:Long):Long = {
      if (x.length == 1){
        min
      }
      else{
        val one = x.head.toLong
        val left = leftSum + one
        val right = rightSum - one
        val diff = if (abs(left-right) > min) min else abs(left - right)
        calculateMin(x.tail,left,right,diff)
      }
    }
    if (a.length < 2 || a.length > 100000) 0
    else calculateMin(a,0l,a.sum.toLong,1000000).toInt
  }
}
