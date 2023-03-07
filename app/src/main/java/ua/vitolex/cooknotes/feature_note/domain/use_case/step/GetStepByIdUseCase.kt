package ua.vitolex.cooknotes.feature_note.domain.use_case.note

import ua.vitolex.cooknotes.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class GetStepByIdUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(noteId: String, id: Int) =
        noteRepository.getStepById(noteId = noteId, id = id)
}