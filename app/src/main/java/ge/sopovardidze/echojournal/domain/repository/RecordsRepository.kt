package ge.sopovardidze.echojournal.domain.repository

import ge.sopovardidze.echojournal.data.local.entity.RecordEntity
import ge.sopovardidze.echojournal.presentation.records.model.RecordModel
import kotlinx.coroutines.flow.Flow

interface RecordsRepository {

    suspend fun insertRecord(record: RecordEntity)
    fun getAllRecords(): Flow<List<RecordModel>>
    fun getRecordsByMoodAndTopic(mood: String?, topic: String?): Flow<List<RecordModel>>
}