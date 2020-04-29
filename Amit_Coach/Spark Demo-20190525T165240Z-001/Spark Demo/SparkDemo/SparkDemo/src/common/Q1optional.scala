package common

object Q1optional {
  def main(args: Array[String]): Unit = {
    val soccerEventDF = SparkUtils.sparkSession.read.option("header", "true").csv("soccer_event.csv")
    import scala.collection.Map
    import org.apache.spark.sql.{ Row }
    import SparkUtils.sparkSession.implicits._
    val mapRDD = soccerEventDF.rdd.map { case Row(x1: String, x2: String, x3: String, x4: String, x5: String) => (x1 + ":" + x2 + ":" + x3, Map(x4 -> x5.toInt)) }
    case class PlayerStats(firstName: String, lastName: String, country: String, matchAndScore: Map[String, Int])
    val reducedRDD = mapRDD.reduceByKey((a, b) => a ++ b).map(t => PlayerStats(t._1.split(":")(0), t._1.split(":")(1), t._1.split(":")(2), t._2))
    reducedRDD.foreach(println)
  }
}