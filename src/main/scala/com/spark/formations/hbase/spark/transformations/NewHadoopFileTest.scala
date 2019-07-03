package com.spark.formations.hbase.spark.transformations

import org.apache.hadoop.io.Text
import org.apache.hadoop
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

object NewHadoopFileTest extends App with Serializable {

  val sparkSession: SparkSession = SparkSession
    .builder()
    .master("local")
    .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    .getOrCreate()

  val path : String = "C:\\Users\\ddiop\\Documents\\data\\data.csv" //args(0)
  val sc : SparkContext = sparkSession.sparkContext

  val input = sc.newAPIHadoopFile(path, classOf[KeyValueTextInputFormat], classOf[Text],classOf[Text])

  for (elem <- input.collect()) {
    println(elem)
  }
}
