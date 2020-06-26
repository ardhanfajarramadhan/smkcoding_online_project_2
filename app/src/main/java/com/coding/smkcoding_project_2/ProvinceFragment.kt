package com.coding.smkcoding_project_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.coding.smkcoding_project_2.adapter.ProvinceDataAdapter
import com.coding.smkcoding_project_2.data.*
import com.coding.smkcoding_project_2.serialized.IndonesiaDataNew
import com.coding.smkcoding_project_2.serialized.province.ProvinceDataItem
import com.coding.smkcoding_project_2.serialized.province.ProvinceDataNew
import com.coding.smkcoding_project_2.util.dismissLoading
import com.coding.smkcoding_project_2.util.showLoading
import com.coding.smkcoding_project_2.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_province.*
import kotlinx.android.synthetic.main.fragment_province.swipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_world.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*

class ProvinceFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_province, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetProv()
    }

    private fun callApiGetProv() {
        showLoading(context!!, swipeRefreshLayout)

        val httpClient = httpClient()
        val apiRequest = requestProvIndo<ApiService>(httpClient)
        val apiIndo = requestCovidIndo<ApiService>(httpClient)

        val call = apiRequest.getDataProvince()
        val indoDataTotal = apiIndo.getDataIndonesia()

        indoDataTotal.enqueue(object : Callback<IndonesiaDataNew> {

            override fun onFailure(call: Call<IndonesiaDataNew>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<IndonesiaDataNew>, response:
                Response<IndonesiaDataNew>
            ) {
                when {
                    response.isSuccessful ->
                        when {
                            response.body() != null ->
                                setDataIndoTotal(response.body()!!)
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

        call.enqueue(object : Callback<ProvinceDataNew> {

            override fun onFailure(call: Call<ProvinceDataNew>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<ProvinceDataNew>, response:
                Response<ProvinceDataNew>
            ) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body() != null ->
                                tampilDataProv(response.body()!!)
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

    private fun tampilDataProv(dataGlobals: ProvinceDataNew) {
        rvProvinceData.layoutManager = LinearLayoutManager(context!!)
        rvProvinceData.adapter =
            ProvinceDataAdapter(
                context!!,
                dataGlobals
            )
    }

    private fun setDataIndoTotal(item: IndonesiaDataNew){
        tvPositiveProvince.text = NumberFormat.getInstance(Locale.getDefault()).
        format(item.jumlahKasus)
        tvRecoveredProvince.text = NumberFormat.getInstance(Locale.getDefault()).
        format(item.sembuh)
        tvDeathProvince.text = NumberFormat.getInstance(Locale.getDefault()).
        format(item.meninggal)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
