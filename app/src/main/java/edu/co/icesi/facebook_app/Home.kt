package edu.co.icesi.facebook_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import edu.co.icesi.facebook_app.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding

    private lateinit var homeFragment : HomeFragment
    private lateinit var publishFragment : PublishFragment
    private lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeFragment = HomeFragment.newInstance()
        profileFragment= ProfileFragment.newInstance()
        publishFragment= PublishFragment.newInstance()

        showFragment(homeFragment)

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.home) {
                showFragment(homeFragment)
            } else if (menuItem.itemId == R.id.publish) {
                showFragment(publishFragment)
            } else if (menuItem.itemId == R.id.profile) {
                showFragment(profileFragment)
            }
            true
        }

    }

    fun showFragment(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.posts, fragment)
        transaction.commit()
    }
}