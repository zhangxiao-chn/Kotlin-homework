package com.example.lesson.entity

/**
 * Created by adamchang on 2021/1/5.
 * Company HengXingYun
 */
class Lesson constructor(var date: String?, var content: String?, var state: State?){
    enum class State {
        PLAYBACK {
            override fun stateName(): String {
                return "有回放"
            }
        },
        LIVE {
            override fun stateName(): String {
                return "正在直播"
            }
        },
        WAIT {
            override fun stateName(): String {
                return "等待中"
            }
        };

        abstract fun stateName(): String?
    }
}