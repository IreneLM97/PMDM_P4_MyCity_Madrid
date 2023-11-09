package com.example.pmdm_p4_mycity_madrid

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pmdm_p4_mycity_madrid.data.CategoriesDataSource
import com.example.pmdm_p4_mycity_madrid.ui.CategoriesListScreen
import com.example.pmdm_p4_mycity_madrid.ui.PlacesViewModel

/**
 * Representan las distintas páginas de la aplicación.
 */
enum class MyCityScreen {
    CategoriesList,  // página que muestra lista de categorías
    PlacesList // página que muestra recomendaciones de una categoría
}

/**
 * Función para mostrar el topBar de la aplicación y el botón de volver atrás si es posible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(R.string.city_break)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorResource(R.color.my_purple_light)
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

/**
 * Función para navegar por las páginas de la aplicación.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityApp(
    viewModel: PlacesViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            MyCityAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        //val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = MyCityScreen.CategoriesList.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MyCityScreen.CategoriesList.name) {
                CategoriesListScreen(
                    categories = CategoriesDataSource.getCategories(),
                    // CONTINUAR HACIENDO LLAMADA A CLICK DEL ITEM SUBCATEGORIA
                )
            }

        }
    }
}