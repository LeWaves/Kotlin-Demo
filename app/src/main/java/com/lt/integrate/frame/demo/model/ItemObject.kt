package com.lt.integrate.frame.demo.model

import java.io.Serializable

/**
 * Created by iclick on 2017/9/26.
 */

class ItemObject : Serializable {

    var title = ""
    var imgUrl = ""
    var webUrl = ""

    override fun toString(): String {
        return "ItemObject{" +
                "title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", webUrl='" + webUrl + '\'' +
                '}'
    }

    companion object {

        const val serialVersionUID = 1L
    }
}
