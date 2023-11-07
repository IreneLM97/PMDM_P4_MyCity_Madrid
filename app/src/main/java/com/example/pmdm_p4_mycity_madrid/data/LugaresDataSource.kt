package com.example.pmdm_p4_mycity_madrid.data

import com.example.pmdm_p4_mycity_madrid.R
import com.example.pmdm_p4_mycity_madrid.model.Lugar

object LugaresDataSource {
    fun leerCafeterias(): List<Lugar> {
        return listOf<Lugar> (
            Lugar(1, R.drawable.cafe1, R.string.nom_cafe1, R.string.dir_cafe1, R.string.desc_cafe1),
            Lugar(2, R.drawable.cafe2, R.string.nom_cafe2, R.string.dir_cafe2, R.string.desc_cafe2),
            Lugar(3, R.drawable.cafe3, R.string.nom_cafe3, R.string.dir_cafe3, R.string.desc_cafe3),
            Lugar(4, R.drawable.cafe4, R.string.nom_cafe4, R.string.dir_cafe4, R.string.desc_cafe4),
            Lugar(5, R.drawable.cafe5, R.string.nom_cafe5, R.string.dir_cafe5, R.string.desc_cafe5)
        )
    }

    fun leerRestaurantes(): List<Lugar> {
        return listOf<Lugar> (
            Lugar(1, R.drawable.rest1, R.string.nom_restaurante1, R.string.dir_restaurante1, R.string.desc_restaurante1),
            Lugar(2, R.drawable.rest2, R.string.nom_restaurante2, R.string.dir_restaurante2, R.string.desc_restaurante2),
            Lugar(3, R.drawable.rest3, R.string.nom_restaurante3, R.string.dir_restaurante3, R.string.desc_restaurante3),
            Lugar(4, R.drawable.rest4, R.string.nom_restaurante4, R.string.dir_restaurante4, R.string.desc_restaurante4),
            Lugar(5, R.drawable.rest5, R.string.nom_restaurante5, R.string.dir_restaurante5, R.string.desc_restaurante5)
        )
    }
}