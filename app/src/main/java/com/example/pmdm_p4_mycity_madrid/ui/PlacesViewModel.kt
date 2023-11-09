package com.example.pmdm_p4_mycity_madrid.ui

import androidx.lifecycle.ViewModel
import com.example.pmdm_p4_mycity_madrid.data.PlaceUiState
import com.example.pmdm_p4_mycity_madrid.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PlacesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        PlaceUiState(

        )
    )
    val uiState: StateFlow<PlaceUiState> = _uiState

    fun updateCurrentPlace(selectedPlace: Place) {
        _uiState.update {
            it.copy(currentPlace = selectedPlace)
        }
    }

    fun navigateToListPage() {
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }

    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(isShowingListPage = false)
        }
    }
}