package com.example.pmdm_p4_mycity_madrid.utils

/**
 * Clase que representa los diferentes tipos de contenido de la pantalla en función de su tamaño.
 *
 * - [ListOnly]: Indica que se debe mostrar la lista de lugares y los detalles en pantallas distintas.
 * - [ListAndDetail]: Indica que se deben mostrar tanto la lista como los detalles del lugar en la misma pantalla.
 */
enum class PlacesContentType {
    ListOnly,
    ListAndDetail
}
