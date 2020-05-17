package com.coding.smkcoding_project_2

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.coding.smkcoding_project_2.adapter.WorldDataAdapter
import com.coding.smkcoding_project_2.data.ApiService
import com.coding.smkcoding_project_2.data.apiRequest
import com.coding.smkcoding_project_2.data.httpClient
import com.coding.smkcoding_project_2.serialized.global.GlobalDataItem
import com.coding.smkcoding_project_2.serialized.global.GlobalDataMeninggal
import com.coding.smkcoding_project_2.serialized.global.GlobalDataPositif
import com.coding.smkcoding_project_2.serialized.global.GlobalDataSembuh
import com.coding.smkcoding_project_2.util.dismissLoading
import com.coding.smkcoding_project_2.util.showLoading
import com.coding.smkcoding_project_2.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_world.*
import kotlinx.android.synthetic.main.fragment_world.swipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorldFragment : Fragment() {

    private var globalPositif : String = ""
    private var globalSembuh : String = ""
    private var globalMeninggal : String = ""

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
        val apiRequest = apiRequest<ApiService>(httpClient)

        val call = apiRequest.getDataGlobal()

        val call2 = apiRequest.getDataPositif()
        val call3 = apiRequest.getDataSembuh()
        val call4 = apiRequest.getDataMeninggoy()

        call.enqueue(object : Callback<List<GlobalDataItem>> {

            override fun onFailure(call: Call<List<GlobalDataItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<List<GlobalDataItem>>, response:
                Response<List<GlobalDataItem>>
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

        call2.enqueue(object: Callback<GlobalDataPositif> {
            override fun onFailure(call: Call<GlobalDataPositif>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<GlobalDataPositif>,
                response: Response<GlobalDataPositif>
            ) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body() != null ->
                                globalPositif = response.body()!!.value
                        }
                }
                setDataGlobal()
            }
        })

        call3.enqueue(object: Callback<GlobalDataSembuh> {
            override fun onFailure(call: Call<GlobalDataSembuh>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<GlobalDataSembuh>,
                response: Response<GlobalDataSembuh>
            ) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when{
                            response.body() != null ->
                                globalSembuh = response.body()!!.value
                        }
                }
                setDataGlobal()
            }
        })

        call4.enqueue(object: Callback<GlobalDataMeninggal>{
            override fun onFailure(call: Call<GlobalDataMeninggal>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<GlobalDataMeninggal>,
                response: Response<GlobalDataMeninggal>
            ) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body() != null ->
                                globalMeninggal = response.body()!!.value
                        }
                }
                setDataGlobal()
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

    private fun setDataGlobal(){
        tvPositiveGlobal.text = globalPositif
        tvRecoveredGlobal.text = globalSembuh
        tvDeathGlobal.text = globalMeninggal
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}
