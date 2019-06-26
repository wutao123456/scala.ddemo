/**
  * @author wutao 
  * @date 2019/6/26
  * 柯里化
  */
object CurryDemo {

  def sum(x:Int)(y:Int)=x+y

  def main(args: Array[String]): Unit = {
    val f = sum _
    val f1 = f(1)
    val f2 = f1(2)
    println(f2)
  }

}
