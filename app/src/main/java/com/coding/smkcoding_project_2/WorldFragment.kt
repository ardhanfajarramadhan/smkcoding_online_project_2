package com.coding.smkcoding_project_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.coding.smkcoding_project_2.adapter.WorldDataAdapter
import com.coding.smkcoding_project_2.data.ApiService
import com.coding.smkcoding_project_2.data.apiRequest
import com.coding.smkcoding_project_2.data.httpClient
import com.coding.smkcoding_project_2.serialized.global.GlobalDataItem
import com.coding.smkcoding_project_2.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_world.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorldFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_world, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetGlobal()
    }

    private fun callApiGetGlobal() {

        val httpClient = httpClient()
        val apiRequest = apiRequest<ApiService>(httpClient)

        val call = apiRequest.getDataGlobal()
        call.enqueue(object : Callback<List<GlobalDataItem>> {

            override fun onFailure(call: Call<List<GlobalDataItem>>, t: Throwable) {
//                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<List<GlobalDataItem>>, response:
                Response<List<GlobalDataItem>>
            ) {
//                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilDataGlobal(response.body()!!)
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

    private fun tampilDataGlobal(dataGlobals: List<GlobalDataItem>) {
        rvGlobalData.layoutManager = LinearLayoutManager(context!!)
        rvGlobalData.adapter =
            WorldDataAdapter(
                context!!,
                dataGlobals
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}
