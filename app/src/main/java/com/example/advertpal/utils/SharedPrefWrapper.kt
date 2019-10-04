package com.example.advertpal.utils

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import com.facebook.android.crypto.keychain.AndroidConceal
import com.facebook.android.crypto.keychain.SharedPrefsBackedKeyChain
import com.facebook.crypto.Crypto
import com.facebook.crypto.CryptoConfig
import com.facebook.crypto.Entity
import com.facebook.soloader.SoLoader
import com.google.gson.Gson
import java.nio.charset.Charset

class SharedPrefWrapper(context: Context) {

    private var crypto: Crypto

    init {
        SoLoader.init(context, false)

        val keyChain = SharedPrefsBackedKeyChain(context, CryptoConfig.KEY_256)
        crypto = AndroidConceal.get().createDefaultCrypto(keyChain)
    }

    private val sPref: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_FILENAME, AppCompatActivity.MODE_PRIVATE)

    fun saveToken(token: String) {
        val cipherToken = crypto.encrypt(token.toByteArray(), Entity.create(ENTITY_TOKEN_NAME))
        val cipherTokenJson = Gson().toJson(cipherToken)
        sPref.edit().run {
            putString(SHARED_PREF_TOKEN_KEY, cipherTokenJson)
            apply()
        }
    }

    fun getToken(): String {
        val cipherTokenJson = sPref.getString(SHARED_PREF_TOKEN_KEY, "")
        if (cipherTokenJson.isNullOrEmpty()) return ""
        val cipherToken = Gson().fromJson(cipherTokenJson, ByteArray::class.java)
        val decryptedToken = crypto.decrypt(cipherToken, Entity.create(ENTITY_TOKEN_NAME))
        return decryptedToken.toString(Charset.defaultCharset())
    }


    fun savePostId(id: Int, workId: String) {
        sPref.edit().run {
            putInt(SHARED_PREF_POST_ID_KEY + workId, id)
                .apply()
        }

    }

    fun getPostId(workId: String): Int =
        sPref.getInt(SHARED_PREF_POST_ID_KEY + workId, -1)

    fun saveUserId(userId: String) {
        sPref.edit().run {
            putString(SHARED_PREF_USERID_KEY, userId)
            apply()
        }
    }

    fun getUserId(): String =
        sPref.getString(SHARED_PREF_USERID_KEY, "")
}