package com.mariuszmarondel.currenciesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mariuszmarondel.currenciesapp.model.CurrencyData

class CurrencyViewModel: ViewModel() {
    private val item = MutableLiveData<CurrencyData>()

    fun updateData(item: CurrencyData) {
        this.item.value = item
    }

    fun observeData(): LiveData<CurrencyData> = item
}