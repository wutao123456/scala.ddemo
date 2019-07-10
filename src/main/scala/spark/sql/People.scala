package spark.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author wutao 
  * @date 2019/7/9
  */
case class People (var name:String,var age:Int)

object TestDataFrame1 {
  def main(args: Array[String]): Unit = {
    json()
  }

  def dataFrame(): Unit ={
    val conf = new SparkConf().setAppName("testDF").setMaster("local")
    val sc = new SparkContext(conf)

    val context = new SQLContext(sc)

    val peopleRdd = sc.textFile("E:\\tmp\\spark\\sql\\people.txt").map(line => People(line.split(",")(0),line.split(",")(1).trim.toInt))

    import context.implicits._
    val df = peopleRdd.toDF

    df.createOrReplaceTempView("people")
    context.sql("select * from people").show()
  }

  def structType(): Unit ={
    val conf = new SparkConf().setAppName("testDF").setMaster("local")
    val sc = new SparkContext(conf)

    val context = new SQLContext(sc)

    val rowRDD:RDD[Row] = sc.textFile("E:\\tmp\\spark\\sql\\people.txt").map(line => Row(line.split(",")(0),line.split(",")(1).trim.toInt))


    val structType:StructType = StructType(
      StructField("name",StringType,true)::
      StructField("age",IntegerType,true)::Nil
    )

    val df = context.createDataFrame(rowRDD,structType)
    df.createOrReplaceTempView("people")
    context.sql("desc people").show()
  }

  def json(): Unit ={
    import org.apache.spark.sql.SparkSession
    val context = SparkSession.builder.appName("testDF").master("local").config("test", "1").getOrCreate
    val df:DataFrame = context.read.json("E:\\tmp\\spark\\sql\\people.json")

    df.createOrReplaceTempView("people")
    df.show()
    df.printSchema()
//    context.sql("select * from people").show()
  }
}
