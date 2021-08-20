package com.danesh.imagery.remotedata

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object ApiService {

    fun <T : Any?> apiCall(serviceClass: Class<T>) = Retrofit.Builder()
        .baseUrl("https://pixabay.com/")
        .addConverterFactory(ApiWorker.gsonConverter)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(ApiWorker.client)
        //Get a usable Retrofit object by calling .build()//
        .build()
        .create(serviceClass)
}