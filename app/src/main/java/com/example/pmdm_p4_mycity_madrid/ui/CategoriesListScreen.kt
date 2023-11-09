package com.example.pmdm_p4_mycity_madrid.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
 * Define la estructura que tendrá la pantalla que lista las categorías.
 */
@Composable
fun CategoriesListScreen(
    categories: List<Category>,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Creamos la cabecera de la página
        Header()

        // Creamos las listas desplegables
        CategoriesList(
            categories
        )
    }
}

/**
 * Define la estructura que tendrá el inicio de la página con la silueta y el nombre de la ciudad.
 */
@Composable
private fun Header() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
        Text(
            text = stringResource(R.string.madrid),
            fontSize = 50.sp,
            style = MaterialTheme.typography.headlineSmall
        )
        Line()
        Text(
            text = stringResource(R.string.skyline),
            fontSize = 20.sp,
            style = MaterialTheme.typography.headlineSmall
        )
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
 * Define la estructura que tendrá la lista de desplegables de categorías principales.
 */
@Composable
private fun CategoriesList(
    categories: List<Category>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_medium)),
    ) {
        items(categories) { category ->
            CategoryItem(
                category = category
            )
        }
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
            .background(colorResource(R.color.black))
    )
}

/**
 * Define la estructura que tendrá cada categoría en la lista.
 */
@Composable
private fun CategoryItem(
    category: Category
) {
    var expanded by remember { mutableStateOf(false) }

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
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                CategoryInfo(
                    category.imageResourceId,
                    category.nameResourceId
                )
                Spacer(Modifier.weight(1f))
                CategoryItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                )
            }
            if (expanded) {
                SubcategoriesList(category.subcategories)
            }
        }
    }
}

/**
 * Define la estructura que tendrá la información de la categoría.
 */
@Composable
private fun CategoryInfo(
    @DrawableRes categoryIcon: Int,
    @StringRes categoryName: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_category_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(categoryIcon),
        contentDescription = stringResource(R.string.category_icon)
    )
    Text(
        text = stringResource(categoryName),
        style = MaterialTheme.typography.displayMedium,
        fontSize = 30.sp,
        modifier = Modifier
            .padding(top = dimensionResource(R.dimen.padding_medium))
    )
}

/**
 * Define el icono de expandir de la categoría.
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
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

/**
 * Define la estructura que tendrán las subcategorías de cada categoría principal.
 * Se llama a esta función al expandir una categoría.
 */
@Composable
private fun SubcategoriesList(
    subcategories: List<Subcategory>
) {
    Column (
        modifier = Modifier
            .background(colorResource(id = R.color.black))
    ) {
        subcategories.forEach { subcategory ->
            Text(
                text = stringResource(subcategory.nameResourceId),
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

/**
 * Función para previsualizar la pantalla.
 */
@Preview
@Composable
fun ListCategoryScreenPreview(){
    CategoriesListScreen(
        categories = CategoriesDataSource.getCategories(),
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    )
}
