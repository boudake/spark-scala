package com.spark.formations.hbase.spark.transformations

import org.apache.spark.sql.SparkSession

object ReduceByKeyTest extends App {


  val sparkSession :SparkSession = SparkSession.builder().master("local").getOrCreate()

  val rdd = sparkSession.sparkContext.parallelize(Seq(
    ("math", 12),
    ("histoire",0),
      ("SVT", 3),
    ("math", 12)
  ))

  rdd.reduceByKey((x,y)=> x+y).foreach(println)

  //utilisation avec function
  rdd.reduceByKey(sum).foreach(println)

  def sum( accum : Int, value : Int)= {accum+ value}
}
