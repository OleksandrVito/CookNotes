package ua.vitolex.cooknotes.feature_note.domain.use_case.note

import ua.vitolex.cooknotes.feature_note.domain.model.Ingredient
import ua.vitolex.cooknotes.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class AddIngredientUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(ingredient: Ingredient) = noteRepository.insertIngredient(ingredient = ingredient)
}