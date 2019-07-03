package com.spark.formations.hbase.spark.transformations

import org.apache.spark.sql.types.{StringType, StructType}
import org.apache.spark.sql.{Dataset, Row, SparkSession}

object WholeTextFileTest extends App {

  val sparkSession :SparkSession = SparkSession.builder().master("local").getOrCreate()

  val schema :StructType = new StructType()
    .add( "Name", StringType)
    .add( "Montant", StringType)

  val rddRow  = sparkSession.sparkContext
    .wholeTextFiles(args(0))
    .flatMap(stringToIterator)


  val dataset : Dataset[Row] = sparkSession.createDataFrame(rddRow, schema)

  dataset.show()

  def stringToIterator (x : (String,String)): Array[Row] ={

    val content = x._2.split("\\r?\\n")
    val t = content.map( x=> {
      val rec = x.split(",")
      Row(rec(0),rec(1) )
    })
    t
  }
}
