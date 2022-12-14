package com.mariuszmarondel.currenciesapp.model

import com.mariuszmarondel.currenciesapp.screen.main.recyclerview.Item
import com.mariuszmarondel.currenciesapp.repository.RemoteRepository
import io.reactivex.rxjava3.core.Observable
import java.text.SimpleDateFormat
import java.util.*

class DayCurrenciesDataProvider {
    private val dayCurrencies = arrayListOf<DayCurrenciesData>()
    private var appDateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    private var apiDateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun getDayCurrenciesItems(): List<Item> = dayCurrencies.flatMap { it ->
        (it.rates.map {
            Item(
                "- " + it.key + " : " + it.value.toString(),
                true
            )
        } as ArrayList).apply {
            add(
                0,
                Item(
                    "  Dzień " + appDateFormatter.format(
                        apiDateFormatter.parse(it.date) ?: ""
                    ) + ":", false
                )
            )
        }
    }

    fun updateCurrencies(date: Date): Observable<DayCurrenciesData> =
        RemoteRepository.getCurrenciesByDate(apiDateFormatter.format(date))

    fun addNewCurrencies(currencies: DayCurrenciesData) {
        dayCurrencies.add(currencies)
    }

    fun getCurrencyItem(position: Int): CurrencyData = dayCurrencies.flatMap { day ->
        (day.rates.map {
            CurrencyData(it.key, it.value, appDateFormatter.format(apiDateFormatter.parse(day.date) ?: ""))
        } as ArrayList).apply {
            add(0, CurrencyData("", 0.0, ""))
        }
    }[position]
}