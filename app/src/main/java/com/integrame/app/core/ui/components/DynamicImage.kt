package com.integrame.app.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.integrame.app.core.data.model.content.BitMapImage
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.LocalImage
import com.integrame.app.core.data.model.content.RemoteImage

@Composable
fun DynamicImage(
    image: ImageContent,
    modifier: Modifier = Modifier
) {
    val imageData: Pair<Painter, String> = when (image) {
        is LocalImage -> {
            Pair(painterResource(id = image.id), stringResource(id = image.altDescription))
        }
        is BitMapImage -> {
            Pair(BitmapPainter(image.bitmap.asImageBitmap()), image.altDescription)
        }
        is RemoteImage -> {
            Pair(
                rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image.imageUrl)
                        .size(coil.size.Size.ORIGINAL)
                        .build()
                ),
                image.altDescription
            )
        }
    }

    Image(
        painter = imageData.first,
        contentDescription = imageData.second,
        modifier = modifier
    )
}

@Composable
fun DynamicImage(
    image: ImageContent,
    displayLocalImage: @Composable (LocalImage, Modifier) -> Unit,
    displayBitMapImage: @Composable (BitMapImage, Modifier) -> Unit,
    displayRemoteImage: @Composable (RemoteImage, Modifier) -> Unit,
    modifier: Modifier = Modifier
) {
    when (image) {
        is LocalImage -> {
            displayLocalImage(image, modifier)
        }
        is BitMapImage -> {
            displayBitMapImage(image, modifier)
        }
        is RemoteImage -> {
            displayRemoteImage(image, modifier)
        }
    }
}
