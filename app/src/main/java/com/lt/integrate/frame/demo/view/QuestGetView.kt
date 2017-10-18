package com.lt.integrate.frame.demo.view

import com.lt.integrate.frame.demo.model.ItemObject

/**
 * Created by iclick on 2017/9/26.
 */

interface QuestGetView {
    fun onHttpSucesss(list: List<ItemObject>, data: String)
    fun onHttpError(error: String)
}
