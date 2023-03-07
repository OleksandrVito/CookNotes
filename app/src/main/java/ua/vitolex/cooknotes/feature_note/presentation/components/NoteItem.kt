package ua.vitolex.cooknotes.feature_note.presentation.components


import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import ua.vitolex.cooknotes.R
import ua.vitolex.cooknotes.ui.theme.BorderColor
import ua.vitolex.cooknotes.ui.theme.PrimaryColor
import ua.vitolex.cooknotes.ui.theme.TextColor

@Composable
fun NoteItem(
    title: String,
    category: String,
    photo: String,
    modifier: Modifier,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp, top = 0.dp, end = 14.dp, bottom = 4.dp)
            .then(modifier),
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .border(width = 1.dp, color = BorderColor, shape = RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(end = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
        )
        {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(PrimaryColor)
            ) {
                if (photo.isNotEmpty()) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest
                                .Builder(LocalContext.current)
                                .data(Uri.parse(photo))
                                .build()
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .height(80.dp)
                            .width(80.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.table),
                        contentDescription = null,
                        modifier = Modifier
                            .height(80.dp)
                            .width(80.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Text(
                        text = title,
                        fontSize = 16.sp,
                        color = TextColor,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                    )

                }
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = category,
                        fontSize = 12.sp,
                        color = TextColor.copy(alpha = 0.5f),
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                    )

                }
            }

            Box(
                modifier = Modifier
                    .width(32.dp)
                    .height(32.dp)
                    .clip(CircleShape)
                    .background(Color.Transparent)
                    .then(modifier)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_forward),
                    contentDescription = "nav forward to detail",
                    tint = TextColor,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .scale(0.7f)
                )
            }

        }
    }
}
