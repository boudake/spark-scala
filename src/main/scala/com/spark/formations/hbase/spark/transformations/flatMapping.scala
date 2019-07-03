package com.spark.formations.hbase.spark.transformations

import org.apache.spark.SparkContext
import org.apache.spark.sql.types.{StringType, StructType}
import org.apache.spark.sql.{Dataset, Row, SparkSession}

object flatMapping {

  def main(args: Array[String]): Unit = {

    val path = args(0) ; //"C:\\Users\\ddiop\\Documents\\data\\data.csv"
    val sparkSession :SparkSession = SparkSession.builder().master("local").getOrCreate()

    val sc :SparkContext = sparkSession.sparkContext

    val pairRDD = sc.wholeTextFiles(path)

 pairRDD.flatMap( x => {

      val content = x._2.split("\\r?\\n")
      val t = content.map( x=> {
        val rec = x.split(",")
        demo(rec(0),rec(1) )
      })
    t
    })

    val rddOws  =   pairRDD.flatMap( x => {
      StringToRow (x )
    })
    val dataSet : Dataset[Row] = sparkSession.createDataFrame(rddOws, schema)

    dataSet.filter(!dataSet("Montant").startsWith("mont")).filter(_.getAs("Montant") != "").show()
  }

  val schema :StructType = new StructType().add( "Name", StringType)
    .add( "Montant", StringType)



  def StringToIterator (x : (String,String)): Array[demo] ={

    val content = x._2.split("\\r?\\n")
    val t = content.map( x=> {
      val rec = x.split(",")
      demo(rec(0),rec(1) )
    })
    t
  }

  def StringToRow (x : (String,String)): Array[Row] ={

    val content = x._2.split("\\r?\\n")
    val t = content.map( x=> {
      val rec = x.split(",")
      Row.fromSeq(rec)
    })
    t

  }
  case class demo (id: String, montant: String)


}
