package common

object Q3 {
  def main(args: Array[String]): Unit = {
    var countme = 0
    var rdd = SparkUtils.sparkContext.parallelize(1 to 10)
    rdd.foreach(x => countme += x)
    println("Count Me: " + countme)
  }
}
































/*EXPLANATION
    --> in Cluster mode Each executor JVM will have its own copy of "countme"
    --> variable and change done on executor will not reflect in driver JVM
    --> therefore this code will work fine in local mode, but for cluster mode the "countme" variable will not serve its purpose
*/