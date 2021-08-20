package com.danesh.imagery.remotedata

import com.danesh.imagery.models.ImageResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/")
    fun getDashboardData(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Observable<ImageResponseModel>

    @GET("api/")
    fun getImageById(
        @Query("key") key: String,
        @Query("id") imageId: Int
    ): Observable<ImageResponseModel>

}