package com.spark.formations.hbase.spark.transformations

import com.spark.formations.hbase.spark.transformations.WindowsFunctionsTest.Eleve
import org.apache.spark.sql.SparkSession

object accumulatorsTest extends App {

  val sparkSession :SparkSession = SparkSession
    .builder()
    .master("local")
    .getOrCreate()
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

  val counter = sparkSession.sparkContext.longAccumulator("counter")

  eleveNotes.foreach(x =>
  {
    if (x.note > 50)
      counter.add(1)
  })


  println("Le nombre de note superieur à 50: " +counter.value)
}
