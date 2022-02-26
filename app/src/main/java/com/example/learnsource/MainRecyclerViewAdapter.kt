package com.example.learnsource

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.learnsource.databinding.ItemMainlistBinding

class MainRecyclerViewAdapter: RecyclerView.Adapter<MainRecyclerViewAdapter.MyViewHolder>() {

    private val diffCallback=object:DiffUtil.ItemCallback<ItemMainList>(){
        override fun areItemsTheSame(oldItem: ItemMainList, newItem: ItemMainList): Boolean {
            return oldItem.equals(newItem)
        }

        override fun areContentsTheSame(oldItem: ItemMainList, newItem: ItemMainList): Boolean {
          return oldItem==newItem
        }

    }


    val lista=AsyncListDiffer(this,diffCallback)


    class MyViewHolder(val _binding:ItemMainlistBinding): RecyclerView.ViewHolder(_binding.root){

        fun bind(item:ItemMainList){
            _binding.itemMain=item
            _binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup):  MyViewHolder {
                val layoutInflater=LayoutInflater.from(parent.context)
                val binding=ItemMainlistBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
     val current=lista.currentList[position]
        holder.bind(current)

    }

    override fun getItemCount(): Int {
       return  lista.currentList.size
    }
}