package com.example.pmdm_p4_mycity_madrid.data

import com.example.pmdm_p4_mycity_madrid.R

object CategoriasDataSource {
    /**
     * Lista de opciones principales
     */
    val categorias = listOf(
        R.string.hosteleria,
        R.string.ocio,
        R.string.cultura,
        R.string.alojamiento
    )

    /**
     * Opciones de hostelería
     */
    val catHosteleria = listOf(
        R.string.cafeterias,
        R.string.restaurantes
    )

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