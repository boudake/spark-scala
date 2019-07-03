package com.spark.formations.hbase.spark.transformations

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.avg
import org.apache.spark.sql.functions.max
object WindowsFunctionsTest {

  def main(args: Array[String]): Unit = {
    val sparkSession: SparkSession = SparkSession
      .builder()
      .master("local")
      .getOrCreate()
    import sparkSession.implicits._

    val byClass = Window.partitionBy('Class)




    val eleveNotes = Seq(
      Eleve("CM1", 1, 50),
      Eleve("CM1", 2, 39),
      Eleve("CE2", 3, 48),
      Eleve("CE2", 4, 48),
      Eleve("CE1", 5, 35),
      Eleve("CM2", 7, 42),
      Eleve("CM2", 8, 60),
      Eleve("CM2", 9, 45),
      Eleve("CM2", 10, 52),
      Eleve("CM2", 11, 52)).toDS()

    // exemple pour max et moyenne.
    eleveNotes.withColumn("avg", avg('note) over byClass).show
    eleveNotes.withColumn("max", max('note) over byClass).show
  }
  case class Eleve(Class: String, No: Int, note: Int)
}