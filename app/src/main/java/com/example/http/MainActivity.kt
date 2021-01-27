 package com.example.http

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.OkHttpClient
import java.io.IOException
import java.lang.Exception

 class MainActivity : AppCompatActivity(), CompletedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnValidarRed.setOnClickListener{
            if(Network.thereIsNetwork(this)){
                Toast.makeText(this, "Hay red", Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(this, "Asegurate de que haya red", Toast.LENGTH_LONG).show()
            }
        }

        btnSolicitudHttp.setOnClickListener{
            if(Network.thereIsNetwork(this)){
                DownloadURL(this ).execute("http://google.com.com")
            } else{
                Toast.makeText(this, "Asegurate de que haya red", Toast.LENGTH_LONG).show()
            }
        }

        btnVolley.setOnClickListener {
            if(Network.thereIsNetwork(this)){
                requestHTTPVolley("http://google.com.com")
            } else{
                Toast.makeText(this, "Asegurate de que haya red", Toast.LENGTH_LONG).show()
            }
        }

        btnOkHTTP.setOnClickListener {
            if(Network.thereIsNetwork(this)){
                requestOkHTTP("http://google.com.com")
            } else{
                Toast.makeText(this, "Asegurate de que haya red", Toast.LENGTH_LONG).show()
            }
        }

    }

     override fun downloadCompleted(result: String) {
         Log.d("Descarga completa", result)
     }

     // Method to Volley. Avoid write more code
     private fun requestHTTPVolley(url:String){

         val queue = com.android.volley.toolbox.Volley.newRequestQueue(this)
         val request = StringRequest(Request.Method.GET, url , Response.Listener<String>{
             response ->
             try{
                Log.v("solicitudHTTPVolley", response)
             }catch (e: Exception){

             }
         }, Response.ErrorListener {  })
         queue.add(request)
     }

     // Method to OkHTTP
     private fun requestOkHTTP(url: String){
         val customer = OkHttpClient()
         val request = okhttp3.Request.Builder().url(url).build()
         customer.newCall(request).enqueue(object: okhttp3.Callback{
             override fun onFailure(call: Call, e: IOException) {
                // En caso de que falle la descarga
             }

             override fun onResponse(call: Call, response: okhttp3.Response) {
                 val result = response.body()?.string()
                 this@MainActivity.runOnUiThread{
                    try {
                        Log.v("request", result!!)
                    }catch(e: Exception){

                    }
                 }
             }
         })

     }
}