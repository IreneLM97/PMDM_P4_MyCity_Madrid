package com.example.pmdm_p4_mycity_madrid.data

import com.example.pmdm_p4_mycity_madrid.R
import com.example.pmdm_p4_mycity_madrid.model.Lugar

object CategoriasDataSource {
    /**
     * Lista de opciones principales
     */
    fun leerCategorias(): List<Int> {
        return listOf<Int> (
            R.string.hosteleria,
            R.string.ocio,
            R.string.cultura,
            R.string.alojamiento
        )
    }

    /**
     * Opciones de hostelería
     */
    fun leerCatHosteleria(): List<Int> {
        return listOf<Int>(
            R.string.cafeterias,
            R.string.restaurantes
        )
    }

    /**
     * Opciones de ocio
     */
    val catOcio = listOf(
        R.string.centros_comerciales,
        R.string.cines,
        R.string.parques
    )

    /**
     * Opciones de cultura
     */
    val catCultura = listOf(
        R.string.monumentos,
        R.string.museos
    )

    /**
     * Opciones de alojamientos
     */
    val catAlojamientos = listOf(
        R.string.hoteles,
        R.string.apartamentos
    )
}