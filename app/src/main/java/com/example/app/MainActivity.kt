package com.example.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.app.entity.User
import com.example.app.widget.CodeView
import com.example.core.utils.CacheUtils
import com.example.core.utils.Utils
import com.example.lesson.LessonActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by adamchang on 2021/1/6.
 * Company HengXingYun
 */
class MainActivity: AppCompatActivity(), View.OnClickListener {
    private val usernameKey = "username"
    private val passwordKey = "password"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        et_username.setText(CacheUtils.get(usernameKey))
        et_password.setText(CacheUtils.get(passwordKey))

        btn_login.setOnClickListener(this)
        code_view.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v is CodeView) {
            v.updateCode()
        } else if (v is Button) {
            login()
        }
    }

    private fun login() {
        val username = et_username.text.toString()
        val password = et_password.text.toString()
        val code = et_code.text.toString()
        val user = User(username, password, code)
        if (verify(user)) {
            CacheUtils.save(usernameKey, username)
            CacheUtils.save(passwordKey, password)
            startActivity(Intent(this, LessonActivity::class.java))
        }
    }

    private fun verify(user: User): Boolean {
        if (user.name.length < 4) {
            Utils.toast("用户名不合法")
            return false
        }
        if (user.password.length < 4) {
            Utils.toast("密码不合法")
            return false
        }
        return true
    }
}