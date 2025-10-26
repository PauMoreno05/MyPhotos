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
    @DrawableRes val resId: Int,
    val description: String = "Imagen de la galería"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPhotosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Pantalla()
                }
            }
        }
    }
}

val fotos = listOf(
    Photo(1, R.drawable.image1, "foto1"),
    Photo(2, R.drawable.image2, "foto2"),
    Photo(3, R.drawable.image3, "foto3"),
    Photo(4, R.drawable.image4, "foto4"),
    Photo(5, R.drawable.image5, "foto5"),
    Photo(6, R.drawable.image6, "foto5"),
    Photo(7, R.drawable.image7, "foto6"),
    Photo(8, R.drawable.image8, "foto7")
)

@Composable
fun Pantalla() {
    var fotoSelecionada by remember { mutableStateOf(fotos.first().id) }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.White)
                .padding(2.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(fotos) { photo ->
                Image(
                    painter = painterResource(id = photo.resId),
                    contentDescription = photo.description,
                    modifier = Modifier
                        .size(180.dp)
                        .clickable { fotoSelecionada = photo.id }
                )
            }
        }



        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            val fotoPorDefecto = fotos.find { it.id == fotoSelecionada }

            if (fotoPorDefecto != null) {
                Image(
                    painter = painterResource(id = fotoPorDefecto.resId),
                    contentDescription = fotoPorDefecto.description,
                    contentScale = ContentScale.Fit,
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
fun PantallaPreview() {
    MyPhotosTheme {
        Pantalla()
    }
}
