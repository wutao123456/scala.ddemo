package generic

/**
  * @author wutao 
  * @date 2019/6/26
  */
class GenericTest1[T<:Comparable[T]] {
  def choose(one:T,two:T): T ={
    if(one.compareTo(two) > 0) one else two
  }

}

class Boy(val name:String,val age:Int)extends Comparable[Boy]{
  override def compareTo(o: Boy): Int = {
    this.age - o.age
  }
}

object GenericTestOne{
  def main(args: Array[String]): Unit = {
    val gt = new GenericTest1[Boy];
    val hangbo = new Boy("huangbo",12)
    val xuzheng = new Boy("xuzheng",13)
    val boy = gt.choose(hangbo,xuzheng)
    println(boy.name)


  }
}

