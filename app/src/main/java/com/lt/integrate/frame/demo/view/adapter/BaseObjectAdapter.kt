package com.lt.integrate.frame.demo.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.lt.integrate.frame.demo.R
import com.lt.integrate.frame.demo.listener.OnItemClickListener
import com.lt.integrate.frame.demo.model.ItemObject
import com.lt.integrate.frame.img.ImageLoader

/**
 * Created by iclick on 2017/9/27.
 */

class BaseObjectAdapter(private val mContext: Context,var objectList: List<ItemObject>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var clickListener: OnItemClickListener? = null
    private val inflater: LayoutInflater

    init {
        inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        if (clickListener == null)
            clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var holder = ObjectViewHolder(inflater.inflate(R.layout.list_item_news, null))

        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemHolder = holder as ObjectViewHolder
        val objectItem = objectList[position]
        if (objectItem != null) {
            itemHolder?.textView?.text = objectItem.title
            ImageLoader.display(mContext, itemHolder?.imageView, objectItem.imgUrl)
        }
        itemHolder!!.itemView.setOnClickListener {
            clickListener?.onItemClickListener( itemHolder!!.itemView,position)
        }
    }

    override fun getItemCount() : Int = objectList?.size

    internal inner class ObjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById<View>(R.id.textView) as TextView
        var imageView: ImageView = itemView.findViewById<View>(R.id.imageView) as ImageView


    }

}
