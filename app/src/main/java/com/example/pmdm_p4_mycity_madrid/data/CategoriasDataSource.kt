package com.example.pmdm_p4_mycity_madrid.data

import com.example.pmdm_p4_mycity_madrid.R
import com.example.pmdm_p4_mycity_madrid.model.Categoria
import com.example.pmdm_p4_mycity_madrid.model.Lugar

object CategoriasDataSource {

    val subHosteleria = listOf<Int>(
        R.string.cafeterias,
        R.string.restaurantes
    )

    val subOcio = listOf(
        R.string.centros_comerciales,
        R.string.cines,
        R.string.parques
    )

    val subCultura = listOf(
        R.string.monumentos,
        R.string.museos
    )

    val subAlojamiento = listOf(
        R.string.hoteles,
        R.string.apartamentos
    )

    fun getCategorias(): List<Categoria> {
        return listOf(
            Categoria(R.drawable.icon_hosteleria, R.string.hosteleria, subHosteleria),
            Categoria(R.drawable.icon_hosteleria, R.string.ocio, subOcio),
            Categoria(R.drawable.icon_hosteleria, R.string.cultura, subCultura),
            Categoria(R.drawable.icon_hosteleria, R.string.alojamiento, subAlojamiento),
        )
    }

}