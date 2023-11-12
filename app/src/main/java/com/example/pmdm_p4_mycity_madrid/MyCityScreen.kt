package com.example.pmdm_p4_mycity_madrid

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pmdm_p4_mycity_madrid.data.CategoriesDataSource
import com.example.pmdm_p4_mycity_madrid.ui.CategoriesListScreen
import com.example.pmdm_p4_mycity_madrid.ui.CityViewModel
import com.example.pmdm_p4_mycity_madrid.ui.PlacesListScreen

//TODO REVISAR Y COMENTAR
/**
 * Representan las distintas páginas de la aplicación.
 */
enum class MyCityScreen {
    CategoriesList,  // página que muestra lista de categorías
    PlacesList // página que muestra recomendaciones de una categoría
}

@Composable
fun MyCityApp(
    viewModel: CityViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    windowSize: WindowWidthSizeClass
) {
    NavHost(
        navController = navController,
        startDestination = MyCityScreen.CategoriesList.name,
            modifier = Modifier.fillMaxSize()
    ) {
        composable(route = MyCityScreen.CategoriesList.name) {
            CategoriesListScreen(
                categories = CategoriesDataSource.getCategories(),
                onSubcategorySelected = {
                    viewModel.updateCurrentSubcategory(it)
                    navController.navigate(MyCityScreen.PlacesList.name)
                }
            )
        }

        composable(route = MyCityScreen.PlacesList.name) {
            PlacesListScreen(
                viewModel = viewModel,
                windowSize = windowSize,
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}