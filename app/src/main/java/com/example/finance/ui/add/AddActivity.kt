package com.example.finance.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.finance.DB.AppDatabase
import com.example.finance.DB.EntryEntity
import com.example.finance.DB.UserEntity
import com.example.finance.R
import com.example.finance.databinding.ActivityAddBinding
import com.example.finance.databinding.ActivityRegisterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private lateinit var db: AppDatabase
    private val coroutine = CoroutineScope(Dispatchers.IO)
    lateinit var user: UserEntity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = AppDatabase.build(this)

        val userlogged = intent.getParcelableExtra<UserEntity>("user")
        if (userlogged!=null){
            user = userlogged
        }

        binding.btnaddtrans.setOnClickListener(){
            val cur = binding.edtcur.text.toString()
            val total = binding.edtotal.text.toString().toInt()
            val tipe = binding.sptipe.selectedItem.toString()
            val tipetrans = binding.sptipetransaksi.selectedItem.toString()
            val note = binding.edtnote.text.toString()

            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
            val current = LocalDateTime.now().format(formatter)

            val newEntry = EntryEntity(
                id=null,
                currency = cur,
                total = total,
                tipe = tipe,
                tipetransaksi = tipetrans,
                note= note,
                date = current,
                user = user.username
            )
            coroutine.launch{
                db.entryDao.insert(newEntry)
                Toast.makeText(this@AddActivity, "New Entry added", Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.backmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.back_menu-> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}