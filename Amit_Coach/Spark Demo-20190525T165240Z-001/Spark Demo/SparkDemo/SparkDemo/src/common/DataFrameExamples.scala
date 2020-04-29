package common

object Q2 {
  
  def main(args: Array[String]): Unit = {
    val customerDF = SparkUtils.sparkSession.read.option("header", "true").csv("customer.csv")
    val purchaseDF = SparkUtils.sparkSession.read.option("header", "true").csv("purchase.csv")
    customerDF.createOrReplaceTempView("customers")
    purchaseDF.createOrReplaceTempView("purchases")
    val joinDF = SparkUtils.sparkSession.sql(" select * from customers c join purchases p on c.customer_id =p.customer_id")
    joinDF.createOrReplaceTempView("CUST_PURCHASES")

    val customer_purchase_max_items = SparkUtils.sparkSession.sql("select p.customer_id as cust_id,count(*) as cnt from customers c join purchases p on c.customer_id =p.customer_id group by p.customer_id order by cnt desc limit 1")

    val cust_with_max_money = SparkUtils.sparkSession.sql(" select p.customer_id as cust_id,sum(sell_price) as amount from customers c join purchases p on c.customer_id =p.customer_id group by p.customer_id order by amount desc limit 1")

    val customers_by_max_sell_per_product = SparkUtils.sparkSession.sql("select customer_id,product_id,max(sell_price) from purchases group by product_id,customer_id")
    customers_by_max_sell_per_product.createOrReplaceTempView("CUST_BY_MAX_SELL_PER_PRODUCT")

    val locations_with_max_sell_per_product = SparkUtils.sparkSession.sql("select c.location from CUST_BY_MAX_SELL_PER_PRODUCT pur join customers c on pur.customer_id = c.customer_id")

    val product_with_min_sale = SparkUtils.sparkSession.sql("select product_id,sum(sell_price) as sell_per_product from purchases group by product_id order by sell_per_product limit 1")

    val product_with_min_unit_sold = SparkUtils.sparkSession.sql("select product_id,count(*) as cnt from purchases group by product_id order by cnt limit 1")
    
    //the locations in which sale of each product is maximum .
    locations_with_max_sell_per_product.show()
    
    //customer who has purchased max number of items
    customer_purchase_max_items.show()
    
    //customer who has spent maximum money
    cust_with_max_money.show()
    
    //product which has minimum sale in terms of money.
    product_with_min_sale.show()
    
    //product which has minimum sale in terms of number of unit sold.
    product_with_min_unit_sold.show()
    
  }
}