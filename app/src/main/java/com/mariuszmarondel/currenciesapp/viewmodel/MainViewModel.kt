package com.mariuszmarondel.currenciesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mariuszmarondel.currenciesapp.model.DayCurrenciesDataProvider
import com.mariuszmarondel.currenciesapp.screen.main.recyclerview.Item
import java.util.*

class MainViewModel : ViewModel() {
    private val items = MutableLiveData<List<Item>>()
    private val dataProvider = DayCurrenciesDataProvider()
    private var lastDate = Date()

    init {
        updateData()
    }

    fun updateData() {
        dataProvider.updateCurrencies(lastDate)
            .subscribe({
                dataProvider.addNewCurrencies(it)
                items.value = dataProvider.getDayCurrenciesItems()
                setOneDayBefore()
            }, {

            })
    }

    fun observeData(): LiveData<List<Item>> = items

    fun getCurrencyItem(position: Int) = dataProvider.getCurrencyItem(position)


    private fun setOneDayBefore() {
        val calendar = Calendar.getInstance()
        calendar.time = lastDate
        calendar.add(Calendar.DATE, -1)
        lastDate = calendar.time
    }
}