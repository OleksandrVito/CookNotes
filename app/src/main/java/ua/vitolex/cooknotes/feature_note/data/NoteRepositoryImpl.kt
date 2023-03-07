package ua.vitolex.cooknotes.feature_note.data

import ua.vitolex.cooknotes.feature_note.domain.model.Ingredient
import ua.vitolex.cooknotes.feature_note.domain.model.Note
import ua.vitolex.cooknotes.feature_note.domain.model.Step
import ua.vitolex.cooknotes.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteRepositoryImpl: NoteRepository) {

    suspend fun getAllNotes(): List<Note> = noteRepositoryImpl.getAllNotes()
    suspend fun insertNote(note: Note) = noteRepositoryImpl.insertNote(note = note)
    suspend fun deleteNote(note: Note) = noteRepositoryImpl.deleteNote(note = note)
    suspend fun getNoteById(id: String) = noteRepositoryImpl.getNoteById(nodeId = id)
    suspend fun getIngredients(noteId: String) = noteRepositoryImpl.getIngredients(noteId = noteId)
    suspend fun getIngredientById(noteId: String, id: Int) =
        noteRepositoryImpl.getIngredientById(noteId = noteId, id = id)

    suspend fun insertIngredient(ingredient: Ingredient) =
        noteRepositoryImpl.insertIngredient(ingredient = ingredient)

    suspend fun deleteIngredient(ingredient: Ingredient) =
        noteRepositoryImpl.deleteIngredient(ingredient = ingredient)

    suspend fun getSteps(noteId: String) = noteRepositoryImpl.getSteps(noteId = noteId)
    suspend fun getStepById(noteId: String, id: Int) =
        noteRepositoryImpl.getStepById(noteId = noteId, id = id)

    suspend fun insertSteo(step: Step) = noteRepositoryImpl.insertStep(step = step)
    suspend fun deleteStep(step: Step) = noteRepositoryImpl.deleteStep(step = step)

}