package com.coding.smkcoding_project_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.coding.smkcoding_project_2.model.TutorialModel
import com.coding.smkcoding_project_2.viewmodel.TutorialUpdateViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_update_tutorial.*

class UpdateTutorialActivity : AppCompatActivity() {

    private var judulNew: EditText? = null
    private var deskripsiNew: EditText? = null
    private var sumberNew: EditText? = null
    lateinit var update: Button
    private var database: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var cekJudul: String? = null
    private var cekDeskripsi: String? = null
    private var cekNamaUpload: String? = null

    private val viewModel by viewModels<TutorialUpdateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_tutorial)
        supportActionBar?.title = "Update Data"

        viewModel.init(this)

        judulNew = judul_new
        deskripsiNew = deskripsi_new
        sumberNew = sumber_new
        update = btn_update

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
        getData()

        update.setOnClickListener{
            cekJudul = judulNew?.text.toString()
            cekDeskripsi = deskripsiNew?.text.toString()
            cekNamaUpload = sumberNew?.text.toString()

            if (isEmpty(cekJudul) || isEmpty(cekDeskripsi) || isEmpty(cekNamaUpload)) {
                Toast.makeText(this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show()
            } else {
                val getKey: String = intent.getStringExtra("getPrimaryKey").toString()
                val tutorialBaru = TutorialModel(cekJudul!!, cekDeskripsi!!, cekNamaUpload!!, getKey)
                database!!.child("Tutorial")
                    .child(getKey).setValue(tutorialBaru)
                    .addOnCompleteListener {
                        viewModel.updateData(tutorialBaru)
                        Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                        finish()
                    }
            }
        }

    }

    private fun getData() {
        //Menampilkan data dari item yang dipilih sebelumnya
        val getNama: String  = intent.getStringExtra("dataJudul").toString()
        val getEmail: String  = intent.extras?.getString("dataDeskripsi").toString()
        val getTelp: String  = intent.extras?.getString("dataUploader").toString()
        judulNew?.setText(getNama)
        deskripsiNew?.setText(getEmail)
        sumberNew?.setText(getTelp)
        Toast.makeText(this, getNama, Toast.LENGTH_SHORT).show()

    }

}
