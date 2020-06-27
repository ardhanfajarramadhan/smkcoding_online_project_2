package com.coding.smkcoding_project_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.coding.smkcoding_project_2.model.TutorialModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_tutorial.*

class AddTutorialActivity : AppCompatActivity() {

    private var Judul: EditText? = null
    private var Deskripsi: EditText? = null
    private var NamaUpload: EditText? = null
    lateinit var ref : DatabaseReference
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tutorial)
        supportActionBar?.title = "Upload Tutorial"

        Judul = etJudulAdd
        Deskripsi = etDeskripsiAdd
        NamaUpload = etnamaUploadAdd
        ref = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        simpan.setOnClickListener {
            prosesSave()
        }
    }

    private fun prosesSave() {
        val getJudul: String = Judul?.text.toString()
        val getDeskripsi: String = Deskripsi?.text.toString()
        val getNamaUpload: String = NamaUpload?.text.toString()
        val getUserID: String = auth?.currentUser?.uid.toString()

        if (getJudul.isEmpty() && getDeskripsi.isEmpty() && getNamaUpload.isEmpty()) {

            Toast.makeText(this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show()
        } else {
            val tutorial = TutorialModel(getJudul, getDeskripsi, getNamaUpload, getUserID)

            ref.child("Tutorial").push().setValue(tutorial).addOnCompleteListener {
                Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
