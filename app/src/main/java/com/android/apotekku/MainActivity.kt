package com.android.apotekku

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.android.apotekku.databinding.ActivityMainBinding
import com.android.apotekku.fragment.AkunFragment
import com.android.apotekku.fragment.HomeFragment
import com.android.apotekku.fragment.KeranjangFragment


class MainActivity : AppCompatActivity() {

    private val fragmentHome: Fragment = HomeFragment()
    private val fragmentAkun: Fragment = AkunFragment()
    private var fragmentKeranjang: Fragment = KeranjangFragment()
    private val fm: FragmentManager = supportFragmentManager
    private var active: Fragment = fragmentHome

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpBottomNav()
    }


    fun setUpBottomNav() {
        fm.beginTransaction().apply {
            add(R.id.container, fragmentHome).show(fragmentHome)
            add(R.id.container, fragmentAkun).hide(fragmentAkun)
            add(R.id.container, fragmentKeranjang).hide(fragmentKeranjang)
        }.commit()

        val bottomNavigationView = binding.navView // Akses tampilan melalui binding
        val menu = bottomNavigationView.menu
        val menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    summonFragment(0, fragmentHome)
                    true
                }
                R.id.navigation_keranjang -> {
                    summonFragment(1, fragmentKeranjang)
                    true
                }
                R.id.navigation_akun -> {
                    summonFragment(2, fragmentAkun)
                    true
                }
                else -> false
            }
        }
    }

    fun summonFragment(int: Int, fragment: Fragment){
        val menuItem = binding.navView.menu.getItem(int)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}
