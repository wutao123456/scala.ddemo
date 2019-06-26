/**
  * @author wutao 
  * @date 2019/6/26
  * 偏函数
  */
object PartialFuncDemo {

  //PartialFunction[A, B]的一个实例，A代表参数类型，B代表返回类型，常用作输入模式匹配
  def fun1: PartialFunction[String,Int]={
    case "one" => 1
    case "two" => 2
    case "_" => -1
  }

  def fun2(num:String): Int = num match {
    case "one" => 1
    case "two" => 2
    case "_" => -1
  }

  def main(args: Array[String]): Unit = {
    println(fun1("one"))
    println(fun2("one"))

  }

}
