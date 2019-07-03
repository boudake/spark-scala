package com.spark.formations.hbase.spark.transformations

import org.apache.spark.sql.{Column, SparkSession}
import org.apache.spark.sql.functions.when
object NoUDFUseTest  {

  def main(args: Array[String]): Unit = {
    val sparkSession :SparkSession = SparkSession
      .builder()
      .master("local")
      .getOrCreate()
    import sparkSession.implicits._
  import sparkSession.implicits._


    val eleveNotes = Seq(
      Eleve("CM1", 1, 50),
      Eleve("CM1", 2, 39),
      Eleve("CE2", 3, 48),
      Eleve("CE2", 4, 48),
      Eleve("CE1", 5, 35),
      Eleve("CM2", 7, 42),
      Eleve("CM2", 8, 60),
      Eleve("CM2", 9, 45),
      Eleve("CM2", 10, 56),
      Eleve("CM2", 11, 52)).toDS()

    val eleveWithApreciation = eleveNotes
    .withColumn("appreciation", appreciation($"note"))

    eleveWithApreciation.show()
}
  def appreciation(note : Column): Column ={

    when(note.gt(50), "good").otherwise("bad")
  }
  case class Eleve(Class: String, No: Int, note: Int)
}
