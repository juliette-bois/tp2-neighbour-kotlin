package fr.juliettebois.dmii.tp03_juliette_bois.dal.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import fr.juliettebois.dmii.tp03_juliette_bois.dal.InMemory_Neighbors
import fr.juliettebois.dmii.tp03_juliette_bois.dal.room.daos.NeighborDao
import fr.juliettebois.dmii.tp03_juliette_bois.dal.room.entities.NeighborEntity
import fr.juliettebois.dmii.tp03_juliette_bois.dal.utilis.toEntity
import java.util.concurrent.Executors

@Database(
    entities = [NeighborEntity::class],
    version = 1
)
abstract class NeighborDataBase : RoomDatabase() {
    abstract fun neighborDao(): NeighborDao

    companion object {
        private var instance: NeighborDataBase? = null

        fun getDataBase(application: Application): NeighborDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    application.applicationContext,
                    NeighborDataBase::class.java,
                    "neighbor_database.db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        insertFakeData()
                    }
                })
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }

        private fun insertFakeData() {
            Executors.newSingleThreadExecutor().execute {
                InMemory_Neighbors.forEach {
                    instance?.neighborDao()?.add(it.toEntity())
                }
            }
        }
    }
}
