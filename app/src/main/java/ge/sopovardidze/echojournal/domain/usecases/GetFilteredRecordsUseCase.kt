package ge.sopovardidze.echojournal.domain.usecases

import ge.sopovardidze.echojournal.domain.repository.RecordsRepository
import ge.sopovardidze.echojournal.presentation.records.model.RecordModel
import kotlinx.coroutines.flow.Flow

class GetFilteredRecordsUseCase(
    private val recordsRepository: RecordsRepository
) {
    suspend operator fun invoke(mood: String?, topic: String?): Flow<List<RecordModel>> {
        return recordsRepository.getRecordsByMoodAndTopic(mood, topic)
    }
}