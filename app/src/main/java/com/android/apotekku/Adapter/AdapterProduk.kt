package com.android.apotekku.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.apotekku.R
import com.android.apotekku.model.Produk

class AdapterProduk(var data: ArrayList<Produk>):RecyclerView.Adapter<AdapterProduk.Holder>() {

    class Holder(view: View):RecyclerView.ViewHolder(view){
        val tvNama = view.findViewById<TextView>(R.id.tv_namaObat)
        val tvHarga = view.findViewById<TextView>(R.id.tv_hargaObat)
        val imgObat = view.findViewById<ImageView>(R.id.img_obat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterProduk.Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: AdapterProduk.Holder, position: Int) {
        holder.tvNama.text = data[position].nama
        holder.tvHarga.text = data[position].harga
        holder.imgObat.setImageResource(data[position].gambar)
    }

    override fun getItemCount(): Int {
        return data.size
        //menghitung berapa banyak data yang akan ditampilkan
    }
}