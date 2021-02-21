package fr.juliettebois.dmii.tp03_juliette_bois

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

interface NavigationListener {
    fun showFragment(fragment: Fragment)
    //fun updateTitle(@StringRes title: Int)
}