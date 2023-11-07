package com.example.pmdm_p4_mycity_madrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Lugar(
    val id: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val nombreResourceId: Int,
    @StringRes val direccionResourceId: Int,
    @StringRes val descripcionResourceId: Int
)
