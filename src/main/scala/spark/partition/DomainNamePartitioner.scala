package spark.partition

import java.net.URL

import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}



/**
  * @author wutao 
  * @date 2019/7/9
  */
object DomainNamePartitioner {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("pa").setMaster("local")
    val sc = new SparkContext(conf)
    val urlRDD = sc.makeRDD(Seq(("http://baidu.com/test", 2),
      ("http://baidu.com/index", 2), ("http://ali.com", 3), ("http://baidu.com/tmmmm", 4),
      ("http://baidu.com/test", 4)))
    val hashPartitionedRDD = urlRDD.partitionBy(new HashPartitioner(2))
    hashPartitionedRDD.glom().collect()

    val myPartitionedRDD = urlRDD.partitionBy(new MyPartitioner(2))
    myPartitionedRDD.glom().collect()
  }

}

class MyPartitioner(val numPars:Int) extends Partitioner{
  override def numPartitions: Int = numPars

  override def getPartition(key: Any): Int = {
    val domain = new URL(key.toString).getHost
    val code = domain.hashCode % numPars
    if(code < 0){
      code + numPars
    }else{
      code
    }
  }
}
