package ge.sopovardidze.echojournal.data.local.repository

import ge.sopovardidze.echojournal.data.local.dao.RecordsDao
import ge.sopovardidze.echojournal.data.local.entity.RecordEntity
import ge.sopovardidze.echojournal.domain.repository.RecordsRepository
import ge.sopovardidze.echojournal.presentation.records.model.RecordModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map

class RecordsRepositoryImpl(
    private val recordsDao: RecordsDao,
) : RecordsRepository {

    override suspend fun insertRecord(record: RecordEntity) {
        recordsDao.insertRecord(record)
    }

    override fun getAllRecords(): Flow<List<RecordModel>> = flow {
        val records = recordsDao.getAllRecords()
        emit(records.map { it.toRecordModel() })
    }

    override fun getRecordsByMoodAndTopic(mood: String?, topic: String?): Flow<List<RecordModel>> = flow {
        val filteredRecords = recordsDao.getRecordsByMoodAndTopic(mood, topic)
        emit(filteredRecords.map { it.toRecordModel() })
    }
}