package com.coding.smkcoding_project_2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coding.smkcoding_project_2.R
import com.coding.smkcoding_project_2.serialized.global.GlobalDataItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_world_data.*
import java.text.NumberFormat
import java.util.*

class WorldDataAdapter (private val context: Context,
                        private val globalData: List<GlobalDataItem>) :
    RecyclerView.Adapter<WorldDataAdapter.ViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_world_data, parent, false)
    )

    override fun getItemCount(): Int {
        return globalData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(globalData[position])
    }

    class ViewHolder (override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(item: GlobalDataItem){

            tvNameCountry.text = item.attributes.countryRegion
            tvJmlPositif.text = NumberFormat.getInstance(Locale.getDefault()).
            format(item.attributes.confirmed)
            tvJmlSembuh.text = NumberFormat.getInstance(Locale.getDefault()).
            format(item.attributes.recovered)
            tvJmlDead.text = NumberFormat.getInstance(Locale.getDefault()).
            format(item.attributes.deaths)
        }
    }
}