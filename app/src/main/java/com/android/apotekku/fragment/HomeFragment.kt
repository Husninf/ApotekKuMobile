package com.android.apotekku.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.android.apotekku.Adapter.AdapterProduk
import com.android.apotekku.R
import com.android.apotekku.adapter.AdapterSlider
import com.android.apotekku.model.Produk

class HomeFragment : Fragment() {

    lateinit var vpSlider: ViewPager
    lateinit var rvProduk: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        vpSlider = view.findViewById(R.id.vp_slider)
        rvProduk = view.findViewById(R.id.rv_produk)

        val arryslider = ArrayList<Int>()
        arryslider.add(R.drawable.slider1)
        arryslider.add(R.drawable.slider2)
        arryslider.add(R.drawable.slider3)

        val adapterSlider = AdapterSlider(arryslider, activity)
        vpSlider.adapter = adapterSlider

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        rvProduk.adapter = AdapterProduk(arryProduk)
        rvProduk.layoutManager = layoutManager

        return view
    }
    val arryProduk: ArrayList<Produk> get() {
        val arry = ArrayList<Produk>() //inisialisasi array penampungan
        val p1 = Produk()
        p1.nama = "Obat 1"   //inisialisasi produk
        p1.harga = "Rp. 10.000"
        p1.gambar = R.drawable.obat1

        val p2 = Produk()
        p2.nama = "Obat 2"
        p2.harga = "Rp. 10.000"
        p2.gambar = R.drawable.obat1

        val p3 = Produk()
        p3.nama = "Obat 3"
        p3.harga = "Rp. 10.000"
        p3.gambar = R.drawable.obat1

        arry.add(p1)
        arry.add(p2)
        arry.add(p3)

        return arry
    }
}