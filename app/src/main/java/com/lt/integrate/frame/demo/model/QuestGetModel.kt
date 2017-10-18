package com.lt.integrate.frame.demo.model

import android.content.Context
import com.lt.integrate.frame.demo.listener.HttpsListenerInterface

/**
 * Created by iclick on 2017/9/26.
 */

interface QuestGetModel {
    fun loadQuestGetData(context: Context, url: String, listener: HttpsListenerInterface)
}
