package ua.vitolex.cooknotes.feature_note.domain.use_case.note

import ua.vitolex.cooknotes.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: String) = noteRepository.getNoteById(nodeId = id)
}
