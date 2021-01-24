package me.tolkstudio.firstkotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import me.tolkstudio.firstkotlin.databinding.ActivityMainBinding
import me.tolkstudio.firstkotlin.model.Note
import me.tolkstudio.firstkotlin.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var ui: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        setSupportActionBar(ui.bottomAppBar)
        ui.bottomAppBar.setOutlineProvider(null)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        adapter = MainAdapter(object: OnItemClickListener{
            override fun onItemClick(note: Note) {
                openNoteScreen(note)
            }

        })
        ui.mainRecycler.adapter = adapter

        viewModel.viewState().observe(this, Observer<MainViewState> { state ->
            state?.let { adapter.notes = state.notes }
        })

        ui.fab.setOnClickListener { openNoteScreen() }
    }

    private fun openNoteScreen(note: Note? = null){
        startActivity(NoteActivity.getStartIntent(this,note))
    }


}