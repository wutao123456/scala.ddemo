/**
  * @author wutao 
  * @date 2019/6/26
  * 主构造方法：
  * 1）与类名交织在一起
  * 2）主构造方法运行，导致类名后面的大括号里面的代码都会运行
  *
  * 辅助构造方法：
  * 1）必须名字叫this
  * 2) 必须以调用主构造方法或者是其他辅助构造方法开始。
  * 3）里面的属性不能写修饰符
  */
class Person (val name:String,val age:Int){

  println("hello scala")

  private var address = "世贸中心"

  //用this关键字定义辅助构造器
  def this(name:String,age:Int,address:String){
    this(name,age)
    println("执行辅助构造器")
    this.address = address
  }

}

object Person{
  def main(args: Array[String]): Unit = {
    var p = new Person("test",20)
  }
}
