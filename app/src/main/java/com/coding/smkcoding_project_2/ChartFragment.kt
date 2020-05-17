package com.coding.smkcoding_project_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.coding.smkcoding_project_2.data.ApiService
import com.coding.smkcoding_project_2.data.apiRequest
import com.coding.smkcoding_project_2.data.httpClient
import com.coding.smkcoding_project_2.serialized.IndonesiaDataItem
import com.coding.smkcoding_project_2.util.dismissLoading
import com.coding.smkcoding_project_2.util.showLoading
import kotlinx.android.synthetic.main.fragment_chart.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class ChartFragment : Fragment() {

    private var indPos : String = "6"
    private var indSem : String = "3"
    private var indMen : String = "1"
    private var indRaw : String = "4"

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
        callDataIndonesia()
        super.onViewCreated(view, savedInstanceState)
        chart()
    }

    private fun callDataIndonesia(){
        showLoading(context!!, refreshData)

        val httpClient = httpClient()
        val apiReq = apiRequest<ApiService>(httpClient)

        val call = apiReq.getDataIndonesia()

        call.enqueue(object: Callback<IndonesiaDataItem> {
            override fun onFailure(call: Call<IndonesiaDataItem>, t: Throwable) {
                dismissLoading(refreshData)
            }

            override fun onResponse(
                call: Call<IndonesiaDataItem>,
                response: Response<IndonesiaDataItem>
            ) {
                dismissLoading(refreshData)
                if (response.isSuccessful)
                    if (response.body() != null)
                        indPos = response.body()!!.positif
                        indSem = response.body()!!.sembuh
                        indMen = response.body()!!.meninggal
                        indRaw = response.body()!!.dirawat
                        chart()
                chart()
            }
        })
    }

    private fun chart(){
        val positif = indPos.replace("\\s".toRegex(), ",")
        val sembuh = indSem.replace("\\s".toRegex(), ",")
        val meninggal = indMen.replace("\\s".toRegex(), ",")
        val dirawat = indRaw.replace("\\s".toRegex(), ",")

        val posit: Int? = positif.toInt()
        val sembu: Int? = sembuh.toInt()
        val menin: Int? = meninggal.toInt()
        val rawat: Int? = dirawat.toInt()

        val pie = AnyChart.pie()

        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("Positif", posit))
        data.add(ValueDataEntry("Sembuh",sembu))
        data.add(ValueDataEntry("Meninggal", menin))
        data.add(ValueDataEntry("Dirawat", rawat))

        pie.data(data)

        val anyChartView = any_chart_view as AnyChartView
        anyChartView.setChart(pie)
    }
}
