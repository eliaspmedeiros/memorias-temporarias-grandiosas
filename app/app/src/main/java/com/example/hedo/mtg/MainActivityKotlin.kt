package com.example.hedo.mtg

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import com.example.hedo.mtg.fragments.ColorFragment
import com.example.hedo.mtg.models.Color
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.nav_view as navigationView

class MainActivityKotlin : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {
        private const val BLACK_TAG = "Black"
        private const val BLUE_TAG = "Blue"
        private const val GREEN_TAG = "Green"
        private const val RED_TAG = "Red"
        private const val WHITE_TAG = "White"
        private const val FAVORITES_TAG = "Favorites"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        configureToolbar()
        configureNavigationDrawer()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            drawer_layout.openDrawer(GravityCompat.START)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isChecked = true

        val tag = when(item.itemId) {
            R.id.nav_black -> BLACK_TAG
            R.id.nav_blue -> BLUE_TAG
            R.id.nav_green-> GREEN_TAG
            R.id.nav_red -> RED_TAG
            R.id.nav_white-> WHITE_TAG
            R.id.nav_fav -> FAVORITES_TAG

            else -> BLACK_TAG
        }

        drawer_layout.closeDrawers()
        show(tag)

        return true
    }

    private fun show(fragmentTag: String) {
        val manager = supportFragmentManager
        var fragment = manager.findFragmentByTag(fragmentTag)

        if (fragment == null) {
            val fragmentColor = when(fragmentTag) {
                BLACK_TAG -> Color.BLACK
                BLUE_TAG -> Color.BLUE
                GREEN_TAG -> Color.GREEN
                RED_TAG -> Color.RED
                WHITE_TAG -> Color.WHITE
                FAVORITES_TAG -> Color.FAVORITES

                else -> Color.BLACK
            }

            fragment = ColorFragment.newInstance(fragmentColor)

            val transaction = manager.beginTransaction()
            transaction.replace(R.id.content_frame, fragment, fragmentTag)
            title = fragmentTag
            transaction.commit()
        }

        val transaction = manager.beginTransaction()
        transaction.show(fragment)
        transaction.commit()
    }

    private fun configureNavigationDrawer() : Unit {
        navigationView.itemIconTintList = null
        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun configureToolbar() {
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

}
