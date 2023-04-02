package ua.vitolex.cooknotes.feature_note.presentation.screens.add_edit

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import ua.vitolex.cooknotes.R
import ua.vitolex.cooknotes.di.NoteApp
import ua.vitolex.cooknotes.feature_note.domain.model.Ingredient
import ua.vitolex.cooknotes.feature_note.domain.model.Note
import ua.vitolex.cooknotes.feature_note.domain.model.Step
import ua.vitolex.cooknotes.feature_note.domain.use_case.AddNoteEvent
import ua.vitolex.cooknotes.feature_note.presentation.components.CustomTextField
import ua.vitolex.cooknotes.feature_note.presentation.components.CustomButton
import ua.vitolex.cooknotes.feature_note.presentation.navigation.Screens
import ua.vitolex.cooknotes.ui.theme.*
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditScreen(navController: NavController, id: String?) {
    //для снекбара
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val viewModel = hiltViewModel<AddEditViewModel>()

    //стейт даних для нотатки
    val title = viewModel.title.value
    val category = viewModel.category.value
    val photo = viewModel.photo.value

    val ingredientTitle = viewModel.ingredientTitle.value
    val stepTitle = viewModel.stepTitle.value

    val ingredients = viewModel.ingredients.value
    val steps = viewModel.steps.value


    //для загрузки фото
    val getImageRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            if (uri != null) {
                NoteApp.getUriPermission(uri)
            }
            viewModel.onEvent(AddNoteEvent.EnteredPhoto(uri.toString()))
        }
    )
    //для перевірки унікальності заголовку отримуємо всі нотатки
    val note = viewModel.note.observeAsState().value

    //для центрування тексту в топбарі
    val modifier = Modifier
        .size(width = 70.dp, height = 50.dp)

    //для діалогу
    val openDialog = remember { mutableStateOf(false) }
    val alertText = remember { mutableStateOf("") }

    val noteToDelete = viewModel.noteToDelete

    val focusManager = LocalFocusManager.current

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            // reuse default SnackbarHost to have default animation and timing handling
            SnackbarHost(it) { data ->
                // custom snackbar with the custom colors
                Snackbar(
                    backgroundColor = TextColor,
                    contentColor = Color.White,
                    snackbarData = data
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Cook Notes",
                        style = MaterialTheme.typography.h1,
                        color = TextColor,
                        fontSize = 24.scaledSp(),
                        modifier = Modifier
                            .padding(start = 8.dp, top = 2.dp, bottom = 10.dp, end = 8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    Box(
                        modifier = modifier,
                        contentAlignment = Center
                    ) {
                        Spacer(modifier = Modifier.width(12.dp))
                        CustomButton(
                            size = 32.dp,
                            clickable = { navController.popBackStack()},
                            icon = R.drawable.ic_arrow_back,
                            description = "back button",
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .scale(0.8f)
                        )
                    }

                },
                actions = {
                    Box(modifier = modifier, contentAlignment = Center) {
                        CustomButton(
                            size = 32.dp,
                            clickable = {
                                viewModel.getNoteById(id = title)
                                if (
                                    id!!.isEmpty() &&
                                    note?.id != null
                                ) {
                                    openDialog.value = true
                                    alertText.value = if( Locale.current.region == "UA") "Вже існує нотатка з такою назвою"
                                    else "The note with that name already exists"
                                } else {
                                    if (title.isNotEmpty()) {
                                        viewModel.deleteNote(noteToDelete.value)
                                        viewModel.addNote(
                                            Note(
                                                id = title,
                                                title = title,
                                                category = category,
                                                photo = photo
                                            )
                                        ) {
                                            viewModel.deleteIngredients()
                                            viewModel.deleteSteps()
                                            if (ingredients.isNotEmpty()) {
                                                for (index in ingredients.indices) {
                                                    viewModel.addIngredient(
                                                        Ingredient(
                                                            noteId = title,
                                                            title = ingredients[index].title
                                                        )
                                                    )
                                                }
                                            }
                                            if (steps.isNotEmpty()) {
                                                for (index in steps.indices) {
                                                    viewModel.addStep(
                                                        Step(
                                                            noteId = title,
                                                            description = steps[index].description
                                                        )
                                                    )
                                                }
                                            }
                                            navController.navigate(Screens.MainScreen.rout) {
                                                popUpTo(Screens.MainScreen.rout) {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                    } else {
                                        if (title.isEmpty()) {
                                            openDialog.value = true
                                            alertText.value = if( Locale.current.region == "UA") "Нотатка повинна мати назву"
                                            else "The note must have a title"
                                        }

                                    }
                                }

                            },
                            icon = R.drawable.ic_check,
                            description = "save button",
                            modifier = Modifier,
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }

                },
                contentColor = TextColor,
                backgroundColor = PrimaryColor,
                modifier = Modifier
                    .height(60.dp)
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxSize()
                .background(PrimaryColor)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            //photo
            item {
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .background(PrimaryColor),
                    elevation = 5.dp,
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(6.dp)
                            .fillMaxSize()
                            .clip(RoundedCornerShape(4.dp))
                            .clickable { getImageRequest.launch(arrayOf("image/*")) }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_food),
                            contentDescription = "add photo",
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center),
                            tint = PrimaryColor
                        )
                        if (photo.isNotEmpty()) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    ImageRequest
                                        .Builder(LocalContext.current)
                                        .data(data = Uri.parse(photo))
                                        .build()
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(4.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            item {
                CustomTextField(
                    value = title,
                    onValueChange = {
                        viewModel.onEvent(AddNoteEvent.EnteredTitle(it))
                        viewModel.getNoteById(id = it)
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.Title),
                            style = MaterialTheme.typography.subtitle1,
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        autoCorrect = true,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                CustomTextField(
                    value = category,
                    onValueChange = {
                        viewModel.onEvent(AddNoteEvent.EnteredCategory(it))
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.Category),
                            style = MaterialTheme.typography.subtitle1,
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        autoCorrect = true,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
            }
            item {
                Text(
                    text = stringResource(R.string.Ingredients),
                    style = MaterialTheme.typography.h2,
                    color = TextColor,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(24.dp),
                    textAlign = TextAlign.Center
                )
            }
            items(ingredients.size) { num ->
                Row(
                    modifier = Modifier
                        .defaultMinSize(minHeight = 50.dp)
                        .background(Color.White)
                        .border(1.dp, PrimaryColor)
                        .padding(start = 12.dp, end = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${num + 1}. ${ingredients[num].title}",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(CenterVertically)
                            .weight(1f)
                    )
                    CustomButton(
                        clickable = {
                            viewModel.onEvent(
                                AddNoteEvent.RemoveIngredientFromList(
                                    ingredients[num]
                                )
                            )
                        },
                        icon = R.drawable.ic_cancel,
                        description = "delete button",
                        modifier = Modifier.scale(0.7f),
                    )
                }

            }
            item {
                Spacer(modifier = Modifier.height(2.dp))
                CustomTextField(
                    value = ingredientTitle,
                    onValueChange = {
                        viewModel.onEvent(AddNoteEvent.EnteredIngredient(it))
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.Ingredient),
                            style = MaterialTheme.typography.subtitle1,
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        autoCorrect = true,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if (ingredientTitle.isNotEmpty()) {
                                viewModel.onEvent(
                                    AddNoteEvent.AddIngredientToList(
                                        Ingredient(
                                            noteId = title,
                                            title = ingredientTitle
                                        )
                                    )
                                )
                            }
                        }
                    ),
                    trailingIcon = {
                        Box(
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                                .clip(CircleShape)
                                .background(Color.Transparent)
                                .clickable {
                                    if (ingredientTitle.isNotEmpty()) {
                                        viewModel.onEvent(
                                            AddNoteEvent.AddIngredientToList(
                                                Ingredient(
                                                    noteId = title,
                                                    title = ingredientTitle
                                                )
                                            )
                                        )
                                    }
                                })
                        {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "add ingredient",
                                tint = TextColor,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .scale(0.8f)
                            )
                        }
                    }
                )

            }
            item {
                Text(
                    text = stringResource(R.string.Steps),
                    style = MaterialTheme.typography.h2,
                    color = TextColor,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(24.dp),
                    textAlign = TextAlign.Center
                )
            }
            items(steps.size) { num ->
                Row(
                    modifier = Modifier
                        .defaultMinSize(minHeight = 50.dp)
                        .background(Color.White)
                        .border(1.dp, PrimaryColor)
                        .padding(start = 12.dp, end = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${num + 1}. ${steps[num].description}",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(CenterVertically)
                            .weight(1f)
                    )
                    CustomButton(
                        clickable = {
                            viewModel.onEvent(
                                AddNoteEvent.RemoveStepFromList(
                                    steps[num]
                                )
                            )
                        },
                        icon = R.drawable.ic_cancel,
                        description = "delete button",
                        modifier = Modifier.scale(0.7f),
                    )
                }

            }
            item {
                Spacer(modifier = Modifier.height(2.dp))
                CustomTextField(
                    value = stepTitle,
                    onValueChange = {
                        viewModel.onEvent(AddNoteEvent.EnteredStep(it))
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.Step),
                            style = MaterialTheme.typography.subtitle1,
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        autoCorrect = true,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if (stepTitle.isNotEmpty()) {
                                viewModel.onEvent(
                                    AddNoteEvent.AddStepToList(
                                        Step(
                                            noteId = title,
                                            description = stepTitle
                                        )
                                    )
                                )
                            }
                        }
                    ),
                    trailingIcon = {
                        Box(
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                                .clip(CircleShape)
                                .background(Color.Transparent)
                                .clickable {
                                    if (stepTitle.isNotEmpty()) {
                                        viewModel.onEvent(
                                            AddNoteEvent.AddStepToList(
                                                Step(
                                                    noteId = title,
                                                    description = stepTitle
                                                )
                                            )
                                        )
                                    }
                                })
                        {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "add step",
                                tint = TextColor,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .scale(0.8f)
                            )
                        }
                    }
                )
            }
        }
        if (openDialog.value) {
            AlertDialog(
                backgroundColor = PrimaryColor,
                onDismissRequest = { openDialog.value = false },
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = alertText.value,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Start
                        )
                    }
                },
                buttons = {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                    horizontalArrangement = Arrangement.End){
                        TextButton(
                            modifier = Modifier.width(70.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = Color.Red
                            ),
                            onClick = {
                                openDialog.value = false
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.Ok),
                                style = MaterialTheme.typography.body1,
                                color = Color.Red
                            )
                        }
                    }
                }
            )
        }
    }
}
@Composable
fun Int.scaledSp(): TextUnit {
    val value: Int = this
    return with(LocalDensity.current) {
        val fontScale = this.fontScale
        val textSize =  value / fontScale
        textSize.sp
    }
}