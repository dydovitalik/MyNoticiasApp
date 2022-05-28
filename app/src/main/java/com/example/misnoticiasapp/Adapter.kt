package com.example.misnoticiasapp

import android.content.Context
import com.example.misnoticiasapp.models.Article
import com.example.misnoticiasapp.Adapter.MyViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val articles: List<Article>, private val context: Context) : RecyclerView.Adapter<MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holders: MyViewHolder, position: Int) {
        val model = articles[position]

        Glide.with(context).load(model.urlToImage).transition(DrawableTransitionOptions.withCrossFade()).into(holders.imageView)

        holders.title.text = model.title
        holders.desc.text = model.description
        holders.source.text = model.source!!.name
        holders.time.text = " \u2022 " + Utils.DateToTimeFormat(model.publishedAt)
        holders.published_ad.text = Utils.DateFormat(model.publishedAt)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var title: TextView
        var desc: TextView
        var published_ad: TextView
        var source: TextView
        var time: TextView
        var imageView: ImageView

        init {
            title = itemView.findViewById(R.id.title)
            desc = itemView.findViewById(R.id.desc)
            published_ad = itemView.findViewById(R.id.publishedAt)
            source = itemView.findViewById(R.id.source)
            time = itemView.findViewById(R.id.time)
            imageView = itemView.findViewById(R.id.img)
        }
    }

}