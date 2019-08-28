package com.phone

import org.scalatest.{FreeSpec,Matchers}

class PermMissingElem extends FreeSpec with Matchers {

  private trait Solution {

    def soln1(a: Array[Int]):Int = {
      if (a.isEmpty) return 1
      if (a.size == 1) return 2
      val x = a.reduce(_+_)
      val z = a.max
      val total = List.tabulate(z)(_+1).reduce(_+_)
      val y = if ((total-x) == 0) z+1 else total-x
      return y
    }
    // solution from http://scalaproblems.github.io/
    def soln(A: Array[Int]): Int = {
      val N: Long = (A.length + 1).toLong
      val expectedSum: Long = (N) * (N + 1) / 2
      val actualSum: Long = A.view.map(_.toLong).sum
      (expectedSum - actualSum).toInt
    }

  }

  "My test cases" - {
    "should return 4" in new Solution{
      val a = Array(2,3,1,5)
      soln(a) shouldBe 4
    }

    "should return 1 when array empty" in new Solution {
      val a:Array[Int] = Array()
      soln(a) shouldBe 1
    }

    "should return 2" in new Solution{
      val a = Array(1,3)
      soln(a) shouldBe 2
    }

    "should return 2 for a single element" in new Solution{
      val a = Array(1)
      soln(a) shouldBe 2
    }

    "should return 1 for missing first element" in new Solution{
      val a = Array(2,3,4)
      soln(a) shouldBe 1
    }

    "should return the last element" in new Solution{
      val a = Array(1,2,3)
      soln(a) shouldBe 4
    }

  }

}
