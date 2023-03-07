package ua.vitolex.cooknotes.feature_note.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ua.vitolex.cooknotes.ui.theme.BorderColor
import ua.vitolex.cooknotes.ui.theme.SecondaryColor
import ua.vitolex.cooknotes.ui.theme.TextColor

@Composable
fun CustomTextField(
    value: String,
    onValueChange: ((String) -> Unit),
    placeholder: @Composable() (() -> Unit)? = {},
    trailingIcon: @Composable() (() -> Unit)? = {},
) {
    TextField(
        value = value ?: "",
        onValueChange = onValueChange ?: {},
        placeholder = placeholder,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderColor, shape = RoundedCornerShape(3.dp)),
        shape = RoundedCornerShape(3.dp),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
        ),
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = TextColor,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = SecondaryColor,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        trailingIcon = trailingIcon
    )
}
