package fr.juliettebois.dmii.tp03_juliette_bois.data

import fr.juliettebois.dmii.tp03_juliette_bois.data.datasource.InMemoryNeighborDataSource
import fr.juliettebois.dmii.tp03_juliette_bois.data.datasource.NeighborDatasource
import fr.juliettebois.dmii.tp03_juliette_bois.models.Neighbor

class NeighborRepository {
    private val dataSource: NeighborDatasource

    init {
        dataSource = InMemoryNeighborDataSource()
    }

    // Méthode qui retourne la liste des voisins
    fun getNeighbours(): List<Neighbor> = dataSource.neighbours

    fun deleteNeighbour(neighbor: Neighbor) = dataSource.deleteNeighbour(neighbor)

    fun createNeighbor(neighbor: Neighbor) = dataSource.createNeighbour(neighbor)

    companion object {
        private var instance: NeighborRepository? = null

        // On crée un méthode qui retourne l'instance courante du repository si elle existe ou en crée une nouvelle sinon
        fun getInstance(): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository()
            }
            return instance!!
        }
    }
}