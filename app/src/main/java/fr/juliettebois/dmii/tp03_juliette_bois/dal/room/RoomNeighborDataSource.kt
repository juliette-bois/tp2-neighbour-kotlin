package fr.juliettebois.dmii.tp03_juliette_bois.dal.room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import fr.juliettebois.dmii.tp03_juliette_bois.dal.NeighborDatasource
import fr.juliettebois.dmii.tp03_juliette_bois.dal.room.daos.NeighborDao
import fr.juliettebois.dmii.tp03_juliette_bois.dal.utilis.toEntity
import fr.juliettebois.dmii.tp03_juliette_bois.dal.utilis.toNeighbor
import fr.juliettebois.dmii.tp03_juliette_bois.models.Neighbor
import java.util.concurrent.Executors

class RoomNeighborDataSource(application: Application) : NeighborDatasource {
    private val database: NeighborDataBase = NeighborDataBase.getDataBase(application)
    private val dao: NeighborDao = database.neighborDao()

    private val _neighors = MediatorLiveData<List<Neighbor>>()

    init {
        _neighors.addSource(dao.getNeighbors()) { entities ->
            _neighors.value = entities.map { entity ->
                entity.toNeighbor()
            }
        }
    }

    override val neighbours: LiveData<List<Neighbor>>
        get() = _neighors

    override fun deleteNeighbour(neighbor: Neighbor) {
        Executors.newSingleThreadExecutor().execute {
            dao.delete(neighbor.toEntity())
        }
    }

    override fun createNeighbour(neighbor: Neighbor) {
        Executors.newSingleThreadExecutor().execute {
            dao.add(neighbor.toEntity())
        }
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun updateNeighbour(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }
}