package com.phone

import org.apache.spark.sql.Row
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types._
import org.scalatest.{FunSpec, Matchers}

class SkyTestSpec extends FunSpec with SparkSessionTestWrapper with Matchers{

  import spark.implicits._

  describe("mapper"){
   it ("mapper should return an object of case class"){
      val expected = SkyTest.Calls("A","555-333","00:02:03")
      val actual = SkyTest.mapper("A 555-333 00:02:03")
      assert(expected == actual)
    }

    it ("should catch out incomplete logs"){
      assert(SkyTest.mapper("A Boy")==SkyTest.Calls("Bad","Bad","00:00:00"))
    }
  }

  describe("timeSeconds"){

    it ("calculates time in seconds for each call in the dataframe") {
      val sourceDF = Seq(
        ("00:02:03"),
        ("01:01:01"),
        ("00:01:59")
      ).toDF("duration")

      val actualDF = sourceDF.withColumn("inSeconds", SkyTest.timeSeconds(col("duration")))

      val expectedSchema = List(
        StructField("duration", StringType, true),
        StructField("inSeconds", IntegerType, true)
      )

      val expectedData = Seq(
        Row("00:02:03", 123),
        Row("01:01:01", 3661),
        Row("00:01:59", 119)
      )

      val expectedDF = spark.createDataFrame(
        spark.sparkContext.parallelize(expectedData),
        StructType(expectedSchema)
      )

      expectedDF.except(actualDF).count should be(0)
    }
  }

  describe("costPerCall"){
    it("should calculate the cost accurately for each call"){
      val sourceDF = Seq(
        (300),
        (180),
        (100)).toDF("inSeconds")

      val actualDF = sourceDF.withColumn("cost", SkyTest.costPerCall(col("inSeconds")))

      val expectedSchema = List(
        StructField("inSeconds", IntegerType, true),
        StructField("cost", DoubleType, true)
      )

      val expectedData = Seq(
        Row(300, 12.60),
        Row(180, 9.00),
        Row(100, 5.00)
      )

      val expectedDF = spark.createDataFrame(
        spark.sparkContext.parallelize(expectedData),
        StructType(expectedSchema)
      )

      expectedDF.except(actualDF).count should be(0)

    }
  }

  describe("finalOutput"){
    it("should take a dataframe with individual calls and return a concise dataframe"){
      val sourceDF = Seq(
        ("A",300,12.60),
        ("B",100,5.00),
        ("A",180,9.00),
        ("A",100,5.00),
        ("B",300,12.60)
      ).toDF("customerID","inSeconds","cost")

      val actualDF = sourceDF.transform(SkyTest.finalOutput())

      val expectedSchema = List(
        StructField("customerID", StringType, true),
        StructField("finalCost", DoubleType, true)
      )

      val expectedData = Seq(
        Row("A", 14.00),
        Row("B", 5.00)
      )

      val expectedDF = spark.createDataFrame(
        spark.sparkContext.parallelize(expectedData),
        StructType(expectedSchema)
      )

      expectedDF.except(actualDF).count should be(0)
    }

    it("should return the final cost to two decimal places"){
      assert(SkyTest.toTwoDec(14.30003)==14.30)
    }
  }

}
