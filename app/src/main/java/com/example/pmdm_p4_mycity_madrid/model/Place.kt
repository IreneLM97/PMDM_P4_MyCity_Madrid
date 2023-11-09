package com.example.pmdm_p4_mycity_madrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(
    val id: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val nameResourceId: Int,
    @StringRes val dirResourceId: Int,
    @StringRes val descResourceId: Int
)
