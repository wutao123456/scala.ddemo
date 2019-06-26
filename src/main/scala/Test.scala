/**
  * @author wutao 
  * @date 2019/6/26
  */
object Test {
  def main(args:Array[String]): Unit ={
    println("hello scala");

    //使用val定义的变量值是不可变的，相当于java里用final修饰的变量
    val i = 1
    //使用var定义的变量是可变的，在Scala中鼓励使用val
    var s = "hello"
    //Scala编译器会自动推断变量的类型，必要的时候可以指定类型
    //变量名在前，类型在后
    val str: String = "world"

    for(i <- 1 to 10){
      print(i+"\t")
    }

    println()

    val arr = Array("a","b","c")
    for(i <- arr){
      println(i)
    }

    for(i <- 1 to 3;j <- 1 to 3 if i!=j)
      print((10 * i + j) + " ")
    println()


    val v = for(i <- 1 to 10) yield  i * 10
    println(v)


    /**
      * 定义函数
      * 函数与方法的区别在于"函数可以作为参数传递"
      */
    val f2 =(x:Int,y:Int)=> x-y

    println(m2(f2))

    /**
      * 方法转换为函数
      */
    val ff = m2 _

  }

  /**
    * 定义方法
    * def为默认访问控制修饰符(public)
    * @param f
    * @return
    */
  def m2(f:(Int,Int)=>Int): Int ={
    f(2,6)
  }

}
