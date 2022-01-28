package com.example.dmemes

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar

import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class MainActivity : AppCompatActivity() {
  lateinit  var memeimageView : ImageView

   var currentImageurl:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme()
    }
   private fun loadMeme()
    {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
         currentImageurl = "https://meme-api.herokuapp.com/gimme"

        // Request a string response from the provided URL.
        val JsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,currentImageurl,null,
            { response ->
                currentImageurl = response.getString("url")
                memeimageView = findViewById(R.id.memeimageView)
           Glide.with( this).load(currentImageurl).into(memeimageView)







            },
            {Response.ErrorListener {
               Toast.makeText(this,"Kuch to garbar hai DAYA",Toast.LENGTH_SHORT).show()
            }

            })

// Add the request to the RequestQueue.
        queue.add(JsonObjectRequest)
    }
    fun Share(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"AAO BETE MAUJ KARE\n$currentImageurl")
        val chooser = Intent.createChooser(intent,"DARKNESS BATNE SE BADHTA HAI!!!!")
        startActivity(chooser)

        Toast.makeText(this,"wait my boi",Toast.LENGTH_SHORT).show()



    }
    fun Next(view: View) {
     loadMeme()
        Toast.makeText(this,"Buddi",Toast.LENGTH_SHORT).show()

    }
}