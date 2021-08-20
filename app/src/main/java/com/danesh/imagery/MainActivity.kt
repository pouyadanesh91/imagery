package com.danesh.imagery

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.danesh.imagery.adapters.ImageAdapter
import com.danesh.imagery.remotedata.ApiInterface
import com.danesh.imagery.remotedata.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ImageAdapter.ClickListener {

    var disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        ImageAdapter.clickListener = this
        getImages()

        // Verify the action and get the query
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                doMySearch(query)
            }
        }


    }

    private fun doMySearch(query: String) {
        val str = query.replace(" ","+")
        getImages(str)
    }

    private fun getImages(query: String){
        val dispose = ApiService.apiCall(ApiInterface::class.java).getDashboardData(
            "23013811-c38b32f7c3d39ed89632f72d6",
            query,
            1,
            30
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.w("TAG", "this is list empty success")
                if (it.total > 0 && it.hits != null && it.hits!!.isNotEmpty()) {

                    image_thumb_list.setHasFixedSize(true)
                    image_thumb_list.layoutManager = GridLayoutManager(this, 2)
                    image_thumb_list.adapter = ImageAdapter(it.hits!!)
                }


            }, {
                it.printStackTrace()
            })

        disposable.add(dispose)
    }

    private fun getImages() {
        getImages("flower")
    }

    override fun onItemClick(position: Int, v: View, id: Int) {
        val intent = Intent(applicationContext, ImageDetailActivity::class.java)
        intent.putExtra("imageId", id)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the options menu from XML
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.app_bar_search).actionView as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            isIconifiedByDefault = false // Do not iconify the widget; expand it by default
        }

        return true
    }

}