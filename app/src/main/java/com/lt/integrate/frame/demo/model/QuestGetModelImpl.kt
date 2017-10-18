package com.lt.integrate.frame.demo.model

import android.content.Context
import android.util.Log

import com.android.volley.VolleyError
import com.lt.integrate.frame.demo.listener.HttpsListenerInterface
import com.lt.integrate.frame.demo.utils.AppUtils
import com.lt.integrate.frame.http.HttpJSONRequest
import com.lt.integrate.frame.http.HttpMethodState
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList


/**
 * Created by iclick on 2017/9/26.
 */

class QuestGetModelImpl : QuestGetModel {

    override fun loadQuestGetData(context: Context, url: String, listener: HttpsListenerInterface) {
        if (AppUtils.isNetworkAvailable(context)) {
            HttpJSONRequest(context, HttpMethodState.GET, url, object : HttpJSONRequest.RequestNetWork {
                override fun onSuccess(response: JSONObject) {
                    //Log.i("iax","onSuccess JSONObject :"+response?.toString())
                    try {
                        val itemList = ArrayList<ItemObject>()
                        var value =""
                        itemList.clear()
                        val obj = JSONObject(response.toString())

                        if (obj.has("result")) {
                            val resultObj = obj.getJSONObject("result")

                            if (resultObj.has("data")) {


                                  val mArray = resultObj.getJSONArray("data")
                                  if (mArray.length() > 0) {
                                      for (i in 0 until mArray.length()) {
                                          val itemObj = mArray.getJSONObject(i)
                                          val bean = ItemObject()
                                          if (itemObj.has("title")) {
                                              bean.title = itemObj.getString("title")
                                          }
                                          if (itemObj.has("url")) {
                                              bean.webUrl = itemObj.getString("url")
                                          }

                                          if (itemObj.has("thumbnail_pic_s")) {
                                              if (!itemObj.isNull("thumbnail_pic_s")) {
                                                  bean.imgUrl = itemObj.getString("thumbnail_pic_s")
                                              }
                                          }
                                          if (itemObj.has("thumbnail_pic_s03")) {
                                              if (!itemObj.isNull("thumbnail_pic_s03")) {
                                                  bean.imgUrl = itemObj.getString("thumbnail_pic_s03")
                                              }
                                          }
                                          if (itemObj.has("thumbnail_pic_s02")) {
                                              if (!itemObj.isNull("thumbnail_pic_s02")) {
                                                  bean.imgUrl = itemObj.getString("thumbnail_pic_s02")
                                              }
                                          }

                                          itemList.add(bean)

                                      }

                                      listener.onSucessHttps(itemList, value)
                                  }
                            }
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Log.i("iax", "解析数据异常:" + e.toString())
                    }

                }

                override fun onFailure(error: VolleyError) {
                    Log.i("iax","onFailure 解析异常 :"+error?.toString())
                    listener.onErrorHttps("解析异常")
                }
            })


        } else {
            listener.onErrorHttps("网络异常")
        }
    }
}
