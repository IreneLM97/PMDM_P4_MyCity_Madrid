package com.example.pmdm_p4_mycity_madrid.ui

import androidx.lifecycle.ViewModel
import com.example.pmdm_p4_mycity_madrid.data.CityUiState
import com.example.pmdm_p4_mycity_madrid.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
// TODO REVISAR Y COMENTAR
class CityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        CityUiState(

        )
    )
    val uiState: StateFlow<CityUiState> = _uiState

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