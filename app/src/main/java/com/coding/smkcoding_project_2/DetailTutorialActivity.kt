package com.coding.smkcoding_project_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_detail_tutorial.*
import kotlinx.android.synthetic.main.item_tutorial.*

class DetailTutorialActivity : AppCompatActivity() {

    private var title: TextView? = null
    private var desc: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tutorial)

        title = title_tutorial
        desc = desc_tutorial
        getData()

    }

    private fun getData() {
        val getNama: String  = intent.getStringExtra("dataJudul").toString()
        val getEmail: String  = intent.extras?.getString("dataDeskripsi").toString()
        title?.text = getNama
        desc?.text = getEmail
    }
}
