package ua.vitolex.cooknotes.feature_note.domain.use_case.note

import ua.vitolex.cooknotes.feature_note.domain.model.Step
import ua.vitolex.cooknotes.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class AddStepUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(step: Step) = noteRepository.insertStep(step = step)
}