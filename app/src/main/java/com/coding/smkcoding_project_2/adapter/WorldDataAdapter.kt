package com.coding.smkcoding_project_2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coding.smkcoding_project_2.R
import com.coding.smkcoding_project_2.serialized.global.GlobalDataNew
import com.coding.smkcoding_project_2.serialized.global.GlobalDataNewItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_world_data.*
import java.text.NumberFormat
import java.util.*

class WorldDataAdapter (private val context: Context,
                        private val globalData: GlobalDataNew
) :
    RecyclerView.Adapter<WorldDataAdapter.ViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_world_data, parent, false)
    )

    override fun getItemCount(): Int {
        return globalData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(globalData.get(position))
    }

    class ViewHolder (override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(item: GlobalDataNewItem){

            tvNameCountry.text = item.countryRegion
            tvJmlPositif.text = NumberFormat.getInstance(Locale.getDefault()).
            format(item.confirmed)
            tvJmlSembuh.text = NumberFormat.getInstance(Locale.getDefault()).
            format(item.recovered)
            tvJmlDead.text = NumberFormat.getInstance(Locale.getDefault()).
            format(item.deaths)
        }
    }
}