package com.coding.smkcoding_project_2

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.coding.smkcoding_project_2.adapter.WorldDataAdapter
import com.coding.smkcoding_project_2.data.ApiService
import com.coding.smkcoding_project_2.data.httpClient
import com.coding.smkcoding_project_2.data.requestApi
import com.coding.smkcoding_project_2.serialized.global.*
import com.coding.smkcoding_project_2.util.dismissLoading
import com.coding.smkcoding_project_2.util.showLoading
import com.coding.smkcoding_project_2.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_world.*
import kotlinx.android.synthetic.main.fragment_world.swipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*

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
        callApiGetGlobal()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun callApiGetGlobal() {
        showLoading(context!!, swipeRefreshLayout)

        val httpClient = httpClient()
        val apiRequest = requestApi<ApiService>(httpClient)

        val call = apiRequest.getDataGlobal()

        val totalWorld = apiRequest.getDataTotalWorld()

        call.enqueue(object : Callback<GlobalDataNew> {

            override fun onFailure(call: Call<GlobalDataNew>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<GlobalDataNew>, response:
                Response<GlobalDataNew>
            ) {
                dismissLoading(swipeRefreshLayout)
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

        totalWorld.enqueue(object: Callback<getGlobalDataNew> {
            override fun onFailure(call: Call<getGlobalDataNew>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<getGlobalDataNew>,
                response: Response<getGlobalDataNew>
            ) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body() != null ->
                                setDataGlobal(response.body()!!)
                        }
                }
            }
        })
    }

    private fun tampilDataGlobal(dataGlobals: GlobalDataNew) {
        rvGlobalData.layoutManager = LinearLayoutManager(context!!)
        rvGlobalData.adapter =
            WorldDataAdapter(
                context!!,
                dataGlobals
            )
    }

    private fun setDataGlobal(item: getGlobalDataNew){
        tvPositiveGlobal.text = NumberFormat.getInstance(Locale.getDefault()).
        format(item.confirmed.value)
        tvRecoveredGlobal.text = NumberFormat.getInstance(Locale.getDefault()).
        format(item.recovered.value)
        tvDeathGlobal.text = NumberFormat.getInstance(Locale.getDefault()).
        format(item.deaths.value)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}
