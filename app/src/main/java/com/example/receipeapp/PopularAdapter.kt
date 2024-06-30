package com.example.receipeapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.receipeapp.databinding.PopularRvBinding

class PopularAdapter(
    var dataList: ArrayList<Receipe>,
    var context: Context
): RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: PopularRvBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var binding = PopularRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size

    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.popularImg
        Glide.with(context).load(dataList.get(position).img).into(holder.binding.popularImg)

        holder.binding.popularTxt.text = dataList.get(position).tittle
        var time = dataList.get(position).ing.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        holder.binding.popularTime.text = time.get(0)


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