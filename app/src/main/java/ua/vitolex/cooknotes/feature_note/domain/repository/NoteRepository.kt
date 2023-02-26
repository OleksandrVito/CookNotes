package ua.vitolex.cooknotes.feature_note.domain.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ua.vitolex.cooknotes.feature_note.domain.model.Note

//interface NoteRepository {
//    fun getNotes(): List<Note>
//
//    suspend fun getNoteById(id: Int): Note?
//
//    suspend fun insertNote(note: Note)
//
//    suspend fun deleteNote(note: Note)
//
//    fun getIngredients(noteId: Int): List<Ingredient>
//
//    suspend fun getIngredientById(noteId: Int, id: Int): Ingredient?
//
//    suspend fun insertIngredient(ingredient: Ingredient)
//
//    suspend fun deleteIngredient(ingredient: Ingredient)

//    fun getSteps(noteId: Int): Flow<List<Step>>
//
//    suspend fun getStepById(noteId: Int, id: Int): Ingredient?
//
//    suspend fun insertStep(step: Step)
//
//    suspend fun deleteStep(step: Step)

//}

@Dao
interface NoteRepository {

    @Insert
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM note")
    suspend fun getAllNotes(): List<Note>

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note WHERE id=:nodeId")
    suspend fun getNoteById(nodeId: String): Note
}