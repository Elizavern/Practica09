package com.example.practica09

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen() {

    // Links de las imagenes
    val imagenes = remember {
        listOf(
            "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?w=600",
            "https://images.unsplash.com/photo-1501785888041-af3ef285b470?w=600",
            "https://images.unsplash.com/photo-1470770841072-f978cf4d019e?w=600",
            "https://images.unsplash.com/photo-1472396961693-142e6e269027?w=600",
            "https://images.unsplash.com/photo-1441974231531-c6227db76b6e?w=600",
            "https://images.unsplash.com/photo-1470252649378-9c29740c9fa8?w=600",
            "https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?w=600",
            "https://images.unsplash.com/photo-1500534623283-312aade485b7?w=600",
            "https://images.unsplash.com/photo-1506744038136-46273834b3fb?w=600",
            "https://images.unsplash.com/photo-1511497584788-876760111969?w=600",
            "https://images.unsplash.com/photo-1518837695005-2083093ee35b?w=600",
            "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=600"
        )
    }

    var imagenSeleccionada by remember {
        mutableStateOf<String?>(null)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Mi Galería")
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(8.dp)
        ) {

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(imagenes) { url ->

                    ImagenItem(
                        url = url,
                        onClick = {
                            imagenSeleccionada = url
                        }
                    )
                }
            }
        }
    }

    // Mostrar imagen ampliada al seleccionarla
    imagenSeleccionada?.let { url ->

        AlertDialog(
            onDismissRequest = {
                imagenSeleccionada = null
            },
            title = {
                Text("Vista de imagen")
            },
            text = {
                SubcomposeAsyncImage(
                    model = url,
                    contentDescription = "Imagen ampliada",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentScale = ContentScale.Fit,
                    loading = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    },
                    error = {
                        Text("No se pudo cargar la imagen")
                    }
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        imagenSeleccionada = null
                    }
                ) {
                    Text("Cerrar")
                }
            }
        )
    }
}

@Composable
fun ImagenItem(
    url: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp)
    ) {
        SubcomposeAsyncImage(
            model = url,
            contentDescription = "Imagen de la galería",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            },
            error = {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Error al cargar")
                }
            }
        )
    }
}