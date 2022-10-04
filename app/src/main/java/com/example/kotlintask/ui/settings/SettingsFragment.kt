package com.example.kotlintask.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kotlintask.LanguageActivity
import com.example.kotlintask.MainActivity
import com.example.kotlintask.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private lateinit var selectedLanguage : TextView
    private lateinit var authorizeBtn : Button

    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding()

        openLanguageActivity()

        return root
    }

    private fun openLanguageActivity() {
        selectedLanguage.setOnClickListener{
            val intent = Intent(activity , LanguageActivity::class.java)
            startActivity(intent)
        }
    }

    private fun binding(){
        selectedLanguage = binding.selectLanguage
        authorizeBtn = binding.authorizeBtn
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}