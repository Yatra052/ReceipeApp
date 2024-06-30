package com.example.receipeapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.receipeapp.databinding.SearchRvBinding

class SearchAdapter(var dataList: ArrayList<Receipe>, var context:Context):RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: SearchRvBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view = SearchRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size

    }




    fun filterList(filterList: ArrayList<Receipe>){
        dataList = filterList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(dataList.get(position).img).into(holder.binding.searchImg)
        holder.binding.searchtxt.text = dataList.get(position).tittle


        holder.itemView.setOnClickListener{
            var intent = Intent(context, ReceipeActivity::class.java)
            intent.putExtra("img", dataList.get(position).img)
            intent.putExtra("title", dataList.get(position).tittle)
            intent.putExtra("des", dataList.get(position).des)
            intent.putExtra("ing", dataList.get(position).ing)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK



            context.startActivity(intent)


        }

    }
}