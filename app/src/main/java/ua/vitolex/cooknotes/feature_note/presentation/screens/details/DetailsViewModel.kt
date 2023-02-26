package ua.vitolex.cooknotes.feature_note.presentation.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.vitolex.cooknotes.feature_note.domain.model.Note
import ua.vitolex.cooknotes.feature_note.domain.use_case.note.DeleteNoteUseCase
import ua.vitolex.cooknotes.feature_note.domain.use_case.note.GetNoteByIdUseCase
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getNoteByTitleUseCase: GetNoteByIdUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
): ViewModel() {

    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note>
        get() = _note

    fun getNoteById(id: String) {
        viewModelScope.launch {
            getNoteByTitleUseCase.invoke(id = id).let {
                _note.postValue(it)
            }
        }
    }

    fun deleteNote( onSuccess: () -> Unit) {
        viewModelScope.launch {
            note.value?.let {
                deleteNoteUseCase.invoke(note = it)
                onSuccess()
            }
        }
    }
}