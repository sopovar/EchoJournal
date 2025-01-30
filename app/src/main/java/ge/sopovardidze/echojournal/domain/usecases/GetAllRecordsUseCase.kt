package ge.sopovardidze.echojournal.domain.usecases

import ge.sopovardidze.echojournal.domain.repository.RecordsRepository
import ge.sopovardidze.echojournal.presentation.records.model.RecordModel
import kotlinx.coroutines.flow.Flow

class GetAllRecordsUseCase(
    private val recordsRepository: RecordsRepository
) {

    suspend operator fun invoke(): Flow<List<RecordModel>> {
        return recordsRepository.getAllRecords()
    }
}