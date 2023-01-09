package com.example.finance.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.finance.DB.EntryEntity
import com.example.finance.DB.UserEntity
import com.example.finance.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RvHistoryAdapter (
    private val history: ArrayList<EntryEntity>,
//    private val userlogged:String,
    private val layout: Int,
    private val context: Context,
    // private var DetailFragment:DetailFragment
    // private val onItemClickListener: (view:View,idx:Int)->Unit,

): RecyclerView.Adapter<RvHistoryAdapter.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var itemView = LayoutInflater.from(parent.context)
        return CustomViewHolder(
            itemView.inflate(
                layout, parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = history[position]
        holder.tipe.text = item.tipe
        holder.total.text = item.currency+" "+item.total
        holder.tipetrans.text = item.tipetransaksi
        holder.date.text = item.date
        holder.note.text = item.note
    }

    override fun getItemCount(): Int {
        return history.size
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tipe: TextView = itemView.findViewById(R.id.tvtipe)
        val tipetrans: TextView = itemView.findViewById(R.id.tvtipetrans)
        val total: TextView = itemView.findViewById(R.id.tvtotal)
        val date: TextView = itemView.findViewById(R.id.tvdate)
        val note: TextView = itemView.findViewById(R.id.tvnote)
    }
}