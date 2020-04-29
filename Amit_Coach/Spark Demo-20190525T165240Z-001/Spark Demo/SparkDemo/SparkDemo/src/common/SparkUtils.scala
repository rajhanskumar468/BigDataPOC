package common

import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.spark.sql.catalyst.encoders.ExpressionEncoder
import org.apache.spark.sql.Encoder

object SparkUtils {
  
  lazy val sparkSession = SparkSession
  .builder()
  .config("spark.sql.warehouse.dir", "file:///C:/Cassandra/Simplilearn/Spark Extra Stuff/SparkDemo/SparkDemo/spark-warehouse")
  .appName("test")
  .master("local[*]")
  .getOrCreate()

  lazy val sparkContext = new SparkContext(master = "local[*]", appName="test", new SparkConf)
  lazy val sqlContext = new SQLContext(sparkContext)
    
}