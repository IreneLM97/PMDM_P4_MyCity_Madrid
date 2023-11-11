package com.example.pmdm_p4_mycity_madrid.data

import com.example.pmdm_p4_mycity_madrid.R
import com.example.pmdm_p4_mycity_madrid.model.Place

object PlacesDataSource {
    fun getCafeterias(): List<Place> {
        return listOf(
            Place(1, R.drawable.cafe1, R.string.nom_cafe1, R.string.dir_cafe1, R.string.desc_cafe1),
            Place(2, R.drawable.cafe2, R.string.nom_cafe2, R.string.dir_cafe2, R.string.desc_cafe2),
            Place(3, R.drawable.cafe3, R.string.nom_cafe3, R.string.dir_cafe3, R.string.desc_cafe3),
            Place(4, R.drawable.cafe4, R.string.nom_cafe4, R.string.dir_cafe4, R.string.desc_cafe4),
            Place(5, R.drawable.cafe5, R.string.nom_cafe5, R.string.dir_cafe5, R.string.desc_cafe5),
            Place(6, R.drawable.cafe6, R.string.nom_cafe6, R.string.dir_cafe6, R.string.desc_cafe6),
            Place(7, R.drawable.cafe7, R.string.nom_cafe7, R.string.dir_cafe7, R.string.desc_cafe7),
        )
    }

    fun getRestaurantes(): List<Place> {
        return listOf(
            Place(1, R.drawable.rest1, R.string.nom_restaurante1, R.string.dir_restaurante1, R.string.desc_restaurante1),
            Place(2, R.drawable.rest2, R.string.nom_restaurante2, R.string.dir_restaurante2, R.string.desc_restaurante2),
            Place(3, R.drawable.rest3, R.string.nom_restaurante3, R.string.dir_restaurante3, R.string.desc_restaurante3),
            Place(4, R.drawable.rest4, R.string.nom_restaurante4, R.string.dir_restaurante4, R.string.desc_restaurante4),
            Place(5, R.drawable.rest5, R.string.nom_restaurante5, R.string.dir_restaurante5, R.string.desc_restaurante5),
            Place(6, R.drawable.rest6, R.string.nom_restaurante6, R.string.dir_restaurante6, R.string.desc_restaurante6),
            Place(7, R.drawable.rest7, R.string.nom_restaurante7, R.string.dir_restaurante7, R.string.desc_restaurante7)
        )
    }

    fun getComerciales(): List<Place> {
        return listOf(
            Place(1, R.drawable.comercial1, R.string.nom_comercial1, R.string.dir_comercial1, R.string.desc_comercial1),
            Place(2, R.drawable.comercial2, R.string.nom_comercial2, R.string.dir_comercial2, R.string.desc_comercial2),
            Place(3, R.drawable.comercial3, R.string.nom_comercial3, R.string.dir_comercial3, R.string.desc_comercial3),
            Place(4, R.drawable.comercial4, R.string.nom_comercial4, R.string.dir_comercial4, R.string.desc_comercial4),
            Place(5, R.drawable.comercial5, R.string.nom_comercial5, R.string.dir_comercial5, R.string.desc_comercial5),
            Place(6, R.drawable.comercial6, R.string.nom_comercial6, R.string.dir_comercial6, R.string.desc_comercial6)
        )
    }

    fun getCines(): List<Place> {
        return listOf(
            Place(1, R.drawable.cine1, R.string.nom_cine1, R.string.dir_cine1, R.string.desc_cine1),
            Place(2, R.drawable.cine2, R.string.nom_cine2, R.string.dir_cine2, R.string.desc_cine2),
            Place(3, R.drawable.cine3, R.string.nom_cine3, R.string.dir_cine3, R.string.desc_cine3),
            Place(4, R.drawable.cine4, R.string.nom_cine4, R.string.dir_cine4, R.string.desc_cine4),
            Place(5, R.drawable.cine5, R.string.nom_cine5, R.string.dir_cine5, R.string.desc_cine5)
        )
    }

    fun getParques(): List<Place> {
        return listOf(
            Place(1, R.drawable.parq1, R.string.nom_parque1, R.string.dir_parque1, R.string.desc_parque1),
            Place(2, R.drawable.parq2, R.string.nom_parque2, R.string.dir_parque2, R.string.desc_parque2),
            Place(3, R.drawable.parq3, R.string.nom_parque3, R.string.dir_parque3, R.string.desc_parque3),
            Place(4, R.drawable.parq4, R.string.nom_parque4, R.string.dir_parque4, R.string.desc_parque4),
            Place(5, R.drawable.parq5, R.string.nom_parque5, R.string.dir_parque5, R.string.desc_parque5)
        )
    }

    fun getMonumentos(): List<Place> {
        return listOf(
            Place(1, R.drawable.monu1, R.string.nom_monumento1, R.string.dir_monumento1, R.string.desc_monumento1),
            Place(2, R.drawable.monu2, R.string.nom_monumento2, R.string.dir_monumento2, R.string.desc_monumento2),
            Place(3, R.drawable.monu3, R.string.nom_monumento3, R.string.dir_monumento3, R.string.desc_monumento3),
            Place(4, R.drawable.monu4, R.string.nom_monumento4, R.string.dir_monumento4, R.string.desc_monumento4),
            Place(5, R.drawable.monu5, R.string.nom_monumento5, R.string.dir_monumento5, R.string.desc_monumento5)
        )
    }

    fun getMuseos(): List<Place> {
        return listOf(
            Place(1, R.drawable.muse1, R.string.nom_museo1, R.string.dir_museo1, R.string.desc_museo1),
            Place(2, R.drawable.muse2, R.string.nom_museo2, R.string.dir_museo2, R.string.desc_museo2),
            Place(3, R.drawable.muse3, R.string.nom_museo3, R.string.dir_museo3, R.string.desc_museo3),
            Place(4, R.drawable.muse4, R.string.nom_museo4, R.string.dir_museo4, R.string.desc_museo4),
            Place(5, R.drawable.muse5, R.string.nom_museo5, R.string.dir_museo5, R.string.desc_museo5),
            Place(6, R.drawable.muse6, R.string.nom_museo6, R.string.dir_museo6, R.string.desc_museo6)
        )
    }

    fun getHoteles(): List<Place> {
        return listOf(
            Place(1, R.drawable.hote1, R.string.nom_hotel1, R.string.dir_hotel1, R.string.desc_hotel1),
            Place(2, R.drawable.hote2, R.string.nom_hotel2, R.string.dir_hotel2, R.string.desc_hotel2),
            Place(3, R.drawable.hote3, R.string.nom_hotel3, R.string.dir_hotel3, R.string.desc_hotel3),
            Place(4, R.drawable.hote4, R.string.nom_hotel4, R.string.dir_hotel4, R.string.desc_hotel4),
            Place(5, R.drawable.hote5, R.string.nom_hotel5, R.string.dir_hotel5, R.string.desc_hotel5),
            Place(6, R.drawable.hote6, R.string.nom_hotel6, R.string.dir_hotel6, R.string.desc_hotel6)
        )
    }

    fun getApartamentos(): List<Place> {
        return listOf(
            Place(1, R.drawable.apar1, R.string.nom_apartamento1, R.string.dir_apartamento1, R.string.desc_apartamento1),
            Place(2, R.drawable.apar2, R.string.nom_apartamento2, R.string.dir_apartamento2, R.string.desc_apartamento2),
            Place(3, R.drawable.apar3, R.string.nom_apartamento3, R.string.dir_apartamento3, R.string.desc_apartamento3),
            Place(4, R.drawable.apar4, R.string.nom_apartamento4, R.string.dir_apartamento4, R.string.desc_apartamento4),
            Place(5, R.drawable.apar5, R.string.nom_apartamento5, R.string.dir_apartamento5, R.string.desc_apartamento5)
        )
    }
}