package com.example.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var myadapter: myadapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView=findViewById(R.id.recyclerview)

        val retrofitBuilder= Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiinterface::class.java)

        val retrofitData=retrofitBuilder.getDta("eminem")

        retrofitData.enqueue(object : Callback<MyDataX?> {
            override fun onResponse(call: Call<MyDataX?>, response: Response<MyDataX?>) {
               //if the api call is a success then this method is executed
                val dataList=response.body()?.data!!
               // val textView=findViewById<TextView>(R.id.hellotext)
              //  textView.text=dataList.toString()

                myadapter= myadapter(this@MainActivity,dataList)
                myRecyclerView.adapter=myadapter
                myRecyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
                Log.d("TAG: onResponse", "onResponse: " + response.body())

            }

            override fun onFailure(call: Call<MyDataX?>, t: Throwable) {
                // if the api is a failure then this method is executed
                Log.d("TAG: onFailure", "onFailure: " + t.message)
            }
        })




    }
}


