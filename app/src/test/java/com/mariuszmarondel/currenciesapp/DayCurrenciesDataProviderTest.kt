package com.mariuszmarondel.currenciesapp

import com.mariuszmarondel.currenciesapp.model.CurrencyData
import com.mariuszmarondel.currenciesapp.model.DayCurrenciesData
import com.mariuszmarondel.currenciesapp.model.DayCurrenciesDataProvider
import com.mariuszmarondel.currenciesapp.screen.main.recyclerview.Item
import org.junit.Before
import org.junit.Test

class DayCurrenciesDataProviderTest {

    private lateinit var dataProvider: DayCurrenciesDataProvider

    @Before
    fun setUp() {
        dataProvider = DayCurrenciesDataProvider()
        val inputDataSet = mutableListOf<DayCurrenciesData>()
        val inputDataCurrenciesSet = mutableMapOf<String, Double>()
        inputDataCurrenciesSet["PLN"] = 10.5
        inputDataCurrenciesSet["USD"] = 0.88
        inputDataCurrenciesSet["AUD"] = 1.555
        inputDataSet.add(DayCurrenciesData("2022-12-14", inputDataCurrenciesSet))

        val inputDataCurrenciesSet2 = mutableMapOf<String, Double>()
        inputDataCurrenciesSet2["PLN"] = 8.34
        inputDataCurrenciesSet2["USD"] = 0.10
        inputDataCurrenciesSet2["AUD"] = 1.578
        inputDataSet.add(DayCurrenciesData("2018-04-20", inputDataCurrenciesSet2))

        for (i in 0..1) {
            dataProvider.addNewCurrencies(inputDataSet[i])
        }
    }

    @Test
    fun getDayCurrenciesItems() {
        val testingSet = mutableListOf<Item>()
        testingSet.add(Item("  Dzień 14-12-2022:", false))
        testingSet.add(Item("- PLN : 10.5", true))
        testingSet.add(Item("- USD : 0.88", true))
        testingSet.add(Item("- AUD : 1.555", true))
        testingSet.add(Item("  Dzień 20-04-2018:", false))
        testingSet.add(Item("- PLN : 8.34", true))
        testingSet.add(Item("- USD : 0.1", true))
        testingSet.add(Item("- AUD : 1.578", true))

        val resultSet = dataProvider.getDayCurrenciesItems()

        assert(resultSet.size == testingSet.size)
        for (i in resultSet.indices) {
            assert(resultSet[i].text == testingSet[i].text)
            assert(resultSet[i].isCurrencyItem == testingSet[i].isCurrencyItem)
        }
    }

    @Test
    fun getCurrencyItem() {
        val testingItem = CurrencyData("PLN", 8.34, "20-04-2018")

        val resultItem = dataProvider.getCurrencyItem(5)

        assert(resultItem.name == testingItem.name)
        assert(resultItem.conversionValue == testingItem.conversionValue)
        assert(resultItem.date == testingItem.date)
    }
}