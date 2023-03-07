package ua.vitolex.cooknotes.feature_note.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ua.vitolex.cooknotes.ui.theme.TextColor


@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    size: Dp = 32.dp,
    clickable: () -> Unit,
    backgroundColor: Color = Color.Transparent,
    icon: Int,
    description: String,
) {
    Box(
        modifier = Modifier
            .width(size)
            .height(size)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable {
                clickable.invoke()
            },
        contentAlignment = Alignment.Center
    )
    {
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = description,
            modifier = Modifier
                .align(Alignment.Center)
                .then(modifier),
            tint = TextColor.copy(0.8f)
        )
    }
}