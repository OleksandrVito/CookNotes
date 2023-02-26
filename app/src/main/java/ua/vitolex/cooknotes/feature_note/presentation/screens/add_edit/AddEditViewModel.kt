package ua.vitolex.cooknotes.feature_note.presentation.screens.add_edit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.vitolex.cooknotes.feature_note.domain.model.Note
import ua.vitolex.cooknotes.feature_note.domain.use_case.note.AddNoteUseCase
import ua.vitolex.cooknotes.feature_note.domain.use_case.note.GetAllNotesUseCase
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase
): ViewModel() {
    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch {
            addNoteUseCase.invoke(note = note)
            onSuccess()
        }
    }
}