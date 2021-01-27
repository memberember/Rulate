package tl.rulate.ru

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.app_bar_main.*
import tl.rulate.ru.data.SharedPrefBookData
import tl.rulate.ru.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var mainViewModel = MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setObservers()

        // считываем данные из sharedPref(для пользователя)
        mainViewModel.sharedPref = SharedPrefManager.getInstance(this)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_profile, R.id.nav_novels, R.id.nav_search
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        // левое окно теперь не закрывает основной экран а сдвигает его.
        val content = findViewById<ConstraintLayout>(R.id.content)
        val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                val slideX: Float = drawerView.getWidth() * slideOffset
                content.translationX = slideX

                // а также меняем размер
//                content.scaleX = 1 - slideOffset
//                content.scaleY = 1 - slideOffset
            }
        }
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        fab.setOnClickListener {
//            Log.d(Constants.DEBUG, "Saved")
//            mainViewModel.sharedPref.saveBook(
//                SharedPrefBookData(1, 1)
//            )
        }

        fab1.setOnClickListener {
//            Log.d(
//                Constants.DEBUG,
//                "book chapter = ${mainViewModel.sharedPref.book.lastChapterId} bookid = ${mainViewModel.sharedPref.book.lastBookId}"
//            )
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setObservers() {
        val navController = findNavController(R.id.nav_host_fragment)

        mainViewModel.lastBookId.observe(this, Observer {

            //todo делать сохранение книги в БД
            mainViewModel.sharedPref.saveBook(
                SharedPrefBookData(
                    it,
                    mainViewModel.lastChapterId.value ?: -1
                )
            )

            if (it != -1)
                navController.navigate(R.id.nav_book_about)
        })

        mainViewModel.lastChapterId.observe(this, Observer {
            //todo делать сохранение книги в БД
            mainViewModel.sharedPref.saveBook(
                SharedPrefBookData(
                    mainViewModel.lastBookId.value ?: -1, it
                )
            )
            if (it != -1)
                navController.navigate(R.id.nav_read)
        })
    }
}