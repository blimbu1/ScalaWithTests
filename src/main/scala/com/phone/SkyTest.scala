package com.phone

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.{col, udf,sum}

/**
 * @author ${user.name}
 */
object SkyTest {

  case class Calls(customerID:String, dialled:String, duration:String)

  def mapper(line:String):Calls={
    val fields = line.trim.split(" ")
    val call = if (fields.size == 3) Calls(fields(0),fields(1),fields(2)) else Calls("Bad","Bad","00:00:00")
    call
  }

  val timeSeconds: UserDefinedFunction = udf((j:String) =>{
    val field: Array[String] = Option(j.split(":")).getOrElse(Array("00","00","00"))
    val totaltime = if (field.size == 3) field(0).toInt * 3600 + field(1).toInt * 60 + field(2).toInt else 0
    totaltime
  })

  val costPerCall = udf((x:Int) =>{
    val cost = if (x <= 180) 0.05*x else {(x-180)*0.03 + 0.05*180}
    val roundedCost = BigDecimal(cost).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
    roundedCost
  })

  def toTwoDec(d:Double):Double = {
    BigDecimal(d).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }

  val toTwoDecUserDefined = udf[Double,Double](toTwoDec)

  def finalOutput()(df:DataFrame):DataFrame = {
    val totalCost = df.groupBy("customerID").agg(sum(col("cost")).as("totalCost"))
    val maxCost = df.groupBy("customerID").max("cost")
    val finalDF = totalCost.join(maxCost,Seq("customerID"))
    val removeCostDF = finalDF.withColumn("finCost",col("totalCost") - col("max(cost)"))
    val finalRounded = removeCostDF.withColumn("finalCost",toTwoDecUserDefined(col("finCost")))
    val finalCost = finalRounded.select(col("customerID"),col("finalCost")).sort(col("customerID"))
    finalCost
  }

  def main(args : Array[String])= {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession
      .builder()
      .appName("sky_test")
      .master("local")
      .getOrCreate()

    import spark.implicits._
    val callList = spark.sparkContext.textFile("./src/main/resources/calls.log")
    val logs = callList.map(mapper).toDS()
    val logsWithTime = logs.withColumn("inSeconds",timeSeconds(col("duration")))
    val logsWithCost = logsWithTime.withColumn("cost",costPerCall(col("inSeconds")))
    val summarised = logsWithCost.transform(finalOutput())
    summarised.show()

  }

}
