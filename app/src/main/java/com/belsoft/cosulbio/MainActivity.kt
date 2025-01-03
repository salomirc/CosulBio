package com.belsoft.cosulbio

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.belsoft.cosulbio.databinding.ActivityMainBinding
import com.belsoft.cosulbio.utils.InjectorUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration

    companion object {
        lateinit var isKeyboardOnScreen: () -> Boolean
        lateinit var hideSoftKeyboard: (View) -> Unit
        lateinit var showSoftKeyboard: (View) -> Unit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setArchitectureComponents()
        setCompanionObjectMembers()
        setGlobalObservers()
        setActivityComponents()
        setViewModelData()
    }

    private fun setViewModelData() {
        viewModel.getUsersFromDb()
    }

    private fun setGlobalObservers() {
        //SingleEvent received from ViewModel
        viewModel.toastMessage.observe(this, Observer { message ->
            message?.let {
                displayToastMessage(this, resources.getString(it))
            }
        })

        //SingleEvent received from ViewModel
        viewModel.toastMessageString.observe(this, Observer { message ->
            message?.let {
                displayToastMessage(this, it)
            }
        })

        //Demo Void SingleEvent received from ViewModel
        viewModel.singleLiveEvent.observe(this, Observer {
            displayToastMessage(this, "singleLiveEvent")
        })

        viewModel.navigateLiveEvent.observe(this, Observer { id ->
            id?.let {
                val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
                navController.navigate(id)
            }
        })
    }

    private fun setCompanionObjectMembers() {
        val rootView = binding.root
        isKeyboardOnScreen = {
            val r = Rect()
            rootView.getWindowVisibleDisplayFrame(r)
            val heightDiff = rootView.rootView.height - (r.bottom - r.top)
            heightDiff > rootView.rootView.height / 4
        }

        hideSoftKeyboard = { view: View ->
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }

        showSoftKeyboard = { view: View ->
            val hasFocus = if (view.hasFocus()) true else view.requestFocus()
            if (hasFocus) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
            }
        }
    }

    private fun setArchitectureComponents() {

        // Get the MainViewModelFactory with all of it's dependencies constructed
        val factory = InjectorUtils.getInstance(applicationContext).provideMainViewModelFactory()

        // Obtain the ViewModel component.
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        // Inflate view and obtain an instance of the binding class.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner = this

        // Assign the component to a property in the binding class.
        binding.viewmodel = viewModel
    }

    private fun setActivityComponents() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment,R.id.cosulMeuFragment , R.id.myOrdersFragment, R.id.loginFragment, R.id.logoutFragment), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //Hide Logout menu item
        val menu = navView.menu
        val logoutItem = menu.findItem(R.id.logoutFragment)
        val loginItem = menu.findItem(R.id.loginFragment)
        logoutItem.isVisible = false

        val header = navView.getHeaderView(0)
        val userNameTextView: TextView = header.findViewById<TextView>(R.id.userNameTextView)
        val mailAdressTextView = header.findViewById<TextView>(R.id.mailAdressTextView)

        viewModel.userInfo.observe(this, Observer { user ->
            if (user == null){
                userNameTextView.text = getString(R.string.nav_header_title)
                mailAdressTextView.text = getString(R.string.empty)
            }
            else{
                userNameTextView.text = getString(R.string.full_name_with_args, user.firstName, user.lastName)
                mailAdressTextView.text = user.email
            }
        })

        viewModel.isUserLogged.observe(this, Observer { value ->
            value?.let {
                logoutItem.isVisible = value
                loginItem.isVisible = !value
            }
        })
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

    private fun displayToastMessage(context: Context, message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER,0, 0)
        toast.show()
    }
}
