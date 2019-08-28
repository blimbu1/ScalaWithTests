package com.phone

import org.scalatest.{FreeSpec, FunSpec, Matchers}

class MushroomTestSpec extends FunSpec with Matchers{

  describe("Mushroom"){
    val a = Array(2,3,7,5,1,3,9)
    it("should return 25 given k = 4 and m = 6"){
      MushroomTest.solve(4,6,a) shouldBe (25)
    }
    
    it("should return 13 given k = 4 and m = 4"){
      MushroomTest.solve(4,4, Array(2,3,7,4,1,3,9)) shouldBe (13)
    }

    it("should return 28 given k = 1 and m = 6"){
      MushroomTest.solve(1,6,a) shouldBe (28)
    }

    it("should return 17 given k = 3 and m = 4 "){
      val b = Array(2,3,7,5,7,3,1)
      assert(MushroomTest.solve(3,4,b)==17)
    }

    it("should tend towards bigger number"){
      val c = Array(0,1,2,3,4,5,6,7)
      MushroomTest.solve(4,4,c) shouldBe (22)
    }
  }

  describe("basic cases"){
    it("should return correct value for array len = 2"){
      MushroomTest.solve(1,2,Array(4,2)) shouldBe (6)
    }

    it ("should return correct output given array len = 3"){
      MushroomTest.solve(1,1,Array(1,2,2)) shouldBe (4)
    }
  }

}
