package me.tolkstudio.firstkotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import me.tolkstudio.firstkotlin.R
import me.tolkstudio.firstkotlin.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        adapter = MainAdapter()
        mainRecycler.adapter = adapter

        viewModel.viewState().observe(this, Observer<MainViewState> { state ->
            state?.let { adapter.notes = state.notes }
        })
    }
}