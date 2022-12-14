package com.mariuszmarondel.currenciesapp.model

data class CurrencyData(
    val name: String,
    val conversionValue: Double,
    val date: String
) : java.io.Serializable