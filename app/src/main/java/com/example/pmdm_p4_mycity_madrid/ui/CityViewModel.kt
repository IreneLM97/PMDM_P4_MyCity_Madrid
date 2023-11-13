package com.example.pmdm_p4_mycity_madrid.ui

import androidx.lifecycle.ViewModel
import com.example.pmdm_p4_mycity_madrid.model.CityUiState
import com.example.pmdm_p4_mycity_madrid.model.initialState
import com.example.pmdm_p4_mycity_madrid.model.Place
import com.example.pmdm_p4_mycity_madrid.model.Subcategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
// TODO REVISAR Y COMENTAR
class CityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)

    val uiState: StateFlow<CityUiState> = _uiState

    fun updateCurrentPlace(
        selectedPlace: Place
    ) {
        _uiState.update {
            it.copy(
                currentPlace = selectedPlace
            )
        }
    }

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
        println("en cityviewmodel ${subcategory.nameResourceId}")
    }

    fun navigateToListPlacesPage() {
        _uiState.update {
            it.copy(
                isShowingListPage = true
            )
        }
    }

    fun navigateToDetailPlacePage() {
        _uiState.update {
            it.copy(
                isShowingListPage = false
            )
        }
    }
}