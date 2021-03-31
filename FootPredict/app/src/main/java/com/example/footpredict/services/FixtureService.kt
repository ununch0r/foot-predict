package com.example.footpredict.services

import com.example.footpredict.data.ApiResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface FixtureService {

    @GET("fixtures/league/{id}/next/10")
    fun getNextFixtures (@Path("id") id: Int?) : Call<ApiResponse>
}