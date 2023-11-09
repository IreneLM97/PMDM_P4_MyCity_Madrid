package com.example.pmdm_p4_mycity_madrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Subcategory(
    @DrawableRes val imageResourceId: Int,
    @StringRes val nameResourceId: Int,
    val recommendations: List<Place>
)
