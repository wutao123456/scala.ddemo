/**
  * @author wutao 
  * @date 2019/6/26
  */
trait Dao {

  def add(o:Any):Boolean

  def delete(id:String):Boolean

  def update(o:Any):Int

  def select(id:String):List[Any]

}

class MysqlDao extends Dao{
  override def add(o: Any): Boolean = true

  override def delete(id: String): Boolean = true

  override def update(o: Any): Int = 1

  override def select(id: String): List[Any] = List(1,2,3)
}
