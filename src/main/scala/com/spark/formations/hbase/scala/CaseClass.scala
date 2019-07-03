package com.spark.formations.hbase.scala

object CaseClass extends App {

  case class test (name: String, age: Int)
  case class test2 (name: String, age: Int)

  println(test("djim", 34).equals(test("djim", 34)))

  println(test("djim", 34).equals(test2("djim", 34)))
}
