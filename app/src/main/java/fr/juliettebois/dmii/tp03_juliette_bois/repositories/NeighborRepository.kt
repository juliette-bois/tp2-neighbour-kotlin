package fr.juliettebois.dmii.tp03_juliette_bois.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import fr.juliettebois.dmii.tp03_juliette_bois.dal.memory.InMemoryNeighborDataSource
import fr.juliettebois.dmii.tp03_juliette_bois.dal.NeighborDatasource
import fr.juliettebois.dmii.tp03_juliette_bois.dal.room.RoomNeighborDataSource
import fr.juliettebois.dmii.tp03_juliette_bois.models.Neighbor

class NeighborRepository private constructor(application: Application) {
    private var dataSource: NeighborDatasource = InMemoryNeighborDataSource()

    init {
        dataSource = RoomNeighborDataSource(application)
    }

    // Méthode qui retourne la liste des voisins
    fun getNeighbours(): LiveData<List<Neighbor>> = dataSource.neighbours

    fun deleteNeighbour(neighbor: Neighbor) = dataSource.deleteNeighbour(neighbor)

    fun createNeighbor(neighbor: Neighbor) = dataSource.createNeighbour(neighbor)

    companion object {
        private var instance: NeighborRepository? = null

        // On crée un méthode qui retourne l'instance courante du repository si elle existe ou en crée une nouvelle sinon
        fun getInstance(application: Application): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository(application)
            }
            return instance!!
        }
    }
}