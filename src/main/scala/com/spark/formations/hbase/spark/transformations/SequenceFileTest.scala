package com.spark.formations.hbase.spark.transformations

import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

object SequenceFileTest {


  val sparkSession: SparkSession = SparkSession
    .builder()
    .master("local")
    .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    .getOrCreate()

  val path : String = "C:\\Users\\ddiop\\Documents\\data\\data.seq" //args(0)
  val sc : SparkContext = sparkSession.sparkContext

  val input = sc.sequenceFile(path,  classOf[IntWritable], classOf[Text])

  for (elem <- input.collect()) {
    println(elem)
  }
}
