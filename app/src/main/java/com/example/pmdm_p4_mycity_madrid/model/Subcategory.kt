package com.example.pmdm_p4_mycity_madrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Clase de datos que representa una subcategoría dentro de una categoría principal de lugares.
 *
 * @property imageResourceId identifica el recurso de la imagen de la subcategoría
 * @property nameResourceId identifica el recurso del nombre de la subcategoría
 * @property places lista de lugares asociados a esta subcategoría
 */
data class Subcategory(
    @DrawableRes val imageResourceId: Int,
    @StringRes val nameResourceId: Int,
    val places: List<Place>
)
