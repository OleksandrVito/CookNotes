package ua.vitolex.cooknotes.feature_note.domain.use_case.note

import ua.vitolex.cooknotes.feature_note.domain.model.Note
import ua.vitolex.cooknotes.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.insertNote(note = note)
}