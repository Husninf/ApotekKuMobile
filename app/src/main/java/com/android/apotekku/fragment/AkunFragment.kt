package com.android.apotekku.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.android.apotekku.R
import com.android.apotekku.helper.SharedPref

class AkunFragment : Fragment() {

    lateinit var x: SharedPref
    lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_akun, container, false)
        btnLogout = view.findViewById(R.id.btn_logout)

        x = SharedPref(requireActivity())

        btnLogout.setOnClickListener {
            x.setStatusLogin(false)
        }

        return view
    }
}