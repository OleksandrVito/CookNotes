package ua.vitolex.cooknotes.feature_note.presentation.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.vitolex.cooknotes.feature_note.domain.model.Ingredient
import ua.vitolex.cooknotes.feature_note.domain.model.Note
import ua.vitolex.cooknotes.feature_note.domain.model.Step
import ua.vitolex.cooknotes.feature_note.domain.use_case.note.*
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val getAllIngredientsUseCase: GetAllIngredientsUseCase,
    private val getAllStepsUseCase: GetAllStepsUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val deleteIngredientUseCase: DeleteIngredientUseCase,
    private val deleteStepUseCase: DeleteStepUseCase,
) : ViewModel() {

    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note>
        get() = _note

    fun getNoteById(id: String) {
        viewModelScope.launch {
            getNoteByIdUseCase.invoke(id = id).let {
                _note.postValue(it)
            }
        }
    }

    fun deleteNote(onSuccess: () -> Unit) {
        viewModelScope.launch {
            note.value?.let {
                deleteNoteUseCase.invoke(note = it)
                onSuccess()
            }
        }
    }

    private val _ingredients = MutableLiveData<List<Ingredient>>()
    val ingredients: LiveData<List<Ingredient>>
        get() = _ingredients

      fun getAllIngredients(noteId: String) {
        viewModelScope.launch {
            getAllIngredientsUseCase.invoke(noteId = noteId).let {
                _ingredients.postValue(it)
            }
        }
    }

    fun deleteIngredients() {
        viewModelScope.launch {
            ingredients.value?.let {
                for (item in it ){
                    deleteIngredientUseCase.invoke(ingredient = item)
                }
            }
        }
    }

    private val _steps = MutableLiveData<List<Step>>()
    val steps: LiveData<List<Step>>
        get() = _steps

    fun getAllSteps(noteId: String) {
        viewModelScope.launch {
            getAllStepsUseCase.invoke(noteId = noteId).let {
                _steps.postValue(it)
            }
        }
    }

    fun deleteSteps() {
        viewModelScope.launch {
            steps.value?.let {
                for (item in it ){
                    deleteStepUseCase.invoke(step = item)
                }
            }
        }
    }
}