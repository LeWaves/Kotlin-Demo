package com.lt.integrate.frame.demo.listener

import android.view.View
import com.lt.integrate.frame.demo.model.ItemObject
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

/**
 * Created by iclick on 2017/9/26.
 */

interface HttpsListenerInterface {
    fun onSucessHttps(list: List<ItemObject>, data: String)
    fun onErrorHttps(error: String)
}
