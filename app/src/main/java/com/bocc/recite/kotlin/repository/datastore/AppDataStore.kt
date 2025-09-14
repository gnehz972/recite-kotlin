package com.bocc.recite.kotlin.repository.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * DataStore implementation to replace SharedPreferences
 * Created to migrate from SharedPreferences to DataStore
 */
@Singleton
class AppDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    
    companion object {
        // Constants for preference keys
        const val CARD_NAME = "card_name"
        const val DAILY_SENTENCE_CHECKDATE = "daily_sentence_checkdate"
    }
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

    // Preference keys
    private val userNameKey = stringPreferencesKey("user_name")
    private val passwordKey = stringPreferencesKey("password")
    private val timeKey = intPreferencesKey("time")
    private val cardNameKey = stringPreferencesKey("card_name")
    private val dailySentenceCheckDateKey = stringPreferencesKey("daily_sentence_checkdate")

    // User preferences
    val userName: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[userNameKey] ?: ""
    }

    val password: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[passwordKey] ?: ""
    }

    val time: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[timeKey] ?: 0
    }

    val cardName: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[cardNameKey] ?: ""
    }

    val dailySentenceCheckDate: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[dailySentenceCheckDateKey] ?: ""
    }

    // Suspend functions for writing data
    suspend fun setUserName(userName: String) {
        context.dataStore.edit { preferences ->
            preferences[userNameKey] = userName
        }
    }

    suspend fun setPassword(password: String) {
        context.dataStore.edit { preferences ->
            preferences[passwordKey] = password
        }
    }

    suspend fun setTime(time: Int) {
        context.dataStore.edit { preferences ->
            preferences[timeKey] = time
        }
    }

    suspend fun setCardName(cardName: String) {
        context.dataStore.edit { preferences ->
            preferences[cardNameKey] = cardName
        }
    }

    suspend fun setDailySentenceCheckDate(date: String) {
        context.dataStore.edit { preferences ->
            preferences[dailySentenceCheckDateKey] = date
        }
    }

    // Generic preference access methods for backward compatibility
    suspend fun getString(key: String, defaultValue: String = ""): String {
        val stringKey = stringPreferencesKey(key)
        return context.dataStore.data.map { preferences ->
            preferences[stringKey] ?: defaultValue
        }.first()
    }

    suspend fun setString(key: String, value: String) {
        val stringKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[stringKey] = value
        }
    }

    suspend fun getInt(key: String, defaultValue: Int = 0): Int {
        val intKey = intPreferencesKey(key)
        return context.dataStore.data.map { preferences ->
            preferences[intKey] ?: defaultValue
        }.first()
    }

    suspend fun setInt(key: String, value: Int) {
        val intKey = intPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[intKey] = value
        }
    }

    suspend fun getLong(key: String, defaultValue: Long = 0L): Long {
        val longKey = longPreferencesKey(key)
        return context.dataStore.data.map { preferences ->
            preferences[longKey] ?: defaultValue
        }.first()
    }

    suspend fun setLong(key: String, value: Long) {
        val longKey = longPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[longKey] = value
        }
    }

    suspend fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        val booleanKey = booleanPreferencesKey(key)
        return context.dataStore.data.map { preferences ->
            preferences[booleanKey] ?: defaultValue
        }.first()
    }

    suspend fun setBoolean(key: String, value: Boolean) {
        val booleanKey = booleanPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[booleanKey] = value
        }
    }

    suspend fun getFloat(key: String, defaultValue: Float = 0f): Float {
        val floatKey = floatPreferencesKey(key)
        return context.dataStore.data.map { preferences ->
            preferences[floatKey] ?: defaultValue
        }.first()
    }

    suspend fun setFloat(key: String, value: Float) {
        val floatKey = floatPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[floatKey] = value
        }
    }

    suspend fun getStringSet(key: String, defaultValue: Set<String> = emptySet()): Set<String> {
        val stringSetKey = stringSetPreferencesKey(key)
        return context.dataStore.data.map { preferences ->
            preferences[stringSetKey] ?: defaultValue
        }.first()
    }

    suspend fun setStringSet(key: String, value: Set<String>) {
        val stringSetKey = stringSetPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[stringSetKey] = value
        }
    }
}
