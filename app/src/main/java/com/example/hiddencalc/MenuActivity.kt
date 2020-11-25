package com.example.hiddencalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_menu.*
import javax.xml.datatype.DatatypeFactory.newInstance

class MenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        var toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        var navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        var toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragment_container, NotesFragment())
            ft.commit()
            navigationView.setCheckedItem(R.id.nav_notes)
        }
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_notes){
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragment_container, NotesFragment())
            ft.commit()
        }
        if (item.itemId == R.id.nav_photos){
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragment_container, PhotosFragment())
            ft.commit()
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}