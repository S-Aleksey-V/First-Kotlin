package me.tolkstudio.firstkotlin.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import me.tolkstudio.firstkotlin.R
import me.tolkstudio.firstkotlin.databinding.ActivityMainBinding
import me.tolkstudio.firstkotlin.model.Note
import me.tolkstudio.firstkotlin.viewmodel.MainViewModel


class MainActivity : BaseActivity<List<Note>?, MainViewState>() {

    override val viewModel: MainViewModel
            by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override val ui: ActivityMainBinding
            by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override val layoutRes: Int = R.layout.activity_main
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(ui.bottomAppBar)

        adapter = MainAdapter(object : OnItemClickListener {
            override fun onItemClick(note: Note) {
                openNoteScreen(note)
            }

        })
        mainRecycler.adapter = adapter

        fab.setOnClickListener { openNoteScreen(null) }
    }

    override fun renderData(data: List<Note>?) {
        if (data == null) return
        adapter.notes = data
    }

    private fun openNoteScreen(note: Note?) {
        val intent = NoteActivity.getStartIntent(this, note?.id)
        startActivity(intent)
    }
}