package com.example.pmdm_p4_mycity_madrid.data

import com.example.pmdm_p4_mycity_madrid.model.Place
import com.example.pmdm_p4_mycity_madrid.model.Subcategory

data class PlaceUiState(
    val currentSubcategory: Subcategory = CategoriesDataSource.getCategories()[0].subcategories[0],
    val placesList: List<Place> = currentSubcategory.places,
    val currentPlace: Place = placesList[0],
    val isShowingListPage: Boolean = true
)
