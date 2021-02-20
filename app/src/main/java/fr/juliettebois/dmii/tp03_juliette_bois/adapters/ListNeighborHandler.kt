package fr.juliettebois.dmii.tp03_juliette_bois.adapters

import fr.juliettebois.dmii.tp03_juliette_bois.models.Neighbor

interface ListNeighborHandler {
    fun onDeleteNeibor(neighbor: Neighbor)
}