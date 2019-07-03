package com.spark.formations.hbase.spark.transformations

import org.apache.spark.sql.SparkSession

object JoinsTest extends App {

  {
    val sparkSession: SparkSession = SparkSession
      .builder()
      .master("local")
      .getOrCreate()
    import sparkSession.implicits._

    val eleveNotes = Seq(
      Eleve("1","CM1", 1, 50),
      Eleve("3","CM1", 2, 39),
      Eleve("2","CE2", 3, 48),
      Eleve("1","CE2", 4, 48),
      Eleve("2","CE1", 5, 35),
      Eleve("3","CM2", 7, 42),
      Eleve("3","CM2", 8, 60),
      Eleve("2","CM2", 9, 45),
      Eleve("1","CM2", 10, 56),
      Eleve("3","CM2", 11, 52)).toDS()

    val ecoles = Seq(
      Ecole("1","Ecole1"),
      Ecole("3","Ecole 3"),
      Ecole("2","Ecole 2")).toDS()


    eleveNotes.join(ecoles, $"NoEcole" === $"NoEcol", "inner").show()
  }

  case class Eleve(NoEcol: String, Class: String, No: Int, Note: Int)
  case class Ecole(NoEcole: String, NomEcole: String)
}
