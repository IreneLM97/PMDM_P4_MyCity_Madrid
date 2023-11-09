package com.example.pmdm_p4_mycity_madrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Subcategoria(
    @DrawableRes val imageResourceId: Int,
    @StringRes val nombreResourceId: Int,
    val recomendaciones: List<Lugar>
)
