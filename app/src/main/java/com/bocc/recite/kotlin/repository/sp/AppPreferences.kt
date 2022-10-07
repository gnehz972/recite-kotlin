package com.bocc.recite.kotlin.repository.sp

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by zouzheng on 18-3-16.
 */
class AppPreferences(context: Context) {
    val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    var userName by PreferenceDelegates.string(defaultValue = "")
    var password by PreferenceDelegates.string()
    var time by PreferenceDelegates.int()
}

object PreferenceDelegates {
    fun string(defaultValue: String = ""): ReadWriteProperty<AppPreferences, Any> {
        return Prefs(defaultValue)
    }

    fun int(defaultValue: Int = 0): ReadWriteProperty<AppPreferences, Any> {
        return Prefs(defaultValue)
    }

    fun long(defaultValue: Long = 0L): ReadWriteProperty<AppPreferences, Any> {
        val dd: String? = null
        val ddd = dd ?: ""
        return Prefs(defaultValue)
    }
}

private class Prefs(private val defaultValue: Any) : ReadWriteProperty<AppPreferences, Any> {
    override fun getValue(thisRef: AppPreferences, property: KProperty<*>): Any {
        return when (defaultValue) {
            is String -> thisRef.preferences.getString(property.name, defaultValue) ?: ""
            is Int -> thisRef.preferences.getInt(property.name, defaultValue)
            is Long -> thisRef.preferences.getLong(property.name, defaultValue)
            is Boolean -> thisRef.preferences.getBoolean(property.name, defaultValue)
            is Float -> thisRef.preferences.getFloat(property.name, defaultValue)
            is Set<*> -> thisRef.preferences.getStringSet(property.name,
                defaultValue as Set<String>) ?: emptySet<String>()
            else -> defaultValue

        }
    }

    override fun setValue(thisRef: AppPreferences, property: KProperty<*>, value: Any) {
        thisRef.preferences.edit {
            when (value) {
                is String -> putString(property.name, value)
                is Int -> putInt(property.name, value)
                is Long -> putLong(property.name, value)
                is Boolean -> putBoolean(property.name, value)
                is Float -> putFloat(property.name, value)
            }
        }
    }
}