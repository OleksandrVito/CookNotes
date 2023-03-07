package ua.vitolex.cooknotes.feature_note.presentation.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import ua.vitolex.cooknotes.R
import ua.vitolex.cooknotes.feature_note.presentation.components.BannerAdView
import ua.vitolex.cooknotes.feature_note.presentation.components.CustomButton
import ua.vitolex.cooknotes.feature_note.presentation.components.NoteItem
import ua.vitolex.cooknotes.feature_note.presentation.navigation.Screens
import ua.vitolex.cooknotes.ui.theme.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<MainViewModel>()
    val notes = viewModel.notes.observeAsState(listOf()).value
    var query by remember { mutableStateOf("") }

    //для центрування тексту в топбарі
    val modifier = Modifier
        .size(width = 70.dp, height = 50.dp)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Cook Notes",
                        style = MaterialTheme.typography.h1,
                        color = TextColor,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 2.dp, bottom = 10.dp, end = 8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    Box(modifier = modifier) {}
                },
                actions = {
                    Box(modifier = modifier, contentAlignment = Alignment.Center) {
                        CustomButton(
                            size = 32.dp,
                            clickable = { navController.navigate(Screens.AddEditScreen.rout) },
                            icon = R.drawable.ic_add,
                            description = "add button",
                            modifier = Modifier
                        )
                    }

                },
                contentColor = TextColor,
                backgroundColor = PrimaryColor,
                modifier = Modifier
                    .height(60.dp)
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    PrimaryColor.copy(0.3f)
                )
        ) {
            TextField(
                value = query,
                onValueChange = { query = it.toString() },
                placeholder = {
                    Text(
                        text = stringResource(R.string.Search),
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 14.dp)
                    .height(50.dp)
                    .fillMaxWidth()
                    .border(1.dp, BorderColor, CircleShape),
                shape = CircleShape,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                ),
                maxLines = 1,
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                    tint = TextColor.copy(0.8f))
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = TextColor,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            val queriedNotes = if (query.isEmpty()) {
                notes
            } else {
                notes.filter {
                    it.category.uppercase().contains(query.uppercase()) ||
                            it.title.uppercase().contains(query.uppercase())
                }
            }
            LazyColumn(
                modifier=Modifier.weight(1f)
            ) {
                items(queriedNotes.size) {
                    NoteItem(
                        title = queriedNotes[it].title,
                        category = queriedNotes[it].category,
                        photo = queriedNotes[it].photo ?: "",
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screens.DetailsScreen.rout + "/${queriedNotes[it].id}")
                            }
                    )
                }
            }
            BannerAdView(id = stringResource(id = R.string.banner_ad_unit_id1))
        }
    }
}

