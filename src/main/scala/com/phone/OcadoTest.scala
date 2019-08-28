package com.phone

object OcadoTest {

  def distributeCandies(candies:Array[Int]):Int = {
    var set = scala.collection.mutable.Set[Int]()
    candies.foreach{b=>
      set+=b
    }
    (if (set.size < candies.length/2) set.size else candies.length/2)
  }

}
