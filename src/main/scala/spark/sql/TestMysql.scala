package spark.sql

import java.util.Properties

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author wutao 
  * @date 2019/7/10
  */
object TestMysql {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("TestMysql").setMaster("local")
    val sc = new SparkContext(conf)

    val sqlContext = SparkSession.builder().config(conf).getOrCreate()
//    read(sqlContext)
    write(sc,sqlContext)
  }

  def read(sqlContext:SparkSession): Unit ={
    val url = "jdbc:mysql://192.168.2.213:3306/test"
    val table = "company"
    val properties = new Properties()
    properties.setProperty("user","bjdlh")
    properties.setProperty("password","dlh2014")
    val df = sqlContext.read.jdbc(url,table,properties)
    df.createOrReplaceTempView("company")
    sqlContext.sql("select * from company limit 10").show()
  }

  def write(sc:SparkContext,context:SparkSession): Unit ={
    val personRdd = sc.parallelize(Array("1 test1 20","2 test2 21","3 test3 22").map(_.split(" ")))

    val rowRdd = personRdd.map(p => Row(p(0).toInt,p(1).trim,p(2).toInt))
    val schema = StructType(
      List(
        StructField("id",IntegerType,true),
        StructField("name",StringType,true),
        StructField("age",IntegerType,true)
      )
    )

    val df = context.createDataFrame(rowRdd,schema)
    val url = "jdbc:mysql://192.168.2.213:3306/test"
    val table = "person"
    val properties = new Properties()
    properties.setProperty("user","bjdlh")
    properties.setProperty("password","dlh2014")
    df.write.mode("append").jdbc(url,table,properties)
    sc.stop()
  }

}
