import scala.collection.immutable.HashSet
import scala.collection.mutable.{ArrayBuffer, ListBuffer, Map}

/**
  * @author wutao 
  * @date 2019/6/26
  */
object TestType {

  def main(args: Array[String]): Unit ={

    val arr1 = new Array[Int](8);

    println("arr1 "+arr1)

    println("arr1 toBuffer "+arr1.toBuffer)

    val arr2 = Array[Int](10)

    println("arr2 toBuffer "+arr2.toBuffer)

    val arr3 = Array("a","b","c")
    println(arr3(2))


    /**
      * 定义一个变长数组
      */
    var ab = ArrayBuffer[Int]()
    ab += 1
    ab += (2,3,4,5)
    ab ++= Array(6,7)
    ab ++= ArrayBuffer(8,9)

    /**
      * 在位置0处插入元素(一个或多个)
      */
    ab.insert(0,-1,199)

    /**
      * 在位置1处删除元素
      */
    ab.remove(1,1)

    println(ab)

    for(i <- ab)
      print(i + "\t")

    println()
    println("------------until----------")
    for(i <- (-10 until ab.length).reverse)
      print(i + "\t")


    println()
    println("数组求和: "+ab.sum)
    println("数组最大值: "+ab.max)
    println("数组排序: "+ab.sorted)

    /**
      * 映射
      */
    val map = Map("zs"->100,"ls"->70,"ww"->80)
    //获取映射元素
    map("ls") = 120
//    注意：在Scala中，有两种Map，
//    一个是immutable包下的Map，该Map中的内容不可变；
//    另一个是mutable包下的Map，该Map中的内容可变 （val var）
    println(map("ls"))
    println(map.getOrElse("zl",0))


    /**
      * 元组
      */
    val t = ("hadoop",3.14,500)
    //获取元组数据
    println(t._1)

    val arr = Array(("test1",50),("test2",3.14))
    println(arr.toMap)

    val scores = Array(10,20,30)
    val names = Array("xl","wt","test")
    println(names.zip(scores).toMap)


    //--------集合---------------

//    4种操作符的区别和联系
//    (1) :: 该方法被称为cons，意为构造，向队列的头部追加数据，创造新的列表。用法为 x::list,其中x为加入到头部的元素，无论x是列表与否，它都只将成为新生成列表的第一个元素，也就是说新生成的列表长度为list的长度＋1(btw, x::list等价于list.::(x))
//    (2) :+和+: 两者的区别在于:+方法用于在尾部追加元素，+:方法用于在头部追加元素，和::很类似，但是::可以用于pattern match ，而+:则不行. 关于+:和:+,只要记住冒号永远靠近集合类型就OK了。
//    (3) ++ 该方法用于连接两个集合，list1++list2
//    (4) ::: 该方法只能用于连接两个List类型的集合
    val list1 = List(1,2,3)
    //将0插入到集合list1的前面生成一个新的list
    val list2 = 0::list1
    val list3 = list1.::(0)
    val list4 = 0+:list1
    val list5 = list1.+:(0)
    println("list1="+list1)
    println("list2="+list2)
    println("list3="+list3)
    println("list4="+list4)
    println("list5="+list5)

    val list6 = list1 :+ 3
    println("list6="+list6)

    val list0 = List(7,8,9)
    val list7 = list0 ++ list1
    println("list7="+list7)

    val list8 = list0 ++: list1
    val list9 = list1 ++: list0
    println("list8="+list8)
    println("list9="+list9)

    /**
      * 可变List
      */
    val list10 = ListBuffer[Int](1,2,3)
    list10 += 6
    list10.append(7)
    println("list10="+list10)
    val list11 = list10 :+ 11
    println("list11="+list11)

    /**
      * 不可变Set
      */
    val set1 = new HashSet[Int]()
    println("set1="+set1)

  }

}
