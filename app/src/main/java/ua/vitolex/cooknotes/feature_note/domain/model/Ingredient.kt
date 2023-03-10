package ua.vitolex.cooknotes.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ingredient(
    @PrimaryKey val id: Int? = null,
    var noteId: String,
    var title: String

)