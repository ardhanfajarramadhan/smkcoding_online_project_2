package com.coding.smkcoding_project_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_my_friend.*

/**
 * A simple [Fragment] subclass.
 */
class MyFriendFragment : Fragment() {

    lateinit var listTeman : ArrayList<MyFriend>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_friend, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun simulasiDataTeman(){
        listTeman = ArrayList()
        listTeman.add(MyFriend("Alif", "Laki-laki", "alif@gmail.com", "0912091092", "Malang"))
        listTeman.add(MyFriend("Dege", "Perempuan", "XC@gmail.com", "121212", "Malang"))
        listTeman.add(MyFriend("Diggue", "Laki-laki", "De@gmail.com", "2323232", "Malang"))
    }

    private fun tampilTeman(){
        rv_listMyFriend.layoutManager = LinearLayoutManager(activity)
        rv_listMyFriend.adapter = MyFriendAdapter(activity!!, listTeman)
    }

    private fun initView(){
        simulasiDataTeman()
        tampilTeman()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}
