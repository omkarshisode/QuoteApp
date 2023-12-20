package dista.learning.quoteapp

data class Result(
    val _id:Int,
    val author:String,
    val authorSlung:String,
    val content:String,
    val dateAdded:String,
    val dateModified:String,
    val length:Int,
    val tags:List<String>
)
