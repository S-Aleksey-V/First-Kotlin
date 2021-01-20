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

class MainAdapter : RecyclerView.Adapter<MainAdapter.NoteViweHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = notes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.NoteViweHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_note, parent, false)
        return MainAdapter.NoteViweHolder(view)
    }

    override fun onBindViewHolder(holder: MainAdapter.NoteViweHolder, position: Int) {
        holder.bind(notes[position])
    }

    class NoteViweHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ui: ItemNoteBinding = ItemNoteBinding.bind(itemView)

        fun bind(note: Note) {
            ui.title.text = note.title
            ui.body.text = note.note
            //itemView.setBackgroundColor(note.color) сделал закруглённые края цвет пока убрал)
            itemView.clipToOutline = true

        }

    }
}