package com.example.finance.ui.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.finance.DB.AppDatabase
import com.example.finance.DB.UserEntity
import com.example.finance.R
import com.example.finance.databinding.ActivityLoginBinding
import com.example.finance.databinding.ActivityRegisterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var db: AppDatabase
    private val coroutine = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = AppDatabase.build(this)

        binding.btnHave.setOnClickListener(){
            finish()
        }

        binding.btnRegister.setOnClickListener(){
            val username = binding.txtreguser.text.toString()
            val dispname = binding.txtregdisplay.text.toString()
            val email = binding.txtregemail.text.toString()
            val pass = binding.txtregpass.text.toString()
            val conpass =binding.txtregconpass.text.toString()

            if (pass ==conpass){
                val user = UserEntity(
                    username = username,
                    name = dispname,
                    email =email,
                    password = pass
                )
                coroutine.launch {
                    insert(user)
                }
                finish()
            }else{
                Toast.makeText(this@RegisterActivity, "Confirmation password and password is not the same", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
    private fun insert(user: UserEntity) {

        coroutine.launch {
            if (db.userDao.get(user.username) != null) {
                runOnUiThread {
                    Toast.makeText(this@RegisterActivity, "Username not unique", Toast.LENGTH_LONG)
                        .show()
                }
            }else {
                db.userDao.insert(user)
                runOnUiThread {
                    Toast.makeText(this@RegisterActivity, "Insert new user", Toast.LENGTH_LONG).show()
                }
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