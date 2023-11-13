package com.example.pmdm_p4_mycity_madrid.model

import com.example.pmdm_p4_mycity_madrid.data.CategoriesDataSource

/**
 * Representa el estado de la interfaz de usuario para la visualización de lugares recomendados de la ciudad.
 *
 * @property currentSubcategory subcategoría seleccionada por el usuario de la cual se están mostrando lugares recomendados
 * @property currentPlace lugar seleccionado por el usuario en la lista de lugares recomendados
 * @property isShowingListPage indica si la interfaz está mostrando la lista de lugares (true) o no (false)
 */
data class CityUiState(
    val currentSubcategory: Subcategory,
    val currentPlace: Place,
    val isShowingListPage: Boolean = true
)

/** Inicializamos el estado */
val initialCategory = CategoriesDataSource.getCategories()[0]
val initialState = CityUiState(
    currentSubcategory = initialCategory.subcategories[0],
    currentPlace = initialCategory.subcategories[0].places[0]
)
