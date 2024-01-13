package dista.learning.quoteapp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface QuoteAPI {
    @GET("random")
     fun getRandomQuotes(@Query("tags")category:String): Call<Quote>

}

object RetrofitHelper {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit:QuoteAPI = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.quotable.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(QuoteAPI::class.java)

}






