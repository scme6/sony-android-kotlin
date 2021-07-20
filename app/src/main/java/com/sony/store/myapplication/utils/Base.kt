package com.sony.store.myapplication.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

open class Base {

    lateinit var settings: SharedPreferences

    fun init(application: Application) {
        settings = application.getSharedPreferences("sonyStore", Context.MODE_PRIVATE)
    }


    operator fun contains(key: String?): Boolean {
        return settings.contains(key)
    }

    fun remove(key: String?) {
        settings.edit().remove(key).apply()
    }

    fun getString(key: String?): String? {
        return getString(key, "")
    }

    fun getString(key: String?, defaultValue: String?): String? {
        return settings.getString(key, defaultValue)
    }

    fun putString(key: String?, value: String?) {
        settings.edit().putString(key, value).apply()
    }

    fun getBoolean(key: String?, defaultValue: Boolean): Boolean {
        return settings.getBoolean(key, defaultValue)
    }

    fun hasKey(key: String?): Boolean {
        return settings.contains(key)
    }

    fun putBoolean(key: String?, value: Boolean) {
        settings.edit().putBoolean(key, value).apply()
    }

    fun putInt(key: String?, value: Int) {
        settings.edit().putInt(key, value).apply()
    }

    fun getInt(key: String?, defaultValue: Int): Int {
        return settings.getInt(key, defaultValue)
    }

    fun putFloat(key: String?, value: Float) {
        settings.edit().putFloat(key, value).apply()
    }

    fun getFloat(key: String?, defaultValue: Float): Float {
        return settings.getFloat(key, defaultValue)
    }

    fun putLong(key: String?, value: Long) {
        settings.edit().putLong(key, value).apply()
    }

    fun getLong(key: String?): Long {
        return getLong(key, 0)
    }

    fun getLong(key: String?, defaultValue: Long): Long {
        return settings.getLong(key, defaultValue)
    }

    fun clear(context: Context?, p: SharedPreferences) {
        val editor = p.edit()
        editor.clear()
        editor.apply()
    }

    fun clear() {
        settings.edit().clear().apply()
    }

}