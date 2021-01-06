package com.example.lesson

import android.widget.Toast
import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import com.example.core.utils.Utils.toast
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken

/**
 * Created by adamchang on 2021/1/6.
 * Company HengXingYun
 */
class LessonPresenter constructor(var activity: LessonActivity) {
    private val LESSON_PATH = "lessons"
    var lessons: List<Lesson> = listOf()
    private val type = object : TypeToken<List<Lesson?>?>() {}.type

    fun fetchData() {
        HttpClient[LESSON_PATH, type, object : EntityCallback<List<Lesson>> {

            override fun onSuccess(entity: List<Lesson>) {
                lessons = entity
                activity.runOnUiThread { activity.showResult(lessons) }
            }

            override fun onFailure(message: String?) {
                activity.runOnUiThread { toast(message, Toast.LENGTH_SHORT) }
            }

        }]
    }

    fun showPlayback() {
        val playbackLessons = mutableListOf<Lesson>()
        lessons.forEach{
            playbackLessons.add(it)
        }
        activity.showResult(playbackLessons)
    }
}