package com.phone

import org.scalatest.{FlatSpec, Matchers}

class OcadoTestSpec extends FlatSpec with Matchers{

  "distribute Candies" should "return 3 when a = [1,1,2,2,3,3,]" in {
    val candies = Array(1,1,2,2,3,3)
    OcadoTest.distributeCandies(candies) shouldBe(3)
  }

  "distribute candies" should "return 2 when a = [1,1,2,2,2,2]" in {
    val candies = Array(1,1,2,2,2,2)
    OcadoTest.distributeCandies(candies) shouldBe(2)
  }

  "distribute candies" should "return 4 when a = [1,2,3,4,5,6,7,8]" in {
    val candies = Array(1,2,3,4,5,6,7,8)
    OcadoTest.distributeCandies(candies) shouldBe (4)
  }

  "distribute candies" should "return 1 when length of a is 1" in {
    val candies = Array(1,2)
    OcadoTest.distributeCandies(candies) shouldBe (1)
  }

}
