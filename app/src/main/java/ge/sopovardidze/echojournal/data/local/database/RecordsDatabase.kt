package ge.sopovardidze.echojournal.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ge.sopovardidze.echojournal.core.Converters
import ge.sopovardidze.echojournal.data.local.dao.RecordsDao
import ge.sopovardidze.echojournal.data.local.entity.RecordEntity

@Database(
    entities = [RecordEntity::class],
    version = 1,
    exportSchema = false
)

abstract class RecordsDatabase: RoomDatabase() {

    abstract fun recordDao(): RecordsDao
}