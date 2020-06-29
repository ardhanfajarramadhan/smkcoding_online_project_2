package com.coding.smkcoding_project_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.charts.Pie
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.coding.smkcoding_project_2.data.ApiService
import com.coding.smkcoding_project_2.data.httpClient
import com.coding.smkcoding_project_2.data.requestCovidIndo
import com.coding.smkcoding_project_2.serialized.IndonesiaDataNew
import com.coding.smkcoding_project_2.util.showLoading
import com.coding.smkcoding_project_2.util.tampilToast
import kotlinx.android.synthetic.main.fragment_chart.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ChartFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val httpClient = httpClient()
        val apiIndo = requestCovidIndo<ApiService>(httpClient)
        val indoDataTotal = apiIndo.getDataIndonesia()

        indoDataTotal.enqueue(object : Callback<IndonesiaDataNew> {

            override fun onFailure(call: Call<IndonesiaDataNew>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<IndonesiaDataNew>, response:
                Response<IndonesiaDataNew>
            ) {
                when {
                    response.isSuccessful ->
                        when {
                            response.body() != null ->
                                setAnyChart(response.body()!!)
                            else -> {
                                tampilToast(context!!, "Berhasil")
                            }
                        }
                    else -> {
                        tampilToast(context!!, "Gagal")
                    }
                }
            }
        })
    }

    private fun setAnyChart(item: IndonesiaDataNew){
        val pie = AnyChart.pie()
        val data: MutableList<DataEntry> = ArrayList()
        if (item == null){
            data.add(ValueDataEntry("Positif", 6))
            data.add(ValueDataEntry("Sembuh", 3))
            data.add(ValueDataEntry("Meninggal", 1))
            data.add(ValueDataEntry("Rawat", 2))
        }
        data.add(ValueDataEntry("Positif", item.jumlahKasus))
        data.add(ValueDataEntry("Sembuh", item.sembuh))
        data.add(ValueDataEntry("Meninggal", item.meninggal))
        data.add(ValueDataEntry("Rawat", item.perawatan))

        pie.data(data)

        val anyChartView = any_chart_view as AnyChartView
        anyChartView.setChart(pie)


    }
}
