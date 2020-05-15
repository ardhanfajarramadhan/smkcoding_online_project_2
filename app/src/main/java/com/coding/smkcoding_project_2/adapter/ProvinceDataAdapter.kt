package com.coding.smkcoding_project_2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coding.smkcoding_project_2.R
import com.coding.smkcoding_project_2.serialized.province.ProvinceDataItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_province_data.*
import java.text.NumberFormat
import java.util.*

class ProvinceDataAdapter (private val context: Context,
                           private val provinceData: List<ProvinceDataItem>) :
    RecyclerView.Adapter<ProvinceDataAdapter.ViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_province_data, parent, false)
    )

    override fun getItemCount(): Int {
        return provinceData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(provinceData[position])
    }

    class ViewHolder (override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(item: ProvinceDataItem){

            tvNameProv.text = item.attributes.provinsi
            tvPositif.text = NumberFormat.getInstance(Locale.getDefault()).
            format(item.attributes.kasusPosi)
            tvSembuh.text = NumberFormat.getInstance(Locale.getDefault()).
            format(item.attributes.kasusSemb)
            tvMeninggal.text = NumberFormat.getInstance(Locale.getDefault()).
            format(item.attributes.kasusMeni)
        }
    }
}