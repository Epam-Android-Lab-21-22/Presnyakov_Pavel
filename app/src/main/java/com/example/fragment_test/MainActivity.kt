package com.example.fragment_test

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.fragment_test.R
import com.example.fragment_test.databinding.ActivityMainBinding
import com.example.fragment_test.fragments.BlueFragment
import com.example.fragment_test.fragments.GreenFragment
import com.example.fragment_test.fragments.RedFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.properties.Delegates

class MainActivity : FragmentActivity() {

    companion object{
        const val FRAGMENT_RED = "FRAGMENT_RED"
        const val FRAGMENT_GREEN = "FRAGMENT_GREEN"
        const val FRAGMENT_BLUE = "FRAGMENT_BLUE"
        const val FRAGMENT_ACTIVE = "FRAGMENT_ACTIVE"
    }

    private var last by Delegates.notNull<String>()
    private val blue : BlueFragment = BlueFragment()
    private val red : RedFragment = RedFragment()
    private val green: GreenFragment = GreenFragment()
    private val fragmentManager :FragmentManager =  supportFragmentManager

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            last = FRAGMENT_RED
            setFragmentOnActivity(red , FRAGMENT_RED)
            initStackOnBackStack()
        }

        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            supportFragmentManager.saveBackStack(last)
            selectStack(item.itemId)
        }
        binding.navigator.setOnItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun selectStack(itemId: Int): Boolean {
        when (itemId) {
            R.id.red -> {
                setFragmentOnActivity(red , FRAGMENT_RED)
                return true
            }
            R.id.blue -> {
                setFragmentOnActivity(blue, FRAGMENT_BLUE)
                return true
            }
            R.id.green -> {
                setFragmentOnActivity(green, FRAGMENT_GREEN)
                return true
            }
        }
        return false
    }

    private fun initStackOnBackStack() {
        supportFragmentManager.saveBackStack(FRAGMENT_RED)
        supportFragmentManager.saveBackStack(FRAGMENT_GREEN)
        supportFragmentManager.saveBackStack(FRAGMENT_BLUE)
    }

    private fun setFragmentOnActivity(fragmentView: Fragment , typeFragment : String ) {

        fragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.fragmentContainer, fragmentView)
            .commit()
        updateBackStack(typeFragment)
    }

    private fun updateBackStack(typeFragment: String) {
        supportFragmentManager.restoreBackStack(typeFragment)
        supportFragmentManager.saveBackStack(last)
        last = typeFragment
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(FRAGMENT_ACTIVE, last);
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        last = savedInstanceState.getSerializable(FRAGMENT_ACTIVE) as String
    }



    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            super.onBackPressed()
        } else {
            finish()
        }
    }


}


