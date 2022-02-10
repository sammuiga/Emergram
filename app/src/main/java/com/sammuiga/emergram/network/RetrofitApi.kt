package com.sammuiga.emergram.network

import com.sammuiga.emergram.googlePlaceModel.GoogleResponseModel
import com.sammuiga.emergram.googlePlaceModel.directionPlaceModel.DirectionResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitApi {

    @GET
    suspend fun getNearByPlaces(@Url url: String): Response<GoogleResponseModel>

    @GET
    suspend fun getDirection(@Url url: String): Response<DirectionResponseModel>
}