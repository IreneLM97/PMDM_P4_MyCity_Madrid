package com.example.pmdm_p4_mycity_madrid.data

import com.example.pmdm_p4_mycity_madrid.model.Place
import com.example.pmdm_p4_mycity_madrid.model.Subcategory

/**
 * Representa el estado de la interfaz de usuario para la visualización de lugares recomendados de la ciudad.
 *
 * @property currentSubcategory subcategoría de la cual se están mostrando lugares recomendados
 * @property placesList lista de lugares recomendados activa basada en la subcategoría actual
 * @property currentPlace lugar seleccionado por el usuario en la lista de lugares recomendados
 * @property isShowingListPage indica si la interfaz está mostrando la lista de lugares (true) o no (false)
 */
data class CityUiState(
    val currentSubcategory: Subcategory = CategoriesDataSource.getCategories()[0].subcategories[0],
    val placesList: List<Place> = currentSubcategory.places,
    val currentPlace: Place = placesList[0],
    val isShowingListPage: Boolean = true
)
