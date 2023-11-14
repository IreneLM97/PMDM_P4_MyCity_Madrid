package com.example.pmdm_p4_mycity_madrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.example.pmdm_p4_mycity_madrid.ui.theme.PMDM_P4_MyCity_MadridTheme

/**
 * Clase `MainActivity` que representa la actividad principal de la aplicación.
 */
class MainActivity : ComponentActivity() {
    /**
     * Método `onCreate` que se ejecuta al crear la actividad.
     *
     * @param savedInstanceState objeto Bundle que contiene el estado de la actividad
     */
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PMDM_P4_MyCity_MadridTheme {
                // Contenedor de la aplicación
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Calculamos el tamaño de la ventana
                    val windowSize = calculateWindowSizeClass(this)

                    // Iniciamos la aplicación de MyCity
                    MyCityApp(
                        windowSize = windowSize.widthSizeClass
                    )
                }
            }
        }
    }
}