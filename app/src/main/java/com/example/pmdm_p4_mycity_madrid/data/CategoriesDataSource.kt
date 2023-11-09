package com.example.pmdm_p4_mycity_madrid.data

import com.example.pmdm_p4_mycity_madrid.R
import com.example.pmdm_p4_mycity_madrid.model.Category

object CategoriesDataSource {

    private val subHosteleria = listOf(
        R.string.cafeterias,
        R.string.restaurantes
    )

    private val subOcio = listOf(
        R.string.centros_comerciales,
        R.string.cines,
        R.string.parques
    )

    private val subCultura = listOf(
        R.string.monumentos,
        R.string.museos
    )

    private val subAlojamiento = listOf(
        R.string.hoteles,
        R.string.apartamentos
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