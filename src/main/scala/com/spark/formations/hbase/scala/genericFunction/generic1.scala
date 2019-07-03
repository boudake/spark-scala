package com.spark.formations.hbase.scala.genericFunction

object generic1 extends App {

  println(randomElement(Seq("Fall", "Djom", "Deme", "Molly")))
  println(randomElement(List(1,2,3)))
  println(randomElement(List(1.0,2.0,3.0)))
  println(randomElement(Vector.range('a', 'z')))

  println(applyDiscountWithReturnType(1))

  def randomName(names: Seq[String]): String = {
    val randomNum = util.Random.nextInt(names.length)
    names(randomNum)

  }
    def randomElement[A] (names: Seq[A]): A = {
      val randomNum = util.Random.nextInt(names.length)
      names(randomNum)
  }

  def applyDiscountWithReturnType[T](discount: T): Option[T] = {
    discount match {
      case d: String =>
        println(s"Lookup percentage discount in database for $d")
        Some[T](discount)

      case d: Double =>
        println(s"$d discount will be applied")
        Some[T](discount)

      case _ =>
        println("Unsupported discount type")
        None
    }
  }
}
