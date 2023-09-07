package com.example.assignmentproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentproject.databinding.ActivityMainBinding
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import kotlinx.coroutines.delay


class MainActivity : AppCompatActivity() {
    private val list = ArrayList<Comic>()
    private lateinit var binding: ActivityMainBinding
    var contentLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        startContent()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rvComic.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecyclerList()

        setUpSplashScreen(splashScreen)
    }

    private fun startContent() {
     contentLoaded = true
    }

    private fun setUpSplashScreen(splashScreen: SplashScreen) {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (contentLoaded) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else false
                }
            }
        )

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_list -> {
                binding.rvComic.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                binding.rvComic.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.about -> {
                val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showRecyclerList() {
        binding.rvComic.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListComicAdapter(list)
        binding.rvComic.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListComicAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Comic) {
                val detailIntent = Intent(this@MainActivity, Detail::class.java)
                detailIntent.putExtra(EXTRA_DATA, data)
                startActivity(detailIntent)
            }
        })
    }

    private fun getListHeroes(): ArrayList<Comic> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataAuthor = resources.getStringArray(R.array.data_author)
        val dataArtist = resources.getStringArray(R.array.data_artist)
        val dataGenres = resources.getStringArray(R.array.data_genres)
        val dataReview = resources.getStringArray(R.array.data_review)
        val dataRating = resources.getStringArray(R.array.data_rating)
        val listHero = ArrayList<Comic>()
        for (i in dataName.indices) {
            val hero = Comic(dataName[i], dataDescription[i], dataPhoto[i], dataAuthor[i], dataArtist[i], dataGenres[i], dataReview[i], dataRating[i])
            listHero.add(hero)
        }
        return listHero
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

}