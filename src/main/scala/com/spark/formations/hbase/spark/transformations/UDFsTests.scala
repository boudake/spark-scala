package com.spark.formations.hbase.spark.transformations

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.functions.udf
import org.apache.spark.sql.types.{IntegerType, StringType, StructType}

object UDFsTests {
  def main(args: Array[String]): Unit = {


    val path: String =  args(0) // "C:\\Users\\ddiop\\Documents\\data\\data.csv"

    val sparkSession: SparkSession = SparkSession.builder().
      master("local").getOrCreate()

    import sparkSession.implicits._

    val rdd = sparkSession.sparkContext.textFile(path).map(_.split(","))
      .map(x=> Row.fromSeq(x))



    val udf1 =udf(coder)
    val udf2 = udf(funct)
    val udfRow = udf(testcase)
    val udfArray = udf(testcaseArray)
    val udfarrays = udf(testarrays _)

    val defUdf = udf[Int,String] (funcRow )

    val schema : StructType = new StructType()
      .add("id", StringType)
      .add("montant", StringType)

    val schemaRow : StructType = new StructType()
      .add("row", new StructType()
        .add("id", StringType)
        .add("montant", IntegerType))

    val dataset = sparkSession.createDataFrame(rdd,schema)
      .withColumn("montant" , $"montant".cast(IntegerType))
      .withColumn("is?", udf1($"montant"))
      .withColumn("", udf2($"id", $"montant"))
      .withColumn("testcase", udfRow($"id", $"montant"))
      .withColumn("udfArray", udfArray($"id", $"montant"))
      .withColumn("defUdf", defUdf($"id"))

    dataset.show()
    dataset.printSchema()
  }
  val coder = (arg: Int) => {if (arg < 500) "little" else "big"}

  val funct = (x : String, y : Int )=>{

    x+y.toString
  }

  def funcRow (x : String ) : Int={
    1
  }

  val testcase = (x : String, y :Int ) => {
    test(x,y)
  }

  val testcaseArray = (x : String, y :Int ) => {
    List(test(x,y))
  }

  def testarrays (x : String, y :Int ): List[test]={
    List(test(x,y))
  }
  case class test(x: String, y: Int)
}
