package com.coding.smkcoding_project_2.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.coding.smkcoding_project_2.R
import com.coding.smkcoding_project_2.UpdateTutorialActivity
import com.coding.smkcoding_project_2.model.TutorialModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_tutorial.*
import kotlin.collections.ArrayList

class TutorialAdapter (private val context: Context, private val list: ArrayList<TutorialModel>) :
    RecyclerView.Adapter<TutorialAdapter.ViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_tutorial, parent, false)
    )

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list)

        val Judul: String? = list[position].judul
        val Deskripsi: String? = list[position].deskripsi
        val Uploader: String? = list[position].namaUpload

        holder.judul.setText("Judul: $Judul")
        holder.deskripsi.setText("Deskripsi: $Deskripsi")
        holder.namaUploader.setText("Uploader: $Uploader")

        lateinit var ref : DatabaseReference
        lateinit var auth: FirebaseAuth

//        holder.cv_item_tutorial.setOnLongClickListener(View.OnLongClickListener { view -> true })

//
//        holder.cv_item_tutorial.setOnLongClickListener(View.OnLongClickListener { view ->
//            val action = arrayOf("Update", "Delete")
//            val alert = AlertDialog.Builder(context)
//            alert.setItems(action) { dialog, i ->
//                when (i) {
//                    0 -> {
//                        /* Berpindah Activity pada halaman layout updateData dan mengambil data pada listMahasiswa, berdasarkan posisinya untuk dikirim pada activity selanjutnya
//                         */
//                        val bundle = Bundle()
//                        bundle.putString("dataJudul", list[position].judul)
//                        bundle.putString("dataDeskripsi", list[position].deskripsi)
//                        bundle.putString("dataUploader", list[position].namaUpload)
//                        bundle.putString("getPrimaryKey", list[position].key)
//                        val intent = Intent(context, UpdateTutorialActivity::class.java)
//                        intent.putExtras(bundle)
//                        context.startActivity(intent)
//                    }
//                    1 -> {
//                        auth = FirebaseAuth.getInstance()
//                        ref = FirebaseDatabase.getInstance().getReference()
//                        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
//                        if (ref != null) {
//                            ref.child(getUserID)
//                                .child("Teman")
//                                .child(list.get(position)?.getKey().toString())
//                                .removeValue()
//                                .addOnSuccessListener {
//                                    Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
//
//                                }
//                        }
//                    }
//                    alert.create()
//                            alert.show()
//                        true
//                })
        }

    class ViewHolder (override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(item: ArrayList<TutorialModel>){
            judul.text = item[position].judul
            deskripsi.text = item[position].deskripsi
            namaUploader.text = item[position].namaUpload

        }
    }

}