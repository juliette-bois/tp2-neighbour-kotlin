package fr.juliettebois.dmii.tp03_juliette_bois.dal.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.juliettebois.dmii.tp03_juliette_bois.dal.room.entities.NeighborEntity

@Dao
interface NeighborDao {
    @Query("SELECT * from neighbors")
    fun getNeighbors(): LiveData<List<NeighborEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(neighbor: NeighborEntity)
}
