package com.phone

import org.scalatest.{FunSpec, Matchers}

class MissingIntegerSpec extends FunSpec with Matchers{

  describe("Missing Integer"){
    it("returns 5 given an array A = [1,3,6,4,1,2]"){
      val a = Array(1,3,6,4,1,2)
      val actual = MissingInteger.soln(a)
      assert(actual == 5)
    }

    it("returns 4 when A = [1,2,3]"){
      val a = Array(1,2,3)
      val actual = MissingInteger.soln(a)
      assert(actual==4)
    }

    it("returns 1 when A = [-1,-4]"){
      val a = Array(-1,-4)
      val actual = MissingInteger.soln(a)
      assert(actual==1)
    }
    it("returns 1 when A = [0,0,0]"){
      val a = Array(0,0,0)
      val actual = MissingInteger.soln(a)
      assert(actual==1)
    }
    it("returns 1 when A = [8,9,9,10]"){
      val a = Array(8,9,9,10)
      val actual = MissingInteger.soln(a)
      assert(actual == 1)
    }

    it("returns 1 when A = [100]"){
      val a = Array(100)
      val actual = MissingInteger.soln(a)
      assert(actual==1)
    }
  }

}
