package com.example.finance.ui.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.finance.DB.AppDatabase
import com.example.finance.MainActivity
import com.example.finance.R
import com.example.finance.databinding.ActivityLoginBinding
import com.example.finance.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: AppDatabase
    private val coroutine = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = AppDatabase.build(this)

        binding.btnreg.setOnClickListener(){
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnlogin.setOnClickListener(){
            var username = binding.txtloginuser2.text.toString()
            var pass = binding.txtloginpass.text.toString()

            coroutine.launch {
                var user =db.userDao.get(username)
                //var user =db.userDao.check(binding.txtloginuser.text.toString(),binding.txtloginpass.text.toString())
                if (user!=null){
                    if (user.password != pass){
                        runOnUiThread{
                            Toast.makeText(this@LoginActivity, "Wrong Password", Toast.LENGTH_SHORT).show()

                        }
                    }else{
                        val intent = Intent(this@LoginActivity,MainActivity::class.java)
                        intent.putExtra("user",user)
                        startActivity(intent)
                    }
                }
                else{
                    runOnUiThread{
                        Toast.makeText(this@LoginActivity, "Username not Found", Toast.LENGTH_LONG)
                            .show()
                    }
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