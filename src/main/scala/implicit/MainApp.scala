import scala.io.Source
import java.io.File

/**
  * @author wutao 
  * @date 2019/6/26
  */
object MainApp {

  def main(args: Array[String]): Unit = {
    val file = new File("E:\\a.txt");
    val richFile = new RichFile(file);
    val str = richFile.read();
    println(str)
  }

}

class RichFile(val file:File){
  def read():String = Source.fromFile(file.getPath).mkString
}
