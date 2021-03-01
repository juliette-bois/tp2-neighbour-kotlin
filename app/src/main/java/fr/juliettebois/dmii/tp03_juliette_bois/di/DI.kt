package fr.juliettebois.dmii.tp03_juliette_bois.di

import android.app.Application
import fr.juliettebois.dmii.tp03_juliette_bois.repositories.NeighborRepository

object DI {
    lateinit var repository: NeighborRepository
    fun inject(application: Application) {
        repository = NeighborRepository.getInstance(application)
    }
}
