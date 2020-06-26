package com.coding.smkcoding_project_2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide.init
import com.coding.smkcoding_project_2.adapter.TutorialAdapter
import com.coding.smkcoding_project_2.model.TutorialModel
import com.coding.smkcoding_project_2.viewmodel.TutorialFragmentViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_tutorial.*

/**
 * A simple [Fragment] subclass.
 */
class TutorialFragment : Fragment() {

    lateinit var ref : DatabaseReference
    lateinit var auth: FirebaseAuth
    lateinit var dataTutorial: ArrayList<TutorialModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutorial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()

        fab.setOnClickListener {
            val intent = Intent(getActivity(), AddTutorialActivity::class.java)
            getActivity()?.startActivity(intent)
        }
    }

    private fun getData() {
        //Mendapatkan Referensi Database
        Toast.makeText(getContext(), "Mohon Tunggu Sebentar...", Toast.LENGTH_LONG).show()
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
        ref = FirebaseDatabase.getInstance().getReference()
        ref.child(getUserID).child("Tutorial").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(getContext(), "Database Error yaa...", Toast.LENGTH_LONG).show()
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                Inisialisasi ArrayList
                dataTutorial = java.util.ArrayList<TutorialModel>()
                for (snapshot in dataSnapshot.children) {
                    val tutorial = snapshot.getValue(TutorialModel::class.java)
                    tutorial?.setKey(snapshot.key)
                    dataTutorial.add(tutorial!!)
                }
                rv_listTutorialMu.layoutManager = LinearLayoutManager(context)
                rv_listTutorialMu.adapter = TutorialAdapter(context!!, dataTutorial)
                Toast.makeText(getContext(), "Data Berhasil Dimuat",
                    Toast.LENGTH_LONG).show()
            }
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}

private fun TutorialModel?.setKey(key: String?) {
}
