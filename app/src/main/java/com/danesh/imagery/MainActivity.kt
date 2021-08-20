package com.danesh.imagery

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.danesh.imagery.adapters.ImageAdapter
import com.danesh.imagery.remotedata.ApiInterface
import com.danesh.imagery.remotedata.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.HttpException

class MainActivity : AppCompatActivity(), ImageAdapter.ClickListener {

    var disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getImages()

    }

    private fun getImages() {
        val dispose = ApiService.apiCall(ApiInterface::class.java).getDashboardData(
            "23013811-c38b32f7c3d39ed89632f72d6",
            "flower",
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

    override fun onItemClick(position: Int, v: View, id: Int) {
        val intent = Intent(applicationContext, ImageDetailActivity::class.java)
        intent.putExtra("imageId", id)
        startActivity(intent)
    }
}