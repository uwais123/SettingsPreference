package com.masuwes.settingspreference

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.CheckBoxPreference
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat

class MyPreferenceFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    // inisialisasi data, disini string smua
    private lateinit var NAME: String
    private lateinit var EMAIL: String
    private lateinit var AGE: String
    private lateinit var PHONE: String
    private lateinit var LOVE: String

    // declare all of the preference layout components
    private lateinit var namePreference: EditTextPreference
    private lateinit var emailPreference: EditTextPreference
    private lateinit var agePreference: EditTextPreference
    private lateinit var phonePreference: EditTextPreference
    private lateinit var isLoveMuPreference: CheckBoxPreference

    // adding default value as none
    companion object {
        private const val DEFAULT_VALUE = "Tidak ada"
    }

    // same as android "onCreate"
    override fun onCreatePreferences(bundle: Bundle?, s: String?) {
        addPreferencesFromResource(R.xml.preference)
        init()
        setSummaries()
    }

    // "in it" function
    private fun init() {
        NAME = resources.getString(R.string.key_name)
        EMAIL = resources.getString(R.string.key_email)
        AGE = resources.getString(R.string.key_age)
        PHONE = resources.getString(R.string.key_phone)
        LOVE = resources.getString(R.string.key_love)

        // all of this guy need to use the unused(?) arguments
        namePreference = findPreference<EditTextPreference> (NAME) as EditTextPreference
        emailPreference = findPreference<EditTextPreference>(EMAIL) as EditTextPreference
        agePreference = findPreference<EditTextPreference>(AGE) as EditTextPreference
        phonePreference = findPreference<EditTextPreference>(PHONE) as EditTextPreference
        isLoveMuPreference = findPreference<CheckBoxPreference>(LOVE) as CheckBoxPreference
    }

    // same with the function name
    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        namePreference.summary = sh.getString(NAME, DEFAULT_VALUE)
        emailPreference.summary = sh.getString(EMAIL, DEFAULT_VALUE)
        agePreference.summary = sh.getString(AGE, DEFAULT_VALUE)
        phonePreference.summary = sh.getString(PHONE, DEFAULT_VALUE)
        isLoveMuPreference.isChecked = sh.getBoolean(LOVE, false)
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    // when the data in preference will be change, this function will work to edintified the data sharedPreference and type as "key"
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        if (key == NAME) {
            namePreference.summary = sharedPreferences.getString(NAME, DEFAULT_VALUE)
        }

        if (key == EMAIL) {
            emailPreference.summary = sharedPreferences.getString(EMAIL, DEFAULT_VALUE)
        }

        if (key == AGE) {
            agePreference.summary = sharedPreferences.getString(AGE, DEFAULT_VALUE)
        }

        if (key == PHONE) {
            phonePreference.summary = sharedPreferences.getString(PHONE, DEFAULT_VALUE)
        }

        if (key == LOVE) {
            isLoveMuPreference.isChecked = sharedPreferences.getBoolean(LOVE, false)
        }

     }
}




































// end
