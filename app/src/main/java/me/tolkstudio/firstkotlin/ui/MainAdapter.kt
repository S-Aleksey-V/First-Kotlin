package me.tolkstudio.firstkotlin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import me.tolkstudio.firstkotlin.R
import me.tolkstudio.firstkotlin.databinding.ItemNoteBinding
import me.tolkstudio.firstkotlin.model.Note
import me.tolkstudio.firstkotlin.ui.MainAdapter.NoteViweHolder

interface OnItemClickListener {
    fun onItemClick(note: Note)
}

class MainAdapter(private val ItemClickListener: OnItemClickListener) : RecyclerView.Adapter<NoteViweHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = notes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViweHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_note, parent, false)
        return NoteViweHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViweHolder, position: Int) {
        holder.bind(notes[position])
    }

    inner class NoteViweHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ui: ItemNoteBinding = ItemNoteBinding.bind(itemView)

        fun bind(note: Note) {
            ui.title.text = note.title
            ui.body.text = note.note
            //itemView.setBackgroundColor(note.color)
            itemView.clipToOutline = true
            itemView.setOnClickListener { ItemClickListener.onItemClick(note) }

        }

    }
}