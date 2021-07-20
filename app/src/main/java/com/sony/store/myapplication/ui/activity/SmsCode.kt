package com.sony.store.myapplication.ui.activity

import android.os.Bundle
import android.widget.Toast
import com.sony.store.myapplication.R
import com.sony.store.myapplication.base.BaseActivity
import com.sony.store.myapplication.widget.IPhoneCode
import kotlinx.android.synthetic.main.sms_code_layout.*


class SmsCode : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sms_code_layout)
//设置监听

        //设置监听
        edit.setOnVCodeCompleteListener(object : IPhoneCode.OnVCodeInputListener{
            override fun vCodeIncomplete(verificationCode: String?) {
            }

            override fun vCodeComplete(verificationCode: String?) {
                Toast.makeText(this@SmsCode, "$verificationCode", Toast.LENGTH_SHORT).show()
            }

        })

    }

}