

package common
object Q1 {
  
	def main(args: Array[String]): Unit = {
	  import SparkUtils.sparkSession.implicits._
			val webLogsDS = SparkUtils.sparkSession.read.text("Q1_weblogs").as[String]

					val webLogsDF1 = webLogsDS.map( x => x.split(" - - ")(0))
					webLogsDF1.createOrReplaceTempView("LOGS")
					val topFiveIPs = SparkUtils.sparkSession.sql("select count(*) as cnt ,value from LOGS group by value order by cnt desc limit 5")
					topFiveIPs.show()
	}
  
}