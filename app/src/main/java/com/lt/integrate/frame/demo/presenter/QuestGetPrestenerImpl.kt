package com.lt.integrate.frame.demo.presenter

import android.content.Context

import com.lt.integrate.frame.demo.listener.HttpsListenerInterface
import com.lt.integrate.frame.demo.model.ItemObject
import com.lt.integrate.frame.demo.model.QuestGetModel
import com.lt.integrate.frame.demo.model.QuestGetModelImpl
import com.lt.integrate.frame.demo.view.QuestGetView

import java.lang.ref.WeakReference
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

/**
 * Created by iclick on 2017/9/26.
 */

class QuestGetPrestenerImpl(private val mGetView: QuestGetView) : QuestGetPrestener {
    private val mGetModel: QuestGetModel
    private val listenerInterface: HttpsListenerInterface

    init {
        this.mGetModel = QuestGetModelImpl()
        this.listenerInterface = OnInterfaceListener(this)
    }

    private inner class OnInterfaceListener(presenter: QuestGetPrestenerImpl) : HttpsListenerInterface {
        private val presenter: WeakReference<QuestGetPrestenerImpl>

        init {
            this.presenter = WeakReference(presenter)
        }

        override fun onSucessHttps(list: List<ItemObject>, data: String) {
            this.presenter.get()?.mGetView!!.onHttpSucesss(list, data)
        }

        override fun onErrorHttps(error: String) {
            this.presenter.get()?.mGetView!!.onHttpError(error)
        }
    }


    override fun loadQuestGetPrestenerData(context: Context, url: String) {
        this.mGetModel.loadQuestGetData(context, url, listenerInterface)
    }
}
