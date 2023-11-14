package com.example.pmdm_p4_mycity_madrid.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pmdm_p4_mycity_madrid.R
import com.example.pmdm_p4_mycity_madrid.data.CategoriesDataSource
import com.example.pmdm_p4_mycity_madrid.model.Category
import com.example.pmdm_p4_mycity_madrid.model.Subcategory

/**
 * Función que representa la pantalla que muestra la lista de categorías de la aplicación.
 *
 * @param categories lista de categorías que se mostrarán en la pantalla
 * @param onSubcategorySelected lambda que se invoca cuando se selecciona una subcategoría
 * @param modifier modificador opcional para aplicar al diseño de la pantalla
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesListScreen(
    categories: List<Category>,
    onSubcategorySelected: (Subcategory) -> Unit,
    modifier: Modifier = Modifier
){
    // Diseño de la estructura básica de la pantalla
    Scaffold(
        topBar = {
            // Barra superior personalizada
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.city_break))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = colorResource(R.color.my_dark_purple)
                )
            )
        }
    ) { innerPadding ->
        // Diseño de la lista de categorías
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = dimensionResource(R.dimen.padding_medium)),
            contentPadding = innerPadding
        ) {
            // Creamos una cabecera con la silueta de la ciudad
            item {
                CityHeader()
            }
            // Creamos las categorías
            items(categories) { category ->
                CategoryItem(
                    category = category,
                    onSubcategorySelected = onSubcategorySelected
                )
            }
        }
    }
}

/**
 * Función que representa la cabecera de la ciudad en la pantalla de lista de categorías.
 */
@Composable
private fun CityHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

        // Texto que representa el nombre de la ciudad
        Text(
            text = stringResource(R.string.madrid),
            color = colorResource(id = R.color.my_dark_gray),
            fontSize = 40.sp,
            style = MaterialTheme.typography.headlineSmall
        )

        // Línea decorativa para separar el nombre de la ciudad del subtítulo
        Line()

        // Texto que representa un subtítulo
        Text(
            text = stringResource(R.string.skyline),
            color = colorResource(id = R.color.my_dark_gray),
            fontSize = 20.sp,
            style = MaterialTheme.typography.headlineSmall
        )

        // Imagen que muestra la silueta de la ciudad
        Image(
            painter = painterResource(R.drawable.silueta),
            contentDescription = stringResource(R.string.silueta),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
    }
}

/**
 * Dibuja una línea horizontal.
 */
@Composable
private fun Line() {
    Box(
        modifier = Modifier
            .width(250.dp)
            .height(1.dp)
            .background(colorResource(R.color.my_dark_gray))
    )
}

/**
 * Función que representa una categoría de la lista.
 *
 * @param category objeto que representa una categoría
 * @param onSubcategorySelected lambda que se invoca cuando se selecciona una subcategoría
 */
@Composable
private fun CategoryItem(
    category: Category,
    onSubcategorySelected: (Subcategory) -> Unit
) {
    // Variable que controla el estado de expansión/cierre de la categoría
    var expanded by remember { mutableStateOf(false) }

    // Tarjeta que contiene la información de la categoría y las subcategorías
    Card(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_small))
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .background(colorResource(id = R.color.my_normal_purple))
        ) {
            // Información principal de la categoría y el botón de expansión/contracción
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                // Muestra información básica de la categoría
                CategoryInfo(
                    category.imageResourceId,
                    category.nameResourceId
                )
                Spacer(Modifier.weight(1f))

                // Muestra botón de expansión/contracción
                CategoryItemButton(
                    expanded = expanded,
                    onClick = {
                        expanded = !expanded
                    },
                )
            }
            // Si la categoría está expandida, se muestra la lista de subcategorías
            if (expanded) {
                SubcategoriesList(
                    category.subcategories,
                    onSubcategorySelected = onSubcategorySelected
                )
            }
        }
    }
}

/**
 * Función que representa la información visual de una categoría, incluyendo su icono y nombre.
 *
 * @param categoryIcon recurso de imagen del icono de la categoría
 * @param categoryName recurso de del nombre de la categoría
 * @param modifier modificador opcional para aplicar al diseño
 */
@Composable
private fun CategoryInfo(
    @DrawableRes categoryIcon: Int,
    @StringRes categoryName: Int,
    modifier: Modifier = Modifier
) {
    // Muestra el icono de la categoría
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_category_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(categoryIcon),
        contentDescription = stringResource(R.string.category_icon)
    )

    // Muestra el nombre de la categoría
    Text(
        text = stringResource(categoryName),
        style = MaterialTheme.typography.titleSmall,
        color = colorResource(id = R.color.my_dark_gray),
        fontSize = 30.sp,
        modifier = Modifier
            .padding(top = dimensionResource(R.dimen.padding_medium))
    )
}

/**
 * Función que representa el botón de expansión/contracción.
 *
 * @param expanded indica si la categoría está actualmente expandida o no
 * @param onClick lambda que se invoca cuando se hace click en el botón
 */
@Composable
private fun CategoryItemButton(
    expanded: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.padding_medium))
    ) {
        // Muestra icono de flecha
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

/**
 * Función que representa una lista de subcategorías.
 *
 * @param subcategories lista de subcategorías a mostrar
 * @param onSubcategorySelected lambda que se invoca cuando se selecciona una subcategoría
 */
@Composable
private fun SubcategoriesList(
    subcategories: List<Subcategory>,
    onSubcategorySelected: (Subcategory) -> Unit
) {
    Column (
        modifier = Modifier
            .background(colorResource(R.color.my_light_purple))
            .padding(top = dimensionResource(R.dimen.padding_small))
            .padding(bottom = dimensionResource(R.dimen.padding_small))
    ) {
        // Representación de cada subcategoría
        subcategories.forEach { subcategory ->
            Row (
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
                    .offset(x = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Muestra el icono de la subcategoría
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_subcategory_size))
                        .clip(MaterialTheme.shapes.small)
                        .padding(5.dp),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(subcategory.imageResourceId),
                    contentDescription = stringResource(R.string.category_icon)
                )

                // Muestra el nombre de la subcategoría
                Text(
                    text = stringResource(subcategory.nameResourceId),
                    color = colorResource(id = R.color.my_dark_gray),
                    fontSize = 25.sp,
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_small))
                        .clickable {
                            onSubcategorySelected(subcategory)
                        }
                )
            }
            Spacer(Modifier.height(5.dp))
        }
    }
}

/**
 * Función para previsualizar la pantalla de lista de categorías.
 */
@Preview
@Composable
fun CategoriesListScreenPreview(){
    CategoriesListScreen(
        categories = CategoriesDataSource.getCategories(),
        onSubcategorySelected = {},
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    )
}

/**
 * Función para previsualizar subcategorías de 'Hostelería'.
 */
@Preview
@Composable
fun SubcategoriesListScreenPreview1(){
    SubcategoriesList(
        subcategories = CategoriesDataSource.getCategories()[0].subcategories,
        onSubcategorySelected = {}
    )
}

/**
 * Función para previsualizar subcategorías de 'Ocio'.
 */
@Preview
@Composable
fun SubcategoriesListScreenPreview2(){
    SubcategoriesList(
        subcategories = CategoriesDataSource.getCategories()[1].subcategories,
        onSubcategorySelected = {}
    )
}

/**
 * Función para previsualizar subcategorías de 'Cultura'.
 */
@Preview
@Composable
fun SubcategoriesListScreenPreview3(){
    SubcategoriesList(
        subcategories = CategoriesDataSource.getCategories()[2].subcategories,
        onSubcategorySelected = {}
    )
}

/**
 * Función para previsualizar subcategorías de 'Alojamientos'.
 */
@Preview
@Composable
fun SubcategoriesListScreenPreview4(){
    SubcategoriesList(
        subcategories = CategoriesDataSource.getCategories()[3].subcategories,
        onSubcategorySelected = {}
    )
}
