package com.mariuszmarondel.currenciesapp.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mariuszmarondel.currenciesapp.databinding.ActivityCurrencyBinding
import com.mariuszmarondel.currenciesapp.model.CurrencyData
import com.mariuszmarondel.currenciesapp.utils.Values
import com.mariuszmarondel.currenciesapp.viewmodel.CurrencyViewModel

class CurrencyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrencyBinding
    private val viewModel: CurrencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrencyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
    }

    private fun initData() {
        (intent.getSerializableExtra(Values.INTENT_EXTRA_CURRENCY_ID) as? CurrencyData)?.let {
            viewModel.updateData(it)
        }

        viewModel.observeData().observe(this) {
            binding.currencyDateValue.text = it.date
            binding.currencyNameValue.text = it.name
            binding.currencyValueValue.text = it.conversionValue.toString()
        }
    }
}