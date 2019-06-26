package com.revolshen.proweek.data


class TaskData{
    var text: String = ""
    var description: String = ""
    var priority: Int = -1

    fun isEmpty(): Boolean{
        return !(text.isNullOrEmpty())
    }

    fun setAllDefault(){
        text = ""
        description = ""
        priority = -1
    }
}