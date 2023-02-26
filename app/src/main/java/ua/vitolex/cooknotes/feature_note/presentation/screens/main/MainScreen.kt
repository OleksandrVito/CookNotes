package ua.vitolex.cooknotes.feature_note.presentation.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ua.vitolex.cooknotes.feature_note.presentation.components.NoteItem
import ua.vitolex.cooknotes.feature_note.presentation.navigation.Screens


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<MainViewModel>()
    val notes = viewModel.notes.observeAsState(listOf()).value
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screens.AddEditScreen.rout) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = Color.White,
                    contentDescription = "add"
                )
            }
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Text(
                    text = "Notes",
                    fontSize = 42.sp,
                    modifier = Modifier.padding(top = 43.dp, start = 24.dp, bottom = 12.dp)
                )
            }

         items(notes) {
                NoteItem(
                    title = it.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 24.dp)
                        .clickable {
                            navController.navigate(Screens.DetailsScreen.rout + "/${it.id}")
                        }
                )
            }
        }
    }
}
