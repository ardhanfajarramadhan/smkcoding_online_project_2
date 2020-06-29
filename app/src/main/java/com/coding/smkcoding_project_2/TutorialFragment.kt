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
    var dataTutorial: MutableList<TutorialModel> = ArrayList()
    private val viewModel by viewModels<TutorialFragmentViewModel>()
    private var adapter: TutorialAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutorial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getData()

        viewModel.init(requireContext())
        viewModel.allTutorials.observe(viewLifecycleOwner, Observer { tutorialY ->
            // Update the cached copy of the words in the adapter.
            tutorialY?.let { adapter }
        })

        fab.setOnClickListener {
            val intent = Intent(activity, AddTutorialActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    private fun init() {
        rv_listTutorialMu.layoutManager = LinearLayoutManager(context)
        rv_listTutorialMu.adapter = TutorialAdapter(requireContext(), dataTutorial)
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
                dataTutorial = ArrayList()
                for (snapshot in dataSnapshot.children) {
                    //Mapping data pada DataSnapshot ke dalam objek mahasiswa
                    val tutorial = snapshot.getValue(TutorialModel::class.java)
                    //Mengambil Primary Key, digunakan untuk proses Update dan Delete
                    tutorial?.key = (snapshot.key!!.toString())
                    init()
                    dataTutorial.add(tutorial!!)
                }
                viewModel.insertAll(dataTutorial)
            }
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

    fun onDeleteData(data: TutorialModel, position: Int) {
        /*
         * Kode ini akan dipanggil ketika method onDeleteData
         * dipanggil dari adapter pada RecyclerView melalui interface.
         * kemudian akan menghapus data berdasarkan primary key dari data tersebut
         * Jika berhasil, maka akan memunculkan Toast
         */
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth.currentUser?.uid.toString()
        if (ref != null) {
            ref.child(getUserID)
                .child("Tutorial")
                .child(data.key.toString())
                .removeValue()
                .addOnSuccessListener {
                    Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                    viewModel.delete(data)
                }
        } else {
            Toast.makeText(context, data.key.toString(), Toast.LENGTH_LONG).show()
        }
    }

}


