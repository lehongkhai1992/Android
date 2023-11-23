package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListItemBinding

class RecycleViewAdapter(
    private val clickListener: (Subscribers) -> Unit
) : RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder>() {
    private val subscribersList =  ArrayList<Subscribers>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subscribersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscribersList[position], clickListener)
    }

    fun setList(subscribers: List<Subscribers>){
        subscribersList.clear()
        subscribersList.addAll(subscribers)
    }

    inner class MyViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subscribers: Subscribers,  clickListener: (Subscribers) -> Unit) {
            binding.nameTextView.text = subscribers.name
            binding.emailTextView.text = subscribers.email
            binding.listItemLayout.setOnClickListener {
                clickListener(subscribers)
            }
        }
    }
}