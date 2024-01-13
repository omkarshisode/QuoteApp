package dista.learning.quoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Result
import kotlin.text.Typography.quote


class MainActivity : AppCompatActivity() {
    private lateinit var quote :TextView
    private lateinit var author :TextView
    private lateinit var getQuote :Button
    private lateinit var categorySpinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

         quote = findViewById(R.id.tvQuote)
         author = findViewById(R.id.tvAuthor)
         getQuote = findViewById(R.id.btnGetQuote)
        categorySpinner = findViewById(R.id.categorySpinner)
        var selectedCategory = "happiness"

        fetchQuote(selectedCategory)
        val tags = resources.getStringArray(R.array.categories_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,tags)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        categorySpinner.adapter = adapter

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedCategory  = tags[p2]
                fetchQuote(selectedCategory)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
               selectedCategory ="happiness"
            }

        }
        getQuote.setOnClickListener {
            fetchQuote(selectedCategory)
        }
    }

    private fun fetchQuote(selectedCategory:String){
        val quoteData = RetrofitHelper.retrofit.getRandomQuotes(selectedCategory)
        quoteData.enqueue(object : Callback<Quote?> {
            override fun onResponse(call: Call<Quote?>, response: Response<Quote?>) {
                val data =response.body()
                quote.text = data?.content
                author.text = "-${data?.author}"
            }

            override fun onFailure(call: Call<Quote?>, t: Throwable) {
                quote.text = t.toString()
                author.text = t.toString()
            }
        })
    }

}

