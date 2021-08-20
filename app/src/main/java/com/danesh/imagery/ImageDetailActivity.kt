package com.danesh.imagery

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.danesh.imagery.remotedata.ApiInterface
import com.danesh.imagery.remotedata.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_image_detail.*

class ImageDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)
        if (intent.extras != null) {
            val imageId = intent.extras!!["imageId"] as Int
            getImageDetails(imageId)
        }
    }

    var disposable = CompositeDisposable()

    private fun getImageDetails(imageId: Int) {
        val dispose = ApiService.apiCall(ApiInterface::class.java).getImageById(
            "23013811-c38b32f7c3d39ed89632f72d6",
            imageId
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.w("TAG", "this is list empty success")
                if (it.total > 0 && it.hits != null && it.hits!!.isNotEmpty()) {
                    Glide.with(applicationContext)
                        .load(it.hits!![0].webformatURL)
                        .into(img_mainImage)

                    txt_imageDetails.text =
                        "${it.hits!![0].type} type with views: ${it.hits!![0].views}"
                }


            }, {
                it.printStackTrace()
            })

        disposable.add(dispose)
    }
}