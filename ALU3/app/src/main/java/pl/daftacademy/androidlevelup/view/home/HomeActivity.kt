package pl.daftacademy.androidlevelup.view.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_home.*
import pl.daftacademy.androidlevelup.R
import pl.daftacademy.androidlevelup.view.movies.MoviesFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        nav.setNavigationItemSelectedListener { changePage(item = it) }
        if (savedInstanceState == null) showPage(null, addToBackStack = false)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
            title = "All Movies"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun changePage(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_all -> showPage(null)
            R.id.action_action -> showPage("Action")
            R.id.action_comedy -> showPage("Comedy")
            R.id.action_crime -> showPage("Crime")
            R.id.action_horror -> showPage("Horror")
            R.id.action_romance -> showPage("Romance")
            else -> return false
        }
        item.isChecked = true
        toolbar.title = item.title
        drawer.closeDrawers()
        return true
    }

    private fun showPage(genre: String?, addToBackStack: Boolean = false) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, MoviesFragment.create(genre))
            .apply { if(addToBackStack) addToBackStack(null) }
            .commit()
    }

}