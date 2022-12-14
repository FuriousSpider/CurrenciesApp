package com.mariuszmarondel.currenciesapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mariuszmarondel.currenciesapp.databinding.ActivityMainBinding
import com.mariuszmarondel.currenciesapp.viewmodel.MainViewModel
import com.mariuszmarondel.currenciesapp.screen.main.recyclerview.Adapter
import com.mariuszmarondel.currenciesapp.utils.Values

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        viewModel.observeData().observe(this) { dayCurrencies ->
            binding.recyclerView.apply {
                if (layoutManager == null) {
                    layoutManager = LinearLayoutManager(applicationContext)
                }
                if (adapter == null) {
                    adapter = Adapter(dayCurrencies).apply {
                        setOnBottomReachedListener(viewModel::updateData)
                        setOnItemClickedCallback(this@MainActivity::onItemClicked)
                    }
                } else {
                    (adapter as Adapter).replaceItems(dayCurrencies)
                }
            }
            binding.mainProgressBar.visibility = View.GONE
        }
    }

    private fun onItemClicked(position: Int) {
        startActivity(Intent(this, CurrencyActivity::class.java).apply {
            putExtra(Values.INTENT_EXTRA_CURRENCY_ID, viewModel.getCurrencyItem(position))
        })
    }
}