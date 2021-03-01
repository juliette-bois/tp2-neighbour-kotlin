package fr.juliettebois.dmii.tp03_juliette_bois.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.juliettebois.dmii.tp03_juliette_bois.di.DI
import fr.juliettebois.dmii.tp03_juliette_bois.models.Neighbor
import fr.juliettebois.dmii.tp03_juliette_bois.repositories.NeighborRepository

class NeighborViewModel : ViewModel() {
    private val repository: NeighborRepository = DI.repository

    // On fait passe plat sur le résultat retourné par le repository
    val neighbors: LiveData<List<Neighbor>>
        get() = repository.getNeighbours()
}
