package com.lt.integrate.frame.demo

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import com.android.volley.VolleyError
import com.lt.integrate.frame.demo.listener.OnItemClickListener
import com.lt.integrate.frame.demo.model.ItemObject
import com.lt.integrate.frame.demo.presenter.QuestGetPrestener
import com.lt.integrate.frame.demo.presenter.QuestGetPrestenerImpl
import com.lt.integrate.frame.demo.view.QuestGetView
import com.lt.integrate.frame.demo.view.adapter.BaseObjectAdapter
import com.lt.integrate.frame.file.HttpUploadFileRequest
import com.lt.integrate.frame.file.VolleyInterface
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView
import kotlinx.android.synthetic.main.main_activity.*
import java.io.File


class MainActivity : AppCompatActivity(), QuestGetView, OnItemClickListener {


    var adapter: BaseObjectAdapter? = null
    var itemList = ArrayList<ItemObject>()
    var urls = arrayOf(Constants.HOST_yule_api, Constants.HOST_shishang_api)
    var questPrestener: QuestGetPrestener? = null
    var indexPostion = -1
    var mContext: Context? = null
    var url = ""
    var fileName = ""
    var file: File? = null
    var params: Map<String, String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        mContext = this.applicationContext
        questPrestener = QuestGetPrestenerImpl(this)

        pullLoadMoreRecyclerView.setLinearLayout()
        pullLoadMoreRecyclerView.pushRefreshEnable = false
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(object : PullLoadMoreRecyclerView.PullLoadMoreListener {
            override fun onRefresh() {
                initData()
            }

            override fun onLoadMore() {

            }
        })

        initData()

        HttpUploadFileRequest.uploadFileMultipart(mContext, url, fileName, file, params, object : VolleyInterface(mContext) {
            override fun onMySuccess(result: String) {

            }

            override fun onMyError(error: VolleyError) {

            }
        })
    }

    private fun initData() {
        indexPostion++
        if (indexPostion > urls.size - 1) {
            indexPostion = 0
        }
        questPrestener?.loadQuestGetPrestenerData(this, urls[indexPostion])
    }

    override fun onHttpSucesss(list: List<ItemObject>, data: String) {
        pullLoadMoreRecyclerView.setPullLoadMoreCompleted()
        if (list.isEmpty() || list?.size ==0) {
            return
        }
        if(itemList.size == 0 && list?.size > 0){
            itemList.addAll(list)
        }
        if(itemList.size> 0) {
            var dataList = ArrayList<ItemObject>()
            dataList.addAll(itemList)
            itemList.clear()
           if(list?.size > 0){
               itemList.addAll(list)
           }
            itemList.addAll(dataList)
        }
        if(itemList.size > 0){
            adapter = BaseObjectAdapter(this,itemList)
            adapter?.setOnItemClickListener(this)
            pullLoadMoreRecyclerView.setAdapter(adapter)
            adapter?.notifyDataSetChanged()
        }
    }


    override fun onHttpError(error: String) {
    }

    override fun onItemClickListener(view: View, position: Int) {
      if(itemList.size > 0){
          val intent = Intent()
          intent.setClass(this,DetailsActivity::class.java)
          intent.putExtra("url",itemList?.get(position).webUrl)
          startActivity(intent)
      }
    }

}

