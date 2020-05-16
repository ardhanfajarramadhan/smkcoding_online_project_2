package com.coding.smkcoding_project_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.coding.smkcoding_project_2.adapter.ProvinceDataAdapter
import com.coding.smkcoding_project_2.data.ApiService
import com.coding.smkcoding_project_2.data.apiRequest
import com.coding.smkcoding_project_2.data.httpClient
import com.coding.smkcoding_project_2.serialized.province.ProvinceDataItem
import com.coding.smkcoding_project_2.util.dismissLoading
import com.coding.smkcoding_project_2.util.showLoading
import com.coding.smkcoding_project_2.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_province.*
import kotlinx.android.synthetic.main.fragment_province.swipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val apiRequest = apiRequest<ApiService>(httpClient)

        val call = apiRequest.getDataProvince()
        call.enqueue(object : Callback<List<ProvinceDataItem>> {

            override fun onFailure(call: Call<List<ProvinceDataItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<List<ProvinceDataItem>>, response:
                Response<List<ProvinceDataItem>>
            ) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
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

    private fun tampilDataProv(dataGlobals: List<ProvinceDataItem>) {
        rvProvinceData.layoutManager = LinearLayoutManager(context!!)
        rvProvinceData.adapter =
            ProvinceDataAdapter(
                context!!,
                dataGlobals
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
