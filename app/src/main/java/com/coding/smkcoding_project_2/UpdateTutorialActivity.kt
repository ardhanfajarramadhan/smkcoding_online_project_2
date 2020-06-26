package com.coding.smkcoding_project_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.coding.smkcoding_project_2.model.TutorialModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateTutorialActivity : AppCompatActivity() {

    private var judulNew: EditText? = null
    private var deskripsiNew: EditText? = null
    private var namaUploadNew: EditText? = null
    lateinit var update: Button
    private var database: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var cekJudul: String? = null
    private var cekDeskripsi: String? = null
    private var cekNamaUpload: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_tutorial)
        getSupportActionBar()?.setTitle("Update Data")
        judulNew = findViewById(R.id.new_judul)
        deskripsiNew = findViewById(R.id.new_deskripsi)
        namaUploadNew = findViewById(R.id.new_uploader)
        update = update

        //Mendapatkan Instance autentikasi dan Referensi dari Database
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        getData();

        update.setOnClickListener{
            //Mendapatkan Data Mahasiswa yang akan dicek
            cekJudul = judulNew?.getText().toString()
            cekDeskripsi = deskripsiNew?.getText().toString()
            cekNamaUpload = namaUploadNew?.getText().toString()

            //Mengecek agar tidak ada data yang kosong, saat proses update
            if (isEmpty(cekJudul) || isEmpty(cekDeskripsi) || isEmpty(cekNamaUpload)) {
                Toast.makeText(this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
            } else {
                /* Menjalankan proses update data.
                  Method Setter digunakan untuk mendapakan data baru yang diinputkan User.
                */
                val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
                val tutorialBaru = TutorialModel(cekJudul!!, cekDeskripsi!!, cekNamaUpload!!, getUserID)
                val getKey: String = getIntent().getStringExtra("getPrimaryKey").toString()
                database!!.child(getUserID).child("Tutorial")
                    .child(getKey).setValue(tutorialBaru)
                    .addOnCompleteListener {
                        Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                        finish();
                    }
            }
        }

    }

    private fun getData() {
        //Menampilkan data dari item yang dipilih sebelumnya
        val getNama: String  = getIntent().getStringExtra("dataJudul").toString()
        val getEmail: String  = getIntent().getExtras()?.getString("dataDeskripsi").toString()
        val getTelp: String  = getIntent().getExtras()?.getString("dataUploader").toString()
        judulNew?.setText(getNama);
        deskripsiNew?.setText(getEmail);
        namaUploadNew?.setText(getTelp);
        Toast.makeText(this, getNama, Toast.LENGTH_SHORT).show()

    }

}
