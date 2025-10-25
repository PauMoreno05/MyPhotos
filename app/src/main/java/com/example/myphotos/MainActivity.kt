package com.example.myphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myphotos.ui.theme.MyPhotosTheme

data class Photo(
    val id: Int,
    @DrawableRes val resId: Int, // ID del recurso drawable
    val description: String = "Imagen de la galería"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPhotosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background // Fondo blanco o del tema
                ) {
                    GalleryScreen()
                }
            }
        }
    }
}

val photoList = listOf(
    Photo(1, R.drawable.image1, "Ballena"),
    Photo(2, R.drawable.image2, "Flores"),
    Photo(3, R.drawable.image3, "Río"),
    Photo(4, R.drawable.image4, "Árboles de otoño"),
    Photo(5, R.drawable.image5, "Bosque"),
    Photo(6, R.drawable.image6, "Montaña"),
    Photo(7, R.drawable.image7, "Atardecer"),
    Photo(8, R.drawable.image8, "Playa")
)

@Composable
fun GalleryScreen() {
    var selectedPhotoId by remember { mutableStateOf(photoList.first().id) }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.White)
                .padding(2.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp) // Espacio entre miniaturas
        ) {
            items(photoList) { photo ->
                Image(
                    painter = painterResource(id = photo.resId),
                    contentDescription = photo.description,
                    modifier = Modifier
                        .size(180.dp) // Tamaño de cada miniatura
                        .clickable { selectedPhotoId = photo.id } // 💡 Al hacer click, actualiza la imagen seleccionada
                )
            }
        }



        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White),
            contentAlignment = Alignment.Center // Centra la imagen dentro del Box
        ) {
            val currentSelectedPhoto = photoList.find { it.id == selectedPhotoId }

            if (currentSelectedPhoto != null) {
                Image(
                    painter = painterResource(id = currentSelectedPhoto.resId),
                    contentDescription = currentSelectedPhoto.description,
                    contentScale = ContentScale.Fit, // Usa FIT para que la imagen quepa dentro del marco sin cortarse
                    modifier = Modifier
                        .fillMaxSize(1f)

                )
            } else {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GalleryScreenPreview() {
    MyPhotosTheme {
        GalleryScreen()
    }
}
