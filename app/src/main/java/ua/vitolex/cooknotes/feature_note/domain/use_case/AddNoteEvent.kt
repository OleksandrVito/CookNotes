package ua.vitolex.cooknotes.feature_note.domain.use_case

import ua.vitolex.cooknotes.feature_note.domain.model.Ingredient
import ua.vitolex.cooknotes.feature_note.domain.model.Step

sealed class AddNoteEvent {
    data class EnteredTitle(val value: String) : AddNoteEvent()
    data class EnteredCategory(val value: String) : AddNoteEvent()
    data class EnteredPhoto(val value: String) : AddNoteEvent()
    data class EnteredIngredient(val value: String) : AddNoteEvent()
    data class EnteredStep(val value: String) : AddNoteEvent()
    data class AddIngredientToList(val value: Ingredient) : AddNoteEvent()
    data class AddStepToList(val value: Step) : AddNoteEvent()
    data class RemoveIngredientFromList(val value: Ingredient) : AddNoteEvent()
    data class RemoveStepFromList(val value: Step) : AddNoteEvent()
}
