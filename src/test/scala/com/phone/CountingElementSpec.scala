package com.phone

import org.scalatest.{FunSpec, Matchers}

class CountingElementSpec extends FunSpec with Matchers{

  describe("Perm Check"){
    it("should return 1 if given array is a permutation"){
      val a = Array(4,1,3,2)
      assert(CountingElements.permCheckSoln(a)==1)
    }

    it("should return 0 if given array is not a permutation"){
      val a = Array(4,1,3)
      assert(CountingElements.permCheckSoln(a)==0)
    }

    it("should return 0 if duplicates are present in the array"){
      val a = Array(1,2,2,3,4)
      assert(CountingElements.permCheckSoln(a)==0)
    }

    it("should return 0 if elements in array don't start from 1"){
      val a = Array(3,4,5)
      assert(CountingElements.permCheckSoln(a)==0)
    }

    it("should capture correct output if it contains a single element"){
      val a = Array(1)
      val b = Array(2)
      assert(CountingElements.permCheckSoln(a)==1)
      assert(CountingElements.permCheckSoln(b)==0)
    }

    it("should return 0 if array contains negative elements"){
      val a = Array(1,2,3,-4)
      assert(CountingElements.permCheckSoln(a)==0)
    }
  }

  describe("Frog River One"){
    it("should return 6 given x = 5"){
      val a = Array(1,3,1,4,2,3,5,4)
      assert(CountingElements.frogRiverOneSoln(5,a)==6)
    }

    it("should return 1 given x = 3"){
      val a = Array(1,2,1,2,1,3)
      assert(CountingElements.frogRiverOneSoln(3,a)==5)
    }

    it("should return -1 given x = 8"){
      val a = Array(1,3,1,4,2,3,5,4)
      assert(CountingElements.frogRiverOneSoln(8,a)== -1)
    }

    it("should return -1 given "){
      val b = Array(2,2,2,2)
      assert(CountingElements.frogRiverOneSoln(2,b)== -1)
    }

    it("should return -1 when leaves fall on the other bank but not in every step"){
      val a = Array(1,2,3,5)
      assert(CountingElements.frogRiverOneSoln(5,a)== -1)
    }
  }

}
