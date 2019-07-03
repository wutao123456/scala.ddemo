import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

/**
 * @author wutao
 * @date 2019/7/3
 */
public class SparkWordCountWithJava8 {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setMaster("local");
        conf.setAppName("wordcount");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> fileRdd = sc.textFile("E:\\a.txt");
        JavaRDD<String> wordRdd = fileRdd.flatMap(line -> Arrays.asList(line.split(" ")).iterator());
        JavaPairRDD<String,Integer> wordOneRdd = wordRdd.mapToPair(word -> new Tuple2<>(word,1));
        JavaPairRDD<String,Integer> wordCountRdd = wordOneRdd.reduceByKey((x,y) -> x + y);
        JavaPairRDD<Integer,String> count2Rdd = wordCountRdd.mapToPair(tuple -> new Tuple2<>(tuple._2,tuple._1));
        JavaPairRDD<Integer,String> sortRdd = count2Rdd.sortByKey(false);
        JavaPairRDD<String,Integer> resultRdd = sortRdd.mapToPair(tuple -> new Tuple2<>(tuple._2,tuple._1));
        resultRdd.saveAsTextFile("E:\\result");
    }
}
