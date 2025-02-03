package ge.sopovardidze.echojournal.domain.usecases

import ge.sopovardidze.echojournal.domain.repository.RecordsRepository
import ge.sopovardidze.echojournal.presentation.records.model.RecordModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InsertRecordUseCase(
    private val recordsRepository: RecordsRepository
) {

    suspend operator fun invoke(recordModel: RecordModel): Flow<Boolean> = flow  {
        try {
            recordsRepository.insertRecord(recordModel.toRecordEntity())
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }
}