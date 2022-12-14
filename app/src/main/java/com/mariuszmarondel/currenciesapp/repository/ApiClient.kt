package com.mariuszmarondel.currenciesapp.repository

import com.mariuszmarondel.currenciesapp.model.DayCurrenciesData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

object ApiClient {

    private val URL = "https://api.apilayer.com/fixer/"

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

interface ApiService {
    @Headers("apikey: BZ4FZx1ARuTYC4DbvM5ABmvmUsQJy1Iz")
    @GET("{date}")
    fun getCurrenciesByDate(@Path("date") date: String): Observable<DayCurrenciesData>
}