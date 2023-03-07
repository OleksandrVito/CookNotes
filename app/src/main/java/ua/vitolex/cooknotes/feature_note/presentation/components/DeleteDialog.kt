package ua.vitolex.cooknotes.feature_note.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ua.vitolex.cooknotes.R
import ua.vitolex.cooknotes.ui.theme.PrimaryColor
import ua.vitolex.cooknotes.ui.theme.TextColor

@Composable
fun DeleteDialog(
    openDialog: MutableState<Boolean>,
    text: String,
    action: () -> Unit
) {
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
                        text = text,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Start
                    )
                }
            },
            buttons = {
                Row(
                    Modifier.padding(14.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Spacer(modifier = Modifier.padding(26.dp))
                        TextButton(
                            modifier = Modifier.width(70.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = TextColor
                            ),
                            onClick = {
                                openDialog.value = false
                            }
                        ) {
                            Text(text = stringResource(R.string.No), style = MaterialTheme.typography.body1)
                        }
                        TextButton(
                            modifier = Modifier.width(70.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = Color.Red
                            ),
                            onClick = {
                                action.invoke()
                                openDialog.value = false
                            }
                        ) {
                            Text(text = stringResource(R.string.Yes), style = MaterialTheme.typography.body1, color = Color.Red)
                        }
                    }
                }
            }
        )
    }
}