package com.phone

import java.util

import scala.annotation.tailrec

object CountingElements {

  def permCheckSoln(a:Array[Int]):Int = {
    val compareAgainst: Set[Int] = 1 to a.length toSet
    val value = compareAgainst.diff(a.toSet).isEmpty match {
      case true => 1
      case false => 0
    }
    value
  }

  //permcheck soln using recursion

  def permCheckRec(a:Array[Int]):Int = {
    import scala.collection.BitSet
    def doRecursion(y:BitSet,z:Int):Int = {
      -1
    }
    -1
  }


  def frogRiverOneMySoln(x:Int, a:Array[Int]):Int = {
    val set = scala.collection.mutable.Set[Int]()
    val value = a.foldLeft(0) {(t, i) =>
      set+=i
      if (set.size == x) return t else t+1
    }
    -1
  }

  // Trying the frog River Soln using tail recursion.

  def frogRiverOneSoln(x:Int,a:Array[Int]):Int = {
    val bitz = scala.collection.mutable.Set[Int]()
    @tailrec
    def dorecursion(y:Int,z:Array[Int],abc:scala.collection.mutable.Set[Int],n:Int):Int= {
      abc+=z.head
      if (abc.size == n) return y
      else if (z.tail.isEmpty || z.isEmpty) return -1
      else dorecursion(y+1,z.tail,abc,n)
    }
    dorecursion(0,a,bitz,x)
  }

}
