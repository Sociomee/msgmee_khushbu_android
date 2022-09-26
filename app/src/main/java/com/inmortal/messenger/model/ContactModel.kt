package com.inmortal.messenger.model

import android.net.Uri

/**
 * Created by Piyush Thapliyal.
 */
class ContactModel {

    var name: String? = null
    var number: String? = null
    var id: String? = null

    fun setNames(name: String) {
        this.name = name
    }

    fun getNumbers(): String {
        return number.toString()
    }

    fun setNumbers(number: String) {
        this.number = number
    }

    fun  getIds(): String{
        return  id.toString()
    }

    fun setIds(id : String) {
        this.id = id
    }

    fun getNames(): String {
        return name.toString()
    }

}