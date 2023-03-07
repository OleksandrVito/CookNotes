package ua.vitolex.cooknotes.feature_note.presentation.screens.add_edit

import androidx.compose.runtime.*
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.vitolex.cooknotes.feature_note.domain.model.Ingredient
import ua.vitolex.cooknotes.feature_note.domain.model.Note
import ua.vitolex.cooknotes.feature_note.domain.model.Step
import ua.vitolex.cooknotes.feature_note.domain.use_case.AddNoteEvent
import ua.vitolex.cooknotes.feature_note.domain.use_case.note.*
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val addIngredientUseCase: AddIngredientUseCase,
    private val addStepUseCase: AddStepUseCase,
    private val getIngredients: GetAllIngredientsUseCase,
    private val getSteps: GetAllStepsUseCase,
    private val deleteIngredientUseCase: DeleteIngredientUseCase,
    private val deleteStepUseCase: DeleteStepUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note>
        get() = _note

    private val _title = mutableStateOf("")
    val title: State<String> = _title

    private val _category = mutableStateOf("")
    val category: State<String> = _category

    private val _photo = mutableStateOf("")
    val photo: State<String> = _photo

    private val _ingredientTitle = mutableStateOf("")
    val ingredientTitle: State<String> = _ingredientTitle

    private val _stepTitle = mutableStateOf("")
    val stepTitle: State<String> = _stepTitle

    private val _ingredients = mutableStateOf(listOf<Ingredient>())
    val ingredients: State<List<Ingredient>> = _ingredients
    var copyIngredients: List<Ingredient> = listOf()

    private val _steps = mutableStateOf(listOf<Step>())
    val steps: State<List<Step>> = _steps
    var copySteps: List<Step> = listOf()


    private var currentNoteId: String? = null

    private val _noteToDelete = mutableStateOf(
        Note(
            title = "",
            category = ""
        )
    )
    val noteToDelete: State<Note> = _noteToDelete

    fun getNoteById(id: String) {
        viewModelScope.launch {
            getNoteByIdUseCase.invoke(id = id).let {
                _note.postValue(it)
            }
        }
    }

    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch {
            addNoteUseCase.invoke(note = note)
            onSuccess()
        }
    }

    fun addIngredient(ingredient: Ingredient) {
        viewModelScope.launch {
            addIngredientUseCase.invoke(ingredient = ingredient)
        }
    }

    fun addStep(step: Step) {
        viewModelScope.launch {
            addStepUseCase.invoke(step = step)
        }
    }

    fun deleteIngredients() {
        viewModelScope.launch {
            copyIngredients.let {
                for (item in it) {
                    deleteIngredientUseCase.invoke(ingredient = item)
                }
            }
        }
    }

    fun deleteSteps() {
        viewModelScope.launch {
            copySteps.let {
                for (item in it) {
                    deleteStepUseCase.invoke(step = item)
                }
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            deleteNoteUseCase.invoke(note = note)
        }
    }

    init {
        savedStateHandle.get<String>("id")?.let { id ->
            if (id != "") {
                viewModelScope.launch {
                    getNoteByIdUseCase(id)?.also { note ->
                        currentNoteId = note.id
                        _title.value = note.title
                        _category.value = note.category
                        _photo.value = note.photo ?: ""
                        getIngredients(id)?.also {
                            _ingredients.value = it
                            copyIngredients = it
                        }
                        getSteps(id)?.also {
                            _steps.value = it
                            copySteps = it
                        }
                        _noteToDelete.value = note
//                        deleteNoteUseCase.invoke(note = note)
                    }
                }
            }
        }
    }

    fun onEvent(event: AddNoteEvent) {
        when (event) {
            is AddNoteEvent.EnteredTitle -> {
                _title.value = event.value
            }
            is AddNoteEvent.EnteredCategory -> {
                _category.value = event.value
            }
            is AddNoteEvent.EnteredPhoto -> {
                _photo.value = event.value
            }
            is AddNoteEvent.EnteredIngredient -> {
                _ingredientTitle.value = event.value
            }
            is AddNoteEvent.EnteredStep -> {
                _stepTitle.value = event.value
            }
            is AddNoteEvent.AddIngredientToList -> {
                _ingredientTitle.value = ""
                val newIngredientsList = ArrayList(_ingredients.value)
                newIngredientsList.add(event.value)
                _ingredients.value = newIngredientsList
            }
            is AddNoteEvent.AddStepToList -> {
                _stepTitle.value = ""
                val newStepsList = ArrayList(_steps.value)
                newStepsList.add(event.value)
                _steps.value = newStepsList
            }
            is AddNoteEvent.RemoveIngredientFromList -> {
                val newIngredientsList = ArrayList(_ingredients.value)
                newIngredientsList.remove(event.value)
                _ingredients.value = newIngredientsList
            }
            is AddNoteEvent.RemoveStepFromList -> {
                val newStepsList = ArrayList(_steps.value)
                newStepsList.remove(event.value)
                _steps.value = newStepsList
            }
        }
    }
}