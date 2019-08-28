package com.phone

import org.scalatest.{FunSpec, Matchers}

class TapeEquilibriumSpec extends FunSpec with Matchers {
  describe("tapeEquilibrium"){
    it("should return minimum values case :one"){
      val a = Array(3,1,2,4,3)
      val actual = TapeEquilibrium.soln(a)
      assert(actual == 1)
    }

    it("should return minimum value caes:two"){
      val a = Array(10,4,9,13,3,2)
      val actual = TapeEquilibrium.soln(a)
      assert(actual==5)
    }

    it("should return correct difference for 2 elements"){
      val a = Array(5,9)
      val actual = TapeEquilibrium.soln(a)
      assert(actual == 4 )
    }

    it("should return correct value when there are negative values"){
      val a = Array(10,-4,-8,15)
      val actual = TapeEquilibrium.soln(a)
      assert(actual == 1)
    }

  }
}
