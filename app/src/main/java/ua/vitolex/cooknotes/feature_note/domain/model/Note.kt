package ua.vitolex.cooknotes.feature_note.domain.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey val id: String = "",
    val title: String,
    val category: String,
    val photo: String? = null
)

