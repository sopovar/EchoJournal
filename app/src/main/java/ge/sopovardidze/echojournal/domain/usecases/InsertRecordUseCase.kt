package ge.sopovardidze.echojournal.domain.usecases

import ge.sopovardidze.echojournal.domain.repository.RecordsRepository
import ge.sopovardidze.echojournal.presentation.records.model.RecordModel

class InsertRecordUseCase(
    private val recordsRepository: RecordsRepository
) {

    suspend operator fun invoke(recordModel: RecordModel) {
        recordsRepository.insertRecord(recordModel.toRecordEntity())
    }
}