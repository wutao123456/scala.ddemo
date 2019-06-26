import scala.util.Random

/**
  * @author wutao 
  * @date 2019/6/26
  */
class ClassDemo {

  val name = "test"

  var age = 20

  var id = 666

  private var address = "三里屯"

}

object ClassTest{
  def main(args: Array[String]): Unit = {
    val demo = new ClassDemo
    println(demo.name)


    /**
      * 模式匹配match类似于java switch
      */
    val arr = Array("hadoop","hbase","spark")
    val name = arr(Random.nextInt(arr.length))
    name match {
      case "hadoop" => println("哈杜普")
      case "hbase" => println("H贝斯")
      case _ => println("what is wrong")
    }

  }
}
