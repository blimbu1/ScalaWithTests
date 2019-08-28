package com.phone

object MissingInteger {

  def soln(a:Array[Int]):Int = {
    val y = a.distinct
    val x = y.filter(z=> z>0)
    if (x.isEmpty) return 1
    if (x.length == 1 && x.head != 1) return 1
    if (x.length == 1 && x.head == 1) return 2
    val z:Long = x.max
    val total: Long = z * (z+1)/2
    val totalOriginal:Long = x.sum
    val missing: Long = (total-totalOriginal) match {
      case 0 => z+1
      case _ => if ((total-totalOriginal)>z) 1 else (total-totalOriginal)
    }
    missing.toInt
  }

  def solnTwo(a:Array[Int]):Int = {
    val y = a.distinct
    val x = y.filter(z=> z>0)
    if (x.isEmpty) return 1
    if (x.length == 1 && x.head != 1) return 1
    if (x.length == 1 && x.head == 1) return 2
    if (x.length.toLong == x.max.toLong) return (x.max.toLong + 1).toInt
    val newList = (1 until x.max).toArray
    newList.view.filter(b=> (!x.contains(b)) ).head
  }

  def solution(A: Array[Int]): Int = {
    val bitz = new java.util.BitSet(A.size)

    val n: Int = A.foldLeft(0) { (total, i) =>
      if (i > 0 && i <= A.size && !bitz.get(i)) {
        bitz.set(i)
        total + 1
      } else total
    }

    val possibilities: Int = if (n < 1) 1
    else n

    (1 to possibilities).foldLeft(possibilities + 1) { (current, i) =>
      bitz.get(i) match {
        case true =>
          current
        case false =>
          return i
      }
    }
  }

}
