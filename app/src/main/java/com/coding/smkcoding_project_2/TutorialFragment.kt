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
            val intent = Intent(activity, AddTutorialActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    private fun getData() {
        //Mendapatkan Referensi Database
        Toast.makeText(context, "Mohon Tunggu Sebentar...", Toast.LENGTH_LONG).show()
        auth = FirebaseAuth.getInstance()
        ref = FirebaseDatabase.getInstance().reference
        ref.child("Tutorial").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, "Database Error yaa...", Toast.LENGTH_LONG).show()
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataTutorial = java.util.ArrayList<TutorialModel>()
                for (snapshot in dataSnapshot.children) {
                    val tutorial = snapshot.getValue(TutorialModel::class.java)
                    tutorial?.key = snapshot.key.toString()
                    dataTutorial.add(tutorial!!)
                }
                rv_listTutorialMu.layoutManager = LinearLayoutManager(context)
                rv_listTutorialMu.adapter = TutorialAdapter(context!!, dataTutorial)
                Toast.makeText(context, "Data Berhasil Dimuat", Toast.LENGTH_LONG).show()
            }
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}


