package com.example.s08recycleviewfundamental

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s16mvvmcleanachitecture.R

class RecycleViewAdapter(
    private val list: List<Fruit>,
    private val clickListener : (Fruit) -> Unit

):RecyclerView.Adapter<RecycleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItem = inflater.inflate(R.layout.recycleview, parent, false)
        return RecycleViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }
}

class RecycleViewHolder(val view: View):RecyclerView.ViewHolder(view){

   fun bind(fruit: Fruit, clickListener: (Fruit) -> Unit){
       val myTextView = view.findViewById<TextView>(R.id.textView)
       myTextView.text = fruit.name
       view.setOnClickListener {
           clickListener(fruit)
       }
   }
}