package com.mariuszmarondel.currenciesapp.model

import com.squareup.moshi.Json

data class DayCurrenciesData(
    @Json(name = "date") val date: String,
    @Json(name = "rates") val rates: Map<String, Double>
)
