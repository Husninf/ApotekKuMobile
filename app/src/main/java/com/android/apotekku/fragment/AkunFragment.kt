package com.android.apotekku.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.apotekku.MainActivity
import com.android.apotekku.R
import com.android.apotekku.activity.LoginActivity
import com.android.apotekku.databinding.FragmentAkunBinding
import com.android.apotekku.helper.SharedPref

class AkunFragment : Fragment() {

    lateinit var x: SharedPref
    private var _binding: FragmentAkunBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAkunBinding.inflate(inflater, container, false)
        x = SharedPref(requireActivity())

        binding.txtLogout.setOnClickListener {
            x.setStatusLogin(false)
        }
        setData()
        return binding.root
    }

    fun setData(){
        if (x.getUser() == null){
            //ketika data bernilai null maka akan diarahkan ke halaman login
            val pindah = Intent(activity, LoginActivity::class.java)
            //destroy activity agar tidak bisa kembali ke login activity
            pindah.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(pindah)
            return
        }
        //jika tidak null maka akan lanjut mengambil data user yang sebelumnya telah disimpan
        val user = x.getUser()!!
        binding.txtNamaUser.text = user.name
        binding.txtEmail.text = user.email
        binding.txtNomor.text = user.phone

        //binding.txtNamaUser.text = x.getString(x.nama)
        //binding.txtEmail.text = x.getString(x.email)
        //binding.txtNomor.text = x.getString(x.phone)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}