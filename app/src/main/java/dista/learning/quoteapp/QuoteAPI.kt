package dista.learning.quoteapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteAPI {
    @GET("quotes/random")
     fun getRandomQuotes(): Call<QuoteList>

}






