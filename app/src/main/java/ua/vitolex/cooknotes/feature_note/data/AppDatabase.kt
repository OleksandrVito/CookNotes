package ua.vitolex.cooknotes.feature_note.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.vitolex.cooknotes.feature_note.domain.model.Ingredient
import ua.vitolex.cooknotes.feature_note.domain.model.Note
import ua.vitolex.cooknotes.feature_note.domain.model.Step
import ua.vitolex.cooknotes.feature_note.domain.repository.NoteRepository

@Database(entities = [Note::class, Ingredient::class, Step::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteRepository
}

