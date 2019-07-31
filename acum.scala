package com.djim.tranning.spark.accumulators

import org.apache.spark.util.AccumulatorV2

class MapAccumulators extends AccumulatorV2[(String, Int),Map[String, Int]]{

  var mapAccumulators : Map[String, Int] = Map()

  override def isZero: Boolean = mapAccumulators.isEmpty

  override def copy(): AccumulatorV2[(String, Int), Map[String, Int]] = ???

  override def reset(): Unit = {
     mapAccumulators  = Map()
  }

  override def add(v: (String, Int)): Unit = {

    if (mapAccumulators.contains(v._1)) {
      val previousValue = mapAccumulators.getOrElse(v._1, 0)
      mapAccumulators += (v._1 -> (v._2 + previousValue))
    }
      else
      {
        mapAccumulators += (v._1 -> v._2)
      }
  }

  override def merge(other: AccumulatorV2[(String, Int), Map[String, Int]]): Unit = {

    other.value.foreach( x=> {
      add(x._1, x._2)
    })
  }

  override def value: Map[String, Int] = mapAccumulators
}
