package com.example.pmdm_p4_mycity_madrid.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pmdm_p4_mycity_madrid.R
import com.example.pmdm_p4_mycity_madrid.model.Category

/**
 * Define la estructura que tendrá la pantalla que lista las recomendaciones.
 */
@Composable
fun ListRecommendationScreen(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Creamos la lista de recomendaciones
       // ShowRecommendations()
    }
}

/**
 * Función para mostrar la lista de recomendaciones.
 */
@Composable
private fun ShowRecommendations(){
    
}


/**
 * Función para previsualizar la pantalla.
 */
@Preview
@Composable
fun ListRecommendationScreenPreview(){
    ListRecommendationScreen(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    )
}

