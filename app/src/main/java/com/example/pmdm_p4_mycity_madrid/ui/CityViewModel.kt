package com.example.pmdm_p4_mycity_madrid.ui

import androidx.lifecycle.ViewModel
import com.example.pmdm_p4_mycity_madrid.model.CityUiState
import com.example.pmdm_p4_mycity_madrid.model.initialState
import com.example.pmdm_p4_mycity_madrid.model.Place
import com.example.pmdm_p4_mycity_madrid.model.Subcategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel que gestiona el estado de la interfaz de usuario.
 */
class CityViewModel : ViewModel() {

    // Variable que representa la información de la interfaz de usuario
    private val _uiState = MutableStateFlow(initialState)

    // Variable que permite actualizar la información la interfaz de usuario
    val uiState: StateFlow<CityUiState> = _uiState

    /**
     * Actualiza el lugar en el estado de la interfaz de usuario.
     *
     * @param selectedPlace lugar seleccionado por el usuario
     */
    fun updateCurrentPlace(
        selectedPlace: Place
    ) {
        _uiState.update {
            it.copy(
                currentPlace = selectedPlace
            )
        }
    }

    /**
     * Actualiza la subcategoría y el lugar en el estado de la interfaz de usuario.
     *
     * @param subcategory subcategoría seleccionada por el usuario
     */
    fun updateCurrentSubcategory(
        subcategory: Subcategory
    ) {
        _uiState.update {
            it.copy(
                currentSubcategory = subcategory,
                currentPlace = subcategory.places[0],
                isShowingListPage = true
            )
        }
    }

    /**
     * Navega a la página que muestra la lista de lugares.
     */
    fun navigateToListPlacesPage() {
        _uiState.update {
            it.copy(
                isShowingListPage = true
            )
        }
    }

    /**
     * Navega a la página que muestra los detalles de un lugar específico.
     */
    fun navigateToDetailPlacePage() {
        _uiState.update {
            it.copy(
                isShowingListPage = false
            )
        }
    }
}