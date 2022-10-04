package com.example.kotlintask

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class LanguageActivity : AppCompatActivity() {
    private lateinit var btnSumbit : Button

    private lateinit var engLanguageChecked : TextView
    private lateinit var rusLanguageChecked : TextView
    private lateinit var lvLanguageChecked : TextView

    private lateinit var engSelectedLanguage : TableRow
    private lateinit var rusSelectedLanguage : TableRow
    private lateinit var lvSelectedLanguage : TableRow

    var languageToLoad = ""
    var usedLanguage = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
        supportActionBar?.hide()

        languageToLoad = resources.configuration.locale.toString()
        usedLanguage = resources.configuration.locale.toString()

        binding()

        validation()

        selectLanguage()

        btnSumbit.setOnClickListener(View.OnClickListener {
            changeLanguage()
        })

    }

    private fun selectLanguage() {
        engSelectedLanguage.setOnClickListener(View.OnClickListener {
            languageToLoad = "en"
            rusLanguageChecked.setVisibility(View.INVISIBLE)
            lvLanguageChecked.setVisibility(View.INVISIBLE)
            engLanguageChecked.setVisibility(View.VISIBLE)
        })

        rusSelectedLanguage.setOnClickListener(View.OnClickListener {
            languageToLoad = "ru"
            engLanguageChecked.setVisibility(View.INVISIBLE)
            lvLanguageChecked.setVisibility(View.INVISIBLE)
            rusLanguageChecked.setVisibility(View.VISIBLE)
        })

        lvSelectedLanguage.setOnClickListener(View.OnClickListener {
            languageToLoad = "lv"
            rusLanguageChecked.setVisibility(View.INVISIBLE)
            engLanguageChecked.setVisibility(View.INVISIBLE)
            lvLanguageChecked.setVisibility(View.VISIBLE)
        })
    }

    private fun validation() {
        if (usedLanguage.contains("en")) {
            rusLanguageChecked.setVisibility(View.INVISIBLE)
            lvLanguageChecked.setVisibility(View.INVISIBLE)
            engLanguageChecked.setVisibility(View.VISIBLE)
        } else if (usedLanguage.contains("ru")) {
            engLanguageChecked.setVisibility(View.INVISIBLE)
            lvLanguageChecked.setVisibility(View.INVISIBLE)
            rusLanguageChecked.setVisibility(View.VISIBLE)
        } else if (usedLanguage.contains("lv")) {
            rusLanguageChecked.setVisibility(View.INVISIBLE)
            engLanguageChecked.setVisibility(View.INVISIBLE)
            lvLanguageChecked.setVisibility(View.VISIBLE)
        }
    }

    private fun changeLanguage() {
        val locale = Locale(languageToLoad)
        Locale.setDefault(locale)
        val resources = this.resources
        val configuration = resources.configuration
        configuration.locale = locale
        configuration.setLayoutDirection(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun binding() {
        engSelectedLanguage = findViewById(R.id.eng_selected_language)
        rusSelectedLanguage = findViewById(R.id.rus_selected_language)
        lvSelectedLanguage = findViewById(R.id.lv_selected_language)

        engLanguageChecked = findViewById(R.id.checked_en_language)
        rusLanguageChecked = findViewById(R.id.checked_ru_language)
        lvLanguageChecked = findViewById(R.id.checked_lv_language)

        btnSumbit = findViewById(R.id.button)

    }
}