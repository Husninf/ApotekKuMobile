package com.android.apotekku.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.apotekku.databinding.ActivityMasukBinding
import com.android.apotekku.helper.SharedPref

class MasukActivity : AppCompatActivity() {

    lateinit var x:SharedPref
    lateinit var binding: ActivityMasukBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMasukBinding.inflate(layoutInflater)
        setContentView(binding.root)

        x = SharedPref(this)
        mainButton()

    }

    private fun mainButton(){
        binding.btnProsesLogin.setOnClickListener {
            x.setStatusLogin(true)
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
