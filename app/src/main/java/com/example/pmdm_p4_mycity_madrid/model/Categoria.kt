package com.example.pmdm_p4_mycity_madrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Categoria(
    @DrawableRes val imageResourceId: Int,
    @StringRes val nombreResourceId: Int,
    val subCategorias: List<Int>
)
