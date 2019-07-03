//package com.spark.formations.hbase
/*
import org.apache.spark.sql.{SQLContext, _}
//import org.apache.spark.sql.execution.datasources.hbase._
import org.apache.spark.{SparkConf, SparkContext}
//import spark.sqlContext.implicits._

object testHbase {
  def main(args: Array[String]): Unit = {
    println("Hello World!")

    val conf = new SparkConf()
      .setAppName("MySparkDriverApp")
      .setMaster("local")

    val sc = new SparkContext(conf)

    val sqlContext = new SQLContext(sc)

 /*   val spark = SparkSession.builder()
      .appName("")
      .master("local")
      .getOrCreate();
 */
    def catalog =
      s"""{
         |"table":{"namespace":"default", "name":"Contacts"},
         |"rowkey":"key",
         |"columns":{
         |"rowkey":{"cf":"rowkey", "col":"key", "type":"string"},
         |"officeAddress":{"cf":"Office", "col":"Address", "type":"string"},
         |"officePhone":{"cf":"Office", "col":"Phone", "type":"string"},
         |"personalName":{"cf":"Personal", "col":"Name", "type":"string"},
         |"personalPhone":{"cf":"Personal", "col":"Phone", "type":"string"}
         |}
         |}""".stripMargin

    def withCatalog(cat: String): DataFrame = {
       sqlContext
        .read
        .options(Map(HBaseTableCatalog.tableCatalog -> cat))
        .format("org.apache.spark.sql.execution.datasources.hbase")
        .load()
    }

    withCatalog(catalog).show()
  }


}
*/