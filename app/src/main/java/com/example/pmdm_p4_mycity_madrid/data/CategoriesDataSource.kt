package com.example.pmdm_p4_mycity_madrid.data

import com.example.pmdm_p4_mycity_madrid.R
import com.example.pmdm_p4_mycity_madrid.model.Category
import com.example.pmdm_p4_mycity_madrid.model.Subcategory

object CategoriesDataSource {

    private val subHosteleria = listOf(
        Subcategory(R.drawable.icon_cafeteria, R.string.cafeterias, PlacesDataSource.getCafeterias()),
        Subcategory(R.drawable.icon_restaurante, R.string.restaurantes, PlacesDataSource.getRestaurantes())
    )

    private val subOcio = listOf(
        Subcategory(R.drawable.icon_comercial, R.string.centros_comerciales, PlacesDataSource.getComerciales()),
        Subcategory(R.drawable.icon_cine, R.string.cines, PlacesDataSource.getCines()),
        Subcategory(R.drawable.icon_parque, R.string.parques, PlacesDataSource.getParques())
    )

    private val subCultura = listOf(
        Subcategory(R.drawable.icon_monumento, R.string.monumentos, PlacesDataSource.getMonumentos()),
        Subcategory(R.drawable.icon_museo, R.string.museos, PlacesDataSource.getMuseos())
    )

    private val subAlojamiento = listOf(
        Subcategory(R.drawable.icon_hotel, R.string.hoteles, PlacesDataSource.getHoteles()),
        Subcategory(R.drawable.icon_apartamento, R.string.apartamentos, PlacesDataSource.getApartamentos())
    )

    fun getCategories(): List<Category> {
        return listOf(
            Category(R.drawable.icon_hosteleria, R.string.hosteleria, subHosteleria),
            Category(R.drawable.icon_ocio, R.string.ocio, subOcio),
            Category(R.drawable.icon_cultura, R.string.cultura, subCultura),
            Category(R.drawable.icon_alojamiento, R.string.alojamiento, subAlojamiento),
        )
    }

}