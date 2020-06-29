package com.coding.smkcoding_project_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import com.coding.smkcoding_project_2.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    val menuTeks = arrayOf("World", "Region", "Province", "Web View", "Tutorial")
    val menuIcon = arrayOf(R.drawable.ic_world, R.drawable.ic_province, R.drawable.ic_region,
    R.drawable.ic_web, R.drawable.ic_tutorial)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ViewPagerAdapter(this)
        view_pager.setAdapter(adapter)
        TabLayoutMediator(tab_layout, view_pager, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
//            tab.text = menuTeks[position]
            tab.icon = ResourcesCompat.getDrawable(resources, menuIcon[position], null)
        }).attach()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId) {
            R.id.help -> moveToReadMe()
            R.id.logOut -> logout()
        }
        return true
    }



    private fun moveToReadMe() {
        val intent = Intent(this, ReadMeActivity::class.java)
        startActivity(intent)
    }

    private fun logout() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Logout")
        alert.setMessage("Apakah anda yakin ingin logout?")
        alert.setPositiveButton("Ya"){dialog, i ->
            FirebaseAuth.getInstance().signOut()
            val i = Intent(this, LoginActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            Toast.makeText(applicationContext, "Berhasil Logout", Toast.LENGTH_SHORT).show()
            startActivity(i)
        }
        alert.setNegativeButton("Tidak"){dialog, i ->
            Toast.makeText(this, "Logout dibatalkan", Toast.LENGTH_SHORT).show()
        }
        alert.show()
    }
}
