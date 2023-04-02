package ua.vitolex.cooknotes.feature_note.presentation.screens.details

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import ua.vitolex.cooknotes.R
import ua.vitolex.cooknotes.feature_note.presentation.components.BannerAdView
import ua.vitolex.cooknotes.feature_note.presentation.components.CustomButton
import ua.vitolex.cooknotes.feature_note.presentation.components.DeleteDialog
import ua.vitolex.cooknotes.feature_note.presentation.navigation.Screens
import ua.vitolex.cooknotes.ui.theme.BackgroundColor
import ua.vitolex.cooknotes.ui.theme.PrimaryColor
import ua.vitolex.cooknotes.ui.theme.TextColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navController: NavController, id: String?) {
    val viewModel = hiltViewModel<DetailsViewModel>()
    val note = viewModel.note.observeAsState().value
    val ingredients = viewModel.ingredients.observeAsState(listOf()).value
    val steps = viewModel.steps.observeAsState(listOf()).value
    id?.let {
        viewModel.getNoteById(id = it)
        viewModel.getAllIngredients(noteId = it)
        viewModel.getAllSteps(noteId = it)
    }

    //для центрування тексту в топбарі
    val modifier = Modifier
        .size(width = 90.dp, height = 50.dp)

    //для діалогу видалення нотатки
    val openDialog = remember {
        mutableStateOf(false)
    }

    Scaffold(
        snackbarHost = {
            // reuse default SnackbarHost to have default animation and timing handling
            SnackbarHost(it) { data ->
                // custom snackbar with the custom colors
                Snackbar(
                    backgroundColor = BackgroundColor,
                    contentColor = TextColor,
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
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(start = 20.dp, top = 2.dp, bottom = 10.dp, end = 0.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    Box(modifier = modifier, contentAlignment = Alignment.Center) {
                        Spacer(modifier = Modifier.width(22.dp))
                        CustomButton(
                            size = 32.dp,
                            clickable = { navController.popBackStack() },
                            icon = R.drawable.ic_arrow_back,
                            description = "back button",
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .scale(0.8f)
                        )
                    }

                },
                actions = {
                    Box(modifier = modifier, contentAlignment = Alignment.Center) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            CustomButton(
                                size = 32.dp,
                                clickable = {
                                    openDialog.value = true
                                },
                                icon = R.drawable.ic_cancel,
                                description = "delete button",
                                modifier = Modifier.scale(0.9f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            CustomButton(
                                size = 32.dp,
                                clickable = {
                                    navController.navigate(Screens.AddEditScreen.rout +
                                        "?id=${note?.id}")
                                },
                                icon = R.drawable.ic_edit,
                                description = "delete button",
                                modifier = Modifier.scale(1f)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        }

                    }


                },
                contentColor = TextColor,
                backgroundColor = PrimaryColor,
                modifier = Modifier
                    .height(60.dp)
            )
        }

    ) {
        Column() {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxSize()
                    .weight(1f)
                    .background(PrimaryColor),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                //photo
                item {
                    Card(
                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth()
                            .background(Color.White),
                        elevation = 5.dp,
                    ) {
                        if (note?.photo?.isNotEmpty() == true) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    ImageRequest
                                        .Builder(LocalContext.current)
                                        .data(data = Uri.parse(note.photo))
                                        .build()
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.table),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Text(
                            text = note?.title ?: "",
                            fontSize = 22.sp,
                            style = MaterialTheme.typography.h2,
                            color = TextColor,
                            modifier = Modifier
                                .padding(top = 18.dp, bottom = 0.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = note?.category ?: "",
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.body1,
                            color = TextColor,
                            modifier = Modifier
                                .padding(top = 4.dp, bottom = 12.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.padding(4.dp))

                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Text(
                            text = stringResource(id = R.string.Ingredients),
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.h2,
                            color = TextColor,
                            modifier = Modifier
                                .padding(start = 28.dp, top = 20.dp, bottom = 10.dp, end = 18.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Start
                        )
                    }

                }
                items(ingredients.size) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(start = 12.dp, top = 0.dp, bottom = 8.dp, end = 8.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(top = 4.dp, start = 4.dp)
                                .width(22.dp)
                                .height(22.dp)
                                .clip(CircleShape)
                                .background(BackgroundColor),
                            contentAlignment = Alignment.Center
                        )
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_ingredients),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .scale(0.65f)
                                    .then(modifier),
                                tint = TextColor.copy(0.8f)
                            )
                        }
                        Text(
                            text = ingredients[it].title,
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.body1,
                            color = TextColor,
                            modifier = Modifier
                                .padding(start = 10.dp, top = 2.dp, bottom = 4.dp, end = 4.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Start
                        )
                    }
                }
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .background(Color.White))
                }
                item {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Text(
                            text = stringResource(id = R.string.Steps),
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.h2,
                            color = TextColor,
                            modifier = Modifier
                                .padding(start = 28.dp, top = 20.dp, bottom = 10.dp, end = 18.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Start
                        )
                    }

                }
                items(steps.size) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(start = 12.dp, top = 0.dp, bottom = 8.dp, end = 8.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(top = 4.dp, start = 4.dp)
                                .width(22.dp)
                                .height(22.dp)
                                .clip(CircleShape)
                                .background(BackgroundColor),
                            contentAlignment = Alignment.Center
                        )
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_cook2),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .scale(0.7f)
                                    .then(modifier),
                                tint = TextColor.copy(0.7f)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                        ) {
                            Text(
                                text = steps[it].description,
                                fontSize = 16.sp,
                                style = MaterialTheme.typography.body1,
                                color = TextColor,
                                modifier = Modifier
                                    .padding(start = 10.dp, top = 2.dp, bottom = 4.dp, end = 4.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .background(Color.White))
                }

            }
//            BannerAdView(id = stringResource(id = R.string.banner_ad_unit_id2))
        }
        DeleteDialog(openDialog = openDialog,  text = stringResource(R.string.AreYouSure)) {
            viewModel.deleteNote {
                viewModel.deleteIngredients()
                viewModel.deleteSteps()
                navController.navigate(Screens.MainScreen.rout){
                    popUpTo(Screens.MainScreen.rout){
                        inclusive = true
                    }
                }
            }

        }
    }
}