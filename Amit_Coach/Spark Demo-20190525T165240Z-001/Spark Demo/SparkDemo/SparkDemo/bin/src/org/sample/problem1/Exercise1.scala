package org.sample.problem1

import org.apache.spark.SparkContext;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.Row;


object Exercise1 {

  def main(args: Array[String]) {
    
    val sparkConf = new SparkConf().setAppName("Dynamic").setMaster("local")
    
    val sc = new SparkContext(sparkConf)
    
    val sql = new org.apache.spark.sql.SQLContext(sc)
    val schema:String = "Name Age"
    
    val myrdd = sc.textFile("D:\\People.txt")
    
    val fields = StructType(schema.split("\\s").
                            map(x => StructField(x,StringType,true)))
                            
     val rowRDD = myrdd.map(x => x.split(",")).map(x => Row(x(0),x(1).trim()))
    

    
    
    
    val df = sql.createDataFrame(rowRDD, fields)
    
    
    
    
    
    
    
  }

}