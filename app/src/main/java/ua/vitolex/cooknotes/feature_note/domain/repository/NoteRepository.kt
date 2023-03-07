package ua.vitolex.cooknotes.feature_note.domain.repository

import androidx.room.*
import ua.vitolex.cooknotes.feature_note.domain.model.Ingredient
import ua.vitolex.cooknotes.feature_note.domain.model.Note
import ua.vitolex.cooknotes.feature_note.domain.model.Step

@Dao
interface NoteRepository {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM note")
    suspend fun getAllNotes(): List<Note>

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note WHERE id=:nodeId")
    suspend fun getNoteById(nodeId: String): Note

    @Query("SELECT * FROM ingredient WHERE noteId=:noteId")
    suspend fun getIngredients(noteId: String): List<Ingredient>

    @Query("SELECT * FROM ingredient WHERE id=:id AND noteId=:noteId")
    suspend fun getIngredientById(noteId: String, id: Int): Ingredient?

    @Insert
    suspend fun insertIngredient(ingredient: Ingredient)

    @Delete
    suspend fun deleteIngredient(ingredient: Ingredient)

    @Query("SELECT * FROM step WHERE noteId=:noteId")
    suspend fun getSteps(noteId: String): List<Step>

    @Query("SELECT * FROM step WHERE id=:id AND noteId=:noteId")
    suspend fun getStepById(noteId: String, id: Int): Step?

    @Insert
    suspend fun insertStep(step: Step)

    @Delete
    suspend fun deleteStep(step: Step)

}