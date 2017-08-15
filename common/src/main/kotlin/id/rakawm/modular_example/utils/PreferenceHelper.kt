package id.rakawm.modular_example.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONException
import java.util.*

/**
 * Created by rakawm on 5/12/17.
 */

class PreferenceHelper(val context: Context) {
    fun putBoolean(key: String, value: Boolean) {
        getEditor().putBoolean(key, value).commit()
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return getPreferences().getBoolean(key, defValue)
    }

    fun putInt(key: String, value: Int) {
        getEditor().putInt(key, value).commit()
    }

    fun getInt(key: String, defValue: Int): Int {
        return getPreferences().getInt(key, defValue)
    }

    fun putString(key: String, value: String) {
        val editor = getEditor()
        editor.putString(key, value)
        editor.commit()
    }

    fun getString(key: String, defValue: String): String {
        return getPreferences().getString(key, defValue)
    }

    fun putFloat(key: String, value: Float) {
        getEditor().putFloat(key, value).commit()
    }

    fun getFloat(key: String, defValue: Float): Float {
        return getPreferences().getFloat(key, defValue)
    }

    fun putLong(key: String, value: Long) {
        getEditor().putLong(key, value).commit()
    }

    fun getLong(key: String, defValue: Long): Long {
        return getPreferences().getLong(key, defValue)
    }

    fun putObject(key: String, `object`: Any) {
        val objectString = convertObjectToString(`object`)
        putString(key, objectString)
    }

    fun <T> getObject(key: String, defValue: Any, tClass: Class<T>): T {
        val defValueString = convertObjectToString(defValue)
        val jsonValue = getPreferences().getString(key, defValueString)
        return convertJsonStringToObject(jsonValue, tClass)
    }

    private fun <T> convertJsonStringToObject(jsonValue: String, tClass: Class<T>): T {
        val gson = Gson()
        return gson.fromJson(jsonValue, tClass)
    }

    fun putListString(key: String, value: List<String>) {
        putListObject(key, value)
    }

    @Throws(JSONException::class)
    fun getListString(key: String, defValue: List<String>): List<String> {
        val defValueString = convertObjectToString(defValue)
        val jsonValue = getPreferences().getString(key, defValueString)
        return convertJsonStringToListString(jsonValue)
    }

    private fun convertJsonStringToListString(jsonValue: String): List<String> {
        val type = object : TypeToken<List<String>>() {

        }.type
        val gson = Gson()
        val value = gson.fromJson<List<String>>(jsonValue, type)
        return value
    }

    fun putListObject(key: String, value: List<Any>) {
        val jsonValue = convertObjectToString(value)
        val editor = getEditor()
        editor.putString(key, jsonValue)
        editor.commit()
    }

    @Throws(JSONException::class)
    fun <T> getListObject(key: String, defValue: List<T>,
                          type: Class<T>): List<T> {
        val defValueString = convertObjectToString(defValue)
        val jsonValue = getPreferences().getString(key, defValueString)
        return convertJsonStringToListObject(jsonValue, type)
    }

    private fun convertObjectToString(value: Any): String {
        val jsonValue = Gson().toJson(value)
        return jsonValue
    }

    @Throws(JSONException::class)
    private fun <T> convertJsonStringToListObject(jsonValue: String, type: Class<T>): List<T> {
        val value = ArrayList<T>()
        val gson = Gson()
        var jsonElement: JsonElement
        val jsonArray = JSONArray(jsonValue)
        for (i in 0..jsonArray.length() - 1) {
            jsonElement = JsonParser().parse(jsonArray.getString(i))
            val t = gson.fromJson(jsonElement, type)
            value.add(t)
        }
        return value
    }

    fun removePreference(key: String) {
        getEditor().remove(key).commit()
    }

    private fun getPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    private fun getEditor(): SharedPreferences.Editor {
        return getPreferences().edit()
    }

    fun clearPreferences() {
        getEditor().clear().commit()
    }
}