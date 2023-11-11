package com.example.pmdm_p4_mycity_madrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Clase de datos que representa un lugar.
 *
 * @property id identificador del lugar
 * @property imageResourceId identifica el recurso de la imagen del lugar
 * @property nameResourceId identifica el recurso del nombre del lugar
 * @property dirResourceId identifica el recurso de la dirección del lugar
 * @property descResourceId identifica el recurso de la descripción del lugar
 */
data class Place(
    val id: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val nameResourceId: Int,
    @StringRes val dirResourceId: Int,
    @StringRes val descResourceId: Int
)
