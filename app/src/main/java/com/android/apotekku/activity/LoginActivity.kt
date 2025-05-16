package com.android.apotekku.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.android.apotekku.R
import com.android.apotekku.databinding.ActivityLoginBinding
import com.android.apotekku.databinding.ActivityMainBinding
import com.android.apotekku.helper.SharedPref

class LoginActivity : AppCompatActivity() {

    lateinit var x:SharedPref
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        x = SharedPref(this)

        binding.btnProsesLogin.setOnClickListener {
        x.setStatusLogin(true)
        }
        }
    }
