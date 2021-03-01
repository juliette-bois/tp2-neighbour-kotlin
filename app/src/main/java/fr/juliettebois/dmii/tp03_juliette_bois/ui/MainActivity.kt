package fr.juliettebois.dmii.tp03_juliette_bois.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import fr.juliettebois.dmii.tp03_juliette_bois.NavigationListener
import fr.juliettebois.dmii.tp03_juliette_bois.R
import fr.juliettebois.dmii.tp03_juliette_bois.databinding.ActivityMainBinding
import fr.juliettebois.dmii.tp03_juliette_bois.di.DI
import fr.juliettebois.dmii.tp03_juliette_bois.ui.fragments.ListNeighborsFragment

class MainActivity : AppCompatActivity(), NavigationListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DI.inject(application)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        showFragment(ListNeighborsFragment())
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun updateTitle(title: Int) {
        binding.toolbar.setTitle(title)
    }
}
