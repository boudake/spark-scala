package com.spark.formations.hbase.spark.transformations


import org.apache.spark.sql.SparkSession

object BroadcastTest extends App {

  val sparkSession :SparkSession = SparkSession
    .builder()
    .master("local")
    .getOrCreate()
  import sparkSession.implicits._
  
  val broadcastValue = sparkSession.sparkContext.broadcast("CM1")

  val notes = Seq(
    ("CM1", 1, 50),
    ("CM1", 2, 39),
    ("CE2", 3, 48),
    ("CE2", 4, 48),
    ("CE1", 5, 35),
    ("CM2", 7, 42),
    ("CM2", 8, 60),
    ("CM2", 9, 45),
    ("CM2", 10, 56),
    ("CM2", 11, 52)).toDS()


  notes.filter(x => {broadcastValue.value.equals(x._1)}).collect().foreach(println)

}
