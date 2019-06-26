import scala.collection.mutable

/**
  * @author wutao 
  * @date 2019/6/26
  */
object TestSet {

  def main(args: Array[String]): Unit = {
    /**
      * 可变Set
      */
    val set = new mutable.HashSet[Int]()
    set.add(1)
    set.add(3)
    println("set="+set)

    /**
      * 删除元素1(已存在的元素)
      */
    set.remove(1)
    println("set="+set)

    set ++= Set(5,6,7)
    println("set="+set)

    /**
      * 删除元素6(已存在的元素)
      */
    set -= 6
    println("set="+set)
  }

}
