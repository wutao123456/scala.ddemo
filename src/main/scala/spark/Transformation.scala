package spark

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

/**
  * @author wutao 
  * @date 2019/7/4
  */
object Transformation {

  private val conf:SparkConf = new SparkConf().setAppName("TestTransformation").setMaster("local")

  private val sc:SparkContext = new SparkContext(conf)

  def main(args: Array[String]): Unit = {

//    map()
//    mapPartitions()
//    mapPartitionsWithIndex()

//    reduce()
//    reduceByKey()
//    union()
//    groupByKey()
//    join()
//    sample()
//    cartesian()
//    filter()
//    distinct()
//    intersection()
//    coalesce()
//    cogroup()
//    sortByKey()
    aggregateByKey()
  }

  def map(): Unit ={
    val list = List("张无忌", "赵敏", "周芷若")
    val listRdd = sc.parallelize(list)
    val nameRdd = listRdd.map(name => "hello "+name)
    nameRdd.foreach(name =>println(name))
  }

  //传入整个RDD
  def mapPartitions(): Unit ={
    val list = List(1,2,3,4,5,6,7,8)
    val listRDD = sc.parallelize(list)
    listRDD.mapPartitions(iterator => {
      val newList:ListBuffer[String] = ListBuffer()
      while (iterator.hasNext){
        newList.append("hello "+iterator.next())
      }
      newList.iterator
    }).foreach(v => println(v))
  }

  def mapPartitionsWithIndex(): Unit ={
    val list = List(1,2,3,4,5,6,7,8)
    val listRDD = sc.parallelize(list)
    listRDD.mapPartitionsWithIndex((index,iterator) => {
      val newList:ListBuffer[String] = ListBuffer()
      while (iterator.hasNext){
        newList.append(index +"_"+iterator.next())
      }
      newList.iterator
    },true).foreach(println(_))
  }

  def reduce(): Unit ={
    val list = List(1,2,3,4,5,6)
    val listRdd = sc.parallelize(list)
    val result = listRdd.reduce((x,y) => x+y)
    println(result)
  }

  // https://blog.csdn.net/weixin_41804049/article/details/80373741
  def reduceByKey(): Unit ={
    val list = List(("武当", 99), ("少林", 97), ("武当", 89), ("少林", 77))
    val mapRDD = sc.parallelize(list)

    val resultRDD = mapRDD.reduceByKey(_+_)
    resultRDD.foreach(tuple => println("门派："+tuple._1+"->" + tuple._2))
  }

  def union(): Unit ={
    val list1 = List(1,2,3)
    val list2 = List(4,5,6)
    val list1RDD = sc.parallelize(list1)
    val list2RDD = sc.parallelize(list2)
    list1RDD.union(list2RDD).foreach(println(_))
  }

  def groupByKey(): Unit ={
    val list = List(("武当", "张三丰"), ("峨眉", "灭绝师太"), ("武当", "宋青书"), ("峨眉", "周芷若"))
    val listRDD = sc.parallelize(list)
    val groupRDD = listRDD.groupByKey()
    groupRDD.foreach(t => {
      val menpai = t._1
      val iterator = t._2.iterator
      var people = ""
      while (iterator.hasNext)
        people = people + iterator.next() +" "
        println("门派:" + menpai + "人员:" + people)

    })
  }

  def join(): Unit ={
    val list1 = List((1, "东方不败"), (2, "令狐冲"), (3, "林平之"))
    val list2 = List((1, 99), (2, 98), (3, 97))
    val list1RDD = sc.parallelize(list1)
    val list2RDD = sc.parallelize(list2)

    val joinRDD = list1RDD.join(list2RDD)
    joinRDD.foreach(t => println("学号："+t._1+",姓名："+t._2._1+",成绩："+t._2._2))
  }

  def sample(): Unit ={
    val list = 1 to 100
    val listRDD = sc.parallelize(list)

//    1、withReplacement：元素可以多次抽样(在抽样时替换)
//    2、fraction：期望样本的大小作为RDD大小的一部分，
//    当withReplacement=false时：选择每个元素的概率;分数一定是[0,1] ；
//    当withReplacement=true时：选择每个元素的期望次数; 分数必须大于等于0。
//    3、seed：随机数生成器的种子
    listRDD.sample(false,0.1,0).foreach(num => print(num + " "))
  }

  //笛卡尔积
  def cartesian(): Unit ={
    val list1 = List("A","B")
    val list2 = List(1,2,3)
    val list1RDD = sc.parallelize(list1)
    val list2RDD = sc.parallelize(list2)
    list1RDD.cartesian(list2RDD).foreach(t => println(t._1 +"->"+t._2))
  }

  def filter(): Unit ={
    val list = 1 to 10
    val listRDD = sc.parallelize(list)
    listRDD.filter(num => num % 2 == 0).foreach(num => print(num + " "))
  }

  def distinct(): Unit ={
    val list = List(1,1,2,2,3,3,4,5)
    val listRDD = sc.parallelize(list)
    listRDD.distinct().foreach(println(_))
  }

  def intersection(): Unit ={
    val list1 = List(1,2,3,4)
    val list2 = List(3,4,5,6)
    val list1RDD = sc.parallelize(list1)
    val list2RDD = sc.parallelize(list2)
    list1RDD.intersection(list2RDD).foreach(println(_))
  }

  //合并分区
  def coalesce(): Unit ={
    val list = 1 to 9
    val listRDD = sc.parallelize(list,3)
    listRDD.coalesce(1).foreach(println(_))
  }

  def cogroup(): Unit ={
    val list1 = List((1, "www"), (2, "bbs"))
    val list2 = List((1, "cnblog"), (2, "cnblog"), (3, "very"))
    val list3 = List((1, "com"), (2, "com"), (3, "good"))

    val list1RDD = sc.parallelize(list1)
    val list2RDD = sc.parallelize(list2)
    val list3RDD = sc.parallelize(list3)
    list1RDD.cogroup(list2RDD,list3RDD).foreach(t => println(t._1+" "+t._2._1+" "+t._2._2+" "+ t._2._3))
  }

  def sortByKey(): Unit ={
    val list = List((99, "张三丰"), (96, "东方不败"), (66, "林平之"), (98, "聂风"))
    //按key降序(默认升序)
    sc.parallelize(list).sortByKey(false).foreach(t => println(t._2 +"=>"+ t._1))
  }

  //先对每个分区进行数据合并,性能优于reduceByKey
  def aggregateByKey(): Unit ={
    val list = List("you,jump", "i,jump")
    sc.parallelize(list).flatMap(_.split(",")).map((_,1)).aggregateByKey(0)(_+_,_+_).foreach(t => println(t._1 +"=>"+ t._2))
  }

}
