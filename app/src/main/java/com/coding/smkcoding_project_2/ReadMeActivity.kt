package com.coding.smkcoding_project_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListView
import com.coding.smkcoding_project_2.adapter.ExpandableListViewAdapter
import kotlinx.android.synthetic.main.activity_read_me.*

class ReadMeActivity : AppCompatActivity() {

    val title: MutableList<String> = ArrayList()
    val subTitle: MutableList<MutableList<String>> = ArrayList()
    lateinit var expandableListView: ExpandableListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_me)

        expandableListView = expandListView
        val isiDunia: MutableList<String> = ArrayList()
        isiDunia.add("Fitur ini menampilkan data Covid-19 di seluruh dunia")
        val isiProvinsi: MutableList<String> = ArrayList()
        isiProvinsi.add("Fitur ini menampilkan data Positif, Sembuh, dan Meninggal Covid-19 di Provinsi Indonesia")
        val isiDiagram: MutableList<String> = ArrayList()
        isiDiagram.add("Fitur ini menampilkan perbandingan diagram Covid-19 di Indonesia")
        val isiWeb: MutableList<String> = ArrayList()
        isiWeb.add("Fitur ini menampilkan sebuah web milik google berita tentang Covid-19")
        val isiTips: MutableList<String> = ArrayList()
        isiTips.add("Fitur ini menampilkan tips mencegah penularan Covid-19")

        title.add("Tab Dunia")
        subTitle.add(isiDunia)
        title.add("Tab Provinsi")
        subTitle.add(isiProvinsi)
        title.add("Tab Diagram Covid-19 Di Indonesia")
        subTitle.add(isiDiagram)
        title.add("Tab Web")
        subTitle.add(isiWeb)
        title.add("Tab Tips")
        subTitle.add(isiTips)

        expandableListView.setAdapter(ExpandableListViewAdapter(this, title, subTitle))
    }
}
