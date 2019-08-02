package com.rss.william.app.rssapp.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rss.william.app.rssapp.Interface.ItemClickListener
import com.rss.william.app.rssapp.Model.RSSObject
import com.rss.william.app.rssapp.R
import com.rss.william.app.rssapp.ViewFeedActivity
import kotlinx.android.synthetic.main.layout_rss.view.*

class FeedViewHolder(itemView:View):RecyclerView.ViewHolder(itemView),View.OnClickListener,View.OnLongClickListener{

    var txtTitle:TextView
    var txtPubDate:TextView
    var txtContent:TextView

    private var itemClickListener:ItemClickListener?=null

    init {
        txtTitle = itemView.findViewById(R.id.txtTitulo) as TextView
        txtPubDate = itemView.findViewById(R.id.txtPubDate) as TextView
        txtContent = itemView.findViewById(R.id.txtContent) as TextView

        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View?) {
        itemClickListener!!.onClick(v,adapterPosition,false)
    }

    override fun onLongClick(v: View?): Boolean {
        itemClickListener!!.onClick(v,adapterPosition,true)
        return true
    }

}

class FeedAdapter(private val rssObject: RSSObject,private val context:Context):RecyclerView.Adapter<FeedViewHolder>(){

    private val inflater:LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val itemView = inflater.inflate(R.layout.layout_rss,parent,false)
        return FeedViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return rssObject.items.size
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.txtTitle.text = rssObject.items[position].title
        holder.txtPubDate.text = rssObject.items[position].pubDate
        holder.txtContent.text = rssObject.items[position].content

        holder.setItemClickListener(ItemClickListener { view, position, isLongClick ->

            if(!isLongClick){
                val url = rssObject.items[position].link
                val intent = Intent(context, ViewFeedActivity::class.java)
                // Setear url
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra("url-extra", url)
                // Iniciar actividad
                context.startActivity(intent)
                /*val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.items[position].link))
                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(browserIntent)*/
            }
        })
    }

}