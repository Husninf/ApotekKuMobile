package com.android.apotekku.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.apotekku.MainActivity
import com.android.apotekku.app.ApiConfig
import com.android.apotekku.databinding.ActivityRegisterBinding
import com.android.apotekku.helper.SharedPref
import com.android.apotekku.model.ToastHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    lateinit var x:SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        x = SharedPref(this)
        binding.btnRegister.setOnClickListener {
            register()
        }

    }

    fun register() {
        if(binding.etNamaLengkap.text.isEmpty()) {
            binding.etNamaLengkap.error = "Kolom Nama Lengkap Tidak Boleh Kosong"
            binding.etNamaLengkap.requestFocus()
            return
        } else if(binding.etEmail.text.isEmpty()) {
            binding.etEmail.error = "Kolom Email Tidak Boleh Kosong"
            binding.etEmail.requestFocus()
            return
        } else if(binding.etNomor.text.isEmpty()) {
            binding.etNomor.error = "Kolom Nomor Telepon Tidak Boleh Kosong"
            binding.etNomor.requestFocus()
            return
        } else if(binding.etPassword.text.isEmpty()) {
            binding.etPassword.error = "Kolom Password Tidak Boleh Kosong"
            binding.etPassword.requestFocus()
            return
        }

        val namaLengkap = binding.etNamaLengkap.text.toString()
        val email = binding.etEmail.text.toString()
        val nomorTelepon = binding.etNomor.text.toString() // Perhatikan ini belum dipanggil ke API
        val password = binding.etPassword.text.toString()

        //summon progress bar
        val progressBar = binding.pb
        //menampilkan progress bar
        progressBar.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.register(namaLengkap, email, password).enqueue(object : Callback<ToastHandler>{
            override fun onResponse(call: Call<ToastHandler>, response: Response<ToastHandler>) {
                progressBar.visibility = View.GONE
                val merespon = response.body()!!

                if (merespon.success == 1){
                    //menangani ketika berhasil
                    x.setStatusLogin(true)
                    val pindah = Intent(this@RegisterActivity, MainActivity::class.java)
                    //destroy activity agar tidak bisa kembali ke login activity
                    pindah.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(pindah)
                    finish()
                    Toast.makeText(this@RegisterActivity,"Selamat datang "+merespon.user.name, Toast.LENGTH_SHORT).show()

                }else{
                    //menangani ketika gagal
                    Toast.makeText(this@RegisterActivity,merespon.message, Toast.LENGTH_SHORT).show()


                }
            }

            override fun onFailure(call: Call<ToastHandler>, t: Throwable) {
                //menangani ketika gagal
                progressBar.visibility = View.GONE
                Toast.makeText(this@RegisterActivity,"Error "+t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }
}
