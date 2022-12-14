package com.mariuszmarondel.currenciesapp.repository

import com.mariuszmarondel.currenciesapp.model.DayCurrenciesData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object RemoteRepository {

    fun getCurrenciesByDate(date: String): Observable<DayCurrenciesData> =
        ApiClient.apiService.getCurrenciesByDate(date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}