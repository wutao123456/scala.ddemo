package spark.sql

import org.apache.spark.sql.{DataFrame, SQLContext, SparkSession}
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

  def json(): Unit ={
    val conf = new SparkConf().setAppName("testDF").setMaster("local")
    val sc = new SparkContext(conf)

    val context = new SQLContext(sc)
    val df:DataFrame = context.read.json("E:\\tmp\\spark\\sql\\people.json")

    df.createOrReplaceTempView("people")
    context.sql("select * from people").show()
  }
}
