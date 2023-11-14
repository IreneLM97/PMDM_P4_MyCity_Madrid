package com.example.pmdm_p4_mycity_madrid

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pmdm_p4_mycity_madrid.data.CategoriesDataSource
import com.example.pmdm_p4_mycity_madrid.ui.CategoriesListScreen
import com.example.pmdm_p4_mycity_madrid.ui.CityViewModel
import com.example.pmdm_p4_mycity_madrid.ui.PlacesListScreen

/**
 * Enumeración que define los posibles estados de pantalla de la aplicación.
 */
enum class MyCityScreen {
    CategoriesList,  // pantalla que muestra lista de categorías (página principal)
    PlacesList // pantalla que muestra lista de recomendaciones de una subcategoría
}

/**
 * Función que representa la estructura principal de la aplicación.
 *
 * @param viewModel ViewModel que gestiona el estado de la ciudad
 * @param navController controlador de navegación para gestionar la navegación entre pantallas
 * @param windowSize tamaño de la ventana que determina el diseño de la interfaz de usuario
 */
@Composable
fun MyCityApp(
    viewModel: CityViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    windowSize: WindowWidthSizeClass
) {
    // Configuración del sistema de navegación
    NavHost(
        navController = navController,
        startDestination = MyCityScreen.CategoriesList.name,
        modifier = Modifier.fillMaxSize()
    ) {
        // Estructura de la pantalla que muestra la lista de categorías (pantalla principal)
        composable(route = MyCityScreen.CategoriesList.name) {
            CategoriesListScreen(
                categories = CategoriesDataSource.getCategories(),  // obtenemos categorías del origen de datos
                onSubcategorySelected = {
                    viewModel.updateCurrentSubcategory(it)  // actualizamos subcategoría
                    navController.navigate(MyCityScreen.PlacesList.name) // navegamos a la pantalla de lista de lugares
                }
            )
        }

        // Estructura de pantalla que muestra la lista de lugares
        composable(route = MyCityScreen.PlacesList.name) {
            val context = LocalContext.current
            PlacesListScreen(
                viewModel = viewModel,
                windowSize = windowSize,
                onBackPressed = {
                    navController.popBackStack()  // retroceder en la pila de navegación
                },
                onSendButtonClicked = { summary: String ->
                    sharePlace(context, summary = summary)  // compartimos la información
                }
            )
        }
    }
}

/**
 * Función que permite compartir la información de un lugar a otra aplicación.
 *
 * @param context contexto de la aplicación
 * @param summary resumen del lugar que se quiere compartir
 */
private fun sharePlace(
    context: Context,
    summary: String
) {
    // Crear un Intent de acción SEND para compartir
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"  // contenido de texto plano
        putExtra(Intent.EXTRA_TEXT, summary)  // agregamos resumen
    }

    // Iniciar una actividad para elegir la aplicación de destino a la que se quiere compartir
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.send_place)
        )
    )
}