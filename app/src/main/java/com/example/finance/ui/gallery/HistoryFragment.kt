package com.example.finance.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finance.DB.AppDatabase
import com.example.finance.DB.EntryEntity
import com.example.finance.DB.UserEntity
import com.example.finance.R
import com.example.finance.adapter.RvHistoryAdapter
import com.example.finance.databinding.FragmentHistoryBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryFragment(

) : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private lateinit var db: AppDatabase
    private val coroutine = CoroutineScope(Dispatchers.IO)
    lateinit var lvhistory: RecyclerView
    lateinit var btnsearch:Button

    private lateinit var adapter: RvHistoryAdapter
    private lateinit var history: ArrayList<EntryEntity>
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val model: GalleryViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.build(view.context)
        history = arrayListOf()
        lvhistory = view.findViewById(R.id.rvHistory)
        btnsearch = view.findViewById(R.id.btnsearch)

        val user = model.text.toString()
        //adapter
        adapter = RvHistoryAdapter(history,R.layout.history_layout,view.context)
        //fill the array
        refresh()
        btnsearch.setOnClickListener() {
//                Toast.makeText(view.context, user.toString(), Toast.LENGTH_LONG)
//                    .show()
            var tipe = binding.sptipe2.selectedItem.toString()
            var tipetrans = binding.sptipetransaksi2.selectedItem.toString()
                Toast.makeText(view.context,tipe +" "+tipetrans,Toast.LENGTH_LONG)
            //need to make it get per user
            history.clear()
            coroutine.launch {
                if (tipe == "All" && tipetrans == "All"){
                        val a = db.entryDao.getall()
                        if (a != null) {
                            for (i in 0..a.size - 1) {
                                history.add(a[i])
                            }
                        }
                    }
                else if (tipe !="All" && tipetrans == "All"){
                        val a = db.entryDao.getbytipe(tipe)
                        if (a != null) {
                            for (i in 0..a.size - 1) {
                                history.add(a[i])
                            }
                        }
                    }
                else if(tipe == "All" && tipetrans !="All"){
                        val a = db.entryDao.getbytipetrans(tipetrans)
                        if (a != null) {
                            for (i in 0..a.size - 1) {
                                history.add(a[i])
                            }
                        }
                    }
                else{
                        val a = db.entryDao.getbytipeandtrans(tipetrans,tipe)
                        if (a != null) {
                            for (i in 0..a.size - 1) {
                                history.add(a[i])
                            }
                        }
                    }
                }

            lvhistory.getRecycledViewPool().clear();
            adapter.notifyDataSetChanged()
        }

        lvhistory.layoutManager = LinearLayoutManager(view.context)
        lvhistory.adapter = adapter
    }
    private fun refresh(){
        history.clear()
        coroutine.launch {
            val a = db.entryDao.getall()
            if (a != null) {
                for (i in 0..a.size - 1) {
                    history.add(a[i])
                }
            }
        }
        lvhistory.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textGallery
//        galleryViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}