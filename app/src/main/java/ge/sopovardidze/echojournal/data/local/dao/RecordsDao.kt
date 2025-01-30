package ge.sopovardidze.echojournal.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ge.sopovardidze.echojournal.data.local.entity.RecordEntity

@Dao
interface RecordsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: RecordEntity)

    @Query("SELECT * FROM RECORDS_TABLE")
    suspend fun getAllRecords(): List<RecordEntity>

    @Query("SELECT * FROM RECORDS_TABLE WHERE " +
            "(:mood IS NULL OR mood = :mood) AND " +
            "(:topic IS NULL OR topics LIKE '%' || :topic || '%')")
    fun getRecordsByMoodAndTopic(mood: String?, topic: String?): List<RecordEntity>
}