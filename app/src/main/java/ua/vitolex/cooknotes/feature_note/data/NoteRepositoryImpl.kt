package ua.vitolex.cooknotes.feature_note.data

import ua.vitolex.cooknotes.feature_note.domain.model.Note
import ua.vitolex.cooknotes.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteRepositoryImpl: NoteRepository) {

    suspend fun getAllNotes(): List<Note> = noteRepositoryImpl.getAllNotes()
    suspend fun insertNote(note: Note) = noteRepositoryImpl.insertNote(note = note)
    suspend fun deleteNote(note: Note) = noteRepositoryImpl.deleteNote(note = note)
    suspend fun getNoteById(id: String) = noteRepositoryImpl.getNoteById(nodeId = id)

}