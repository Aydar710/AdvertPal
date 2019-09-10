package com.example.advertpal.utils

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity

class SharedPrefWrapper(context: Context) {

    private val sPref: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_FILENAME, AppCompatActivity.MODE_PRIVATE)

    fun saveToken(token: String) {
        sPref.edit().run {
            putString(SHARED_PREF_TOKEN_KEY, token)
            apply()
        }
    }

    fun getToken(): String {
        return "ca0db8569c1d89491898b24f9ec10b7146203ccd6b7a2171ddef24f3285650c0ad6bc847cff6c865c6bb7"
        //sPref.getString(SHARED_PREF_TOKEN_KEY, "")
        //TODO: Return actual token
    }

    fun savePostId(id: Int) {
        sPref.edit().run {
            putInt(SHARED_PREF_POST_ID_KEY, id)
                .apply()
        }
    }

    fun getPostId(): Int =
        sPref.getInt(SHARED_PREF_POST_ID_KEY, -1)
}