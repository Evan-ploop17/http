 package com.example.http

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

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
                //Log.d("reference", downloadData("https://www.google.com/"))
                DownloadURL(this ).execute("http://google.com.com")
            } else{
                Toast.makeText(this, "Asegurate de que haya red", Toast.LENGTH_LONG).show()
            }
        }

    }

     override fun downloadCompleted(result: String) {
         Log.d("Descarga completa", result)
     }

     // Esto lo comentamos porque ya se esta haciendo en su propio hilo en la clase DownloadURL

     /*@Throws(IOException::class)
     private fun downloadData(url: String): String{

         // Dos lineas que permiten la ejecucion de slicitudes HTTP en el hilo pincipal, No es recomendable hacerlo
         val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
         StrictMode.setThreadPolicy(policy)

         var inputStream: InputStream? = null
         try {
             val url = URL(url)
             val conn = url.openConnection() as HttpURLConnection
             conn.requestMethod = "GET"
             conn.connect()

             inputStream = conn.inputStream
             return inputStream.bufferedReader().use {
                 it.readText()
             }
         }finally {
             inputStream?.close()
         }
     }*/

}