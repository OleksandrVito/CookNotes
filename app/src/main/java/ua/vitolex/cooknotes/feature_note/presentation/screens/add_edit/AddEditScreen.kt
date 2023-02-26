package ua.vitolex.cooknotes.feature_note.presentation.screens.add_edit

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ua.vitolex.cooknotes.feature_note.domain.model.Note
import ua.vitolex.cooknotes.feature_note.domain.use_case.note.GetAllNotesUseCase
import ua.vitolex.cooknotes.feature_note.presentation.navigation.Screens
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditScreen(navController: NavController) {
    val viewModel = hiltViewModel<AddEditViewModel>()
    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 52.dp)
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF383838))
                        .clickable { navController.navigate(Screens.MainScreen.rout) }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "nav back",
                        tint = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Title") }
            )
            Spacer(modifier = Modifier.height(24.dp))
            TextField(
                value = category,
                onValueChange = { category = it },
                label = { Text(text = "Category") }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF383838))
                    .clickable {
                        viewModel.addNote(
                            Note(
                                id = title,
                                title = title,
                                category = category
                            )
                        ) {
                            Log.d("MuLog", "sssss")
//                            navController.navigate(Screens.MainScreen.rout)
                        }
                    }
            ) {
                Text(text = "Add ingredients")
            }
//            Spacer(modifier = Modifier.height(24.dp))
//            TextField(
//                value = ingredientsTitle,
//                onValueChange = { ingredientsTitle = it },
//                label = { Text(text = "Ingredients title") }
//            )

        }

    }
}