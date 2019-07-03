package spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author wutao 
  * @date 2019/7/3
  */
object SparkWordCountWithScala {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf();
    /**
      * 如果这个参数不设置，默认认为你运行的是集群模式
      * 如果设置成local代表运行的是local模式
      */
    conf.setMaster("local")

    /**
      * 设置任务名
      */
    conf.setAppName("wordcount")

    //创建SparkCode程序入口
    val sc = new SparkContext(conf)
    //读取文件,生成RDD
    var file:RDD[String] = sc.textFile("E:\\a.txt")

    //把每一行按照空格分割
    var word:RDD[String] = file.flatMap(_.split(" "))

    //让每个单词出现一次
    var wordOne:RDD[(String,Int)] = word.map((_,1))

    //单词计数
    var wordCount:RDD[(String,Int)] = wordOne.reduceByKey(_+_)

    //按单词出现的次数降序排序
    var sordRDD:RDD[(String,Int)] = wordCount.sortBy(t=>t._2,false)

    //保存结果
    sordRDD.saveAsTextFile("E:\\result")

    sc.stop()
  }

}
