package fr.juliettebois.dmii.tp03_juliette_bois.dal.memory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.juliettebois.dmii.tp03_juliette_bois.dal.InMemory_Neighbors
import fr.juliettebois.dmii.tp03_juliette_bois.dal.NeighborDatasource
import fr.juliettebois.dmii.tp03_juliette_bois.models.Neighbor

class InMemoryNeighborDataSource : NeighborDatasource {
    private val _neighbors = MutableLiveData<List<Neighbor>>()

    init {
        _neighbors.value = InMemory_Neighbors
    }

    override val neighbours: LiveData<List<Neighbor>>
        get() = this._neighbors

    override fun deleteNeighbour(neighbor: Neighbor) {
        InMemory_Neighbors.remove(neighbor)
    }

    override fun createNeighbour(neighbor: Neighbor) {
        InMemory_Neighbors.add(neighbor)
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun updateNeighbour(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }
}