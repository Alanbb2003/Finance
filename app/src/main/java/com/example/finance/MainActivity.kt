package com.example.finance

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.finance.DB.UserEntity
import com.example.finance.databinding.ActivityMainBinding
import com.example.finance.ui.add.AddActivity
import com.example.finance.ui.gallery.GalleryViewModel
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var user:UserEntity
    lateinit var navview:NavigationView
    val model:GalleryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = this.findNavController(R.id.nav_host_fragment_content_main)
        setSupportActionBar(binding.appBarMain.toolbar)
        user = UserEntity(
            "none","none","none","none"
        )

        val userlogged = intent.getParcelableExtra<UserEntity>("user")
        navview = findViewById(R.id.nav_view)
        val headerView : View = navview.getHeaderView(0)
        val navUsername : TextView = headerView.findViewById(R.id.dispname)
        val navUserEmail : TextView = headerView.findViewById(R.id.username)

        if (userlogged!=null){
            user = userlogged
            navUsername.setText(user.name)
            navUserEmail.setText(user.email)
            processLiveData(user.name)
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        var bundle = Bundle()
        bundle.putParcelable("user", user)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_history, R.id.nav_slideshow,
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)



        binding.appBarMain.fab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra("user",user)
            startActivity(intent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
//    private fun updateNextWordOnScreen() {
//        binding.textViewUnscrambledWord.text = model.user
//    }

    private fun processLiveData(liveData : String){
        val livedata = liveData
        model.update(livedata)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.Log_out_menu-> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}