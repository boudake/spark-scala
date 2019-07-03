package com.spark.formations.testing


import org.apache.spark.sql.SparkSession
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import org.scalamock.scalatest.MockFactory

class tests extends FunSuite with BeforeAndAfterEach with MockFactory {

  var sparkSession : SparkSession = _

  override def beforeEach() {
    sparkSession = SparkSession.builder().appName("testings")
      .master("local")
      .getOrCreate()
  }


  test("your test name here"){
    //your unit test assert here like below
    val m = mockFunction[String, String]
    m expects "" returning "line,line"



    assert("True".toLowerCase == "true")
  }

  override def afterEach() {
    sparkSession.stop()
  }
}
