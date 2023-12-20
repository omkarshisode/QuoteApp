package dista.learning.quoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Result
import kotlin.text.Typography.quote




class MainActivity : AppCompatActivity() {
    private lateinit var textView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

textView = findViewById(R.id.textViewQuote)
        // Getting the retrofit instance from the quote api
        val quoteApi = RetrofitHelper.getInstance().create(QuoteAPI::class.java)
        val call : Call<QuoteList> = quoteApi.getRandomQuotes()
        call.enqueue(object :Callback<QuoteList>{
            override fun onResponse(call: Call<QuoteList>, response: Response<QuoteList>) {
                if(response.isSuccessful){
                    val quote = response.body()
                    Log.d("Response", response.body().toString())
                    quote?.let {
                        val quoteText = "\"${it.content}\" - ${it.author}"
                        textView.text = quoteText
                    }
                }
            }

            override fun onFailure(call: Call<QuoteList>, t: Throwable) {
                Log.d("Fail", t.toString())
            }
        })

    }
}

