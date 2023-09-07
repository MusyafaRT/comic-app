package com.example.assignmentproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.assignmentproject.databinding.ActivityDetailBinding
import java.io.Serializable

class Detail : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailBinding
    var comic:Comic? = null
    companion object {
        const val EXTRA_DATA = "extra_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        if(intent.hasExtra(EXTRA_DATA)) {
            comic = intent.getParcelableExtra<Comic>(EXTRA_DATA)
        }
        if (comic != null) {
            binding?.tvItemName?.text = comic!!.name
            binding?.tvItemDescription?.text = comic!!.description
            binding?.tvItemAuthor?.text = comic!!.author
            binding?.tvItemArtist?.text = comic!!.artist
            binding?.tvItemGenres?.text = comic!!.genres
            binding?.tvItemReview?.text = comic!!.review
            binding?.tvItemRating?.text = comic!!.rating
            Glide.with(this)
              .load(comic!!.photo)
              .into(binding.imgItemPhoto)
        }

        binding.btnShare.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        val shareIntent = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(EXTRA_DATA, comic)
            putExtra("extra_title", comic?.name)
        }, null)
        startActivity(shareIntent)
    }
}