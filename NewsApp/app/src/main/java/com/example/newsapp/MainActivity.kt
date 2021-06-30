package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var bottomNavigationView: BottomNavigationView



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        replaceFragment(ArticleScreen())

        bottomNavigationView = findViewById(R.id.bottomNavBar)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean{
        // toolbar에 menu_toolbar 적용하기
        val menuInflater: MenuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_read -> {
                replaceFragment(ArticleScreen())
                return true
            }
            R.id.action_scrap -> {
                replaceFragment(ScrapScreen())
                return true
            }
            R.id.action_settings -> {
                replaceFragment(SettingsScreen())
                return true
            }
        }
        return false
    }

}