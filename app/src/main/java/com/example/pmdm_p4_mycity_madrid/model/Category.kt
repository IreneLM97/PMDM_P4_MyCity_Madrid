package com.example.pmdm_p4_mycity_madrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Clase de datos que representa una categoría de lugares.
 *
 * @property imageResourceId identifica el recurso de la imagen de la categoría
 * @property nameResourceId identifica el recurso del nombre de la categoría
 * @property subcategories lista de subcategorías asociadas a la categoría
 */
data class Category(
    @DrawableRes val imageResourceId: Int,
    @StringRes val nameResourceId: Int,
    val subcategories: List<Subcategory>
)
