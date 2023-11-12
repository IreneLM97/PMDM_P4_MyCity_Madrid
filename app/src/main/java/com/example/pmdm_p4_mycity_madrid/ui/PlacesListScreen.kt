package com.example.pmdm_p4_mycity_madrid.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pmdm_p4_mycity_madrid.R
import com.example.pmdm_p4_mycity_madrid.data.PlacesDataSource
import com.example.pmdm_p4_mycity_madrid.model.Place
import com.example.pmdm_p4_mycity_madrid.model.Subcategory
import com.example.pmdm_p4_mycity_madrid.utils.PlacesContentType

// TODO COMENTAR Y REVISAR
// TODO AÑADIR OPCIÓN DE COMPARTIR LUGAR A OTRA APP
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacesListScreen(
    windowSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit,
){
    // Creamos una instancia del ViewModel para manejar datos relacionados con la interfaz de usuario
    val viewModel: CityViewModel = viewModel()

    // Observamos el estado de la interfaz de usuario actualizando constantemente uiState
    val uiState by viewModel.uiState.collectAsState()

    // Determinamos el tipo de contenido en función del tamaño de la ventana
    val contentType = when (windowSize) {
        // Para ventanas compactas o de tamaño medio, mostrar solo la lista de lug-ares
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> PlacesContentType.ListOnly

        // Para ventanas expandidas, mostrar tanto la lista como los detalles de los lugares
        WindowWidthSizeClass.Expanded -> PlacesContentType.ListAndDetail
        else -> PlacesContentType.ListOnly
    }

    Scaffold(
        topBar = {
            PlacesListBar(
                isShowingListPage = uiState.isShowingListPage,
                onBackButtonClick = { viewModel.navigateToListPlacesPage() },
                windowSize = windowSize,
                currentSubcategory = uiState.currentSubcategory,
                currentPlace = uiState.currentPlace
            )
        }
    ) { innerPadding ->
        if (contentType == PlacesContentType.ListAndDetail) {
            PlacesListAndDetail(
                places = uiState.placesList,
                selectedPlace = uiState.currentPlace,
                onClick = {
                    viewModel.updateCurrentPlace(it)
                },
                onBackPressed = onBackPressed,
                contentPadding = innerPadding,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            if (uiState.isShowingListPage) {
                PlacesList(
                    places = uiState.placesList,
                    onClick = {
                        viewModel.updateCurrentPlace(it)
                        viewModel.navigateToDetailPlacePage()
                    },
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                    contentPadding = innerPadding,
                )
            } else {
                PlaceDetail(
                    selectedPlace = uiState.currentPlace,
                    contentPadding = innerPadding,
                    onBackPressed = {
                        viewModel.navigateToListPlacesPage()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlacesListBar(
    onBackButtonClick: () -> Unit,
    isShowingListPage: Boolean,
    windowSize: WindowWidthSizeClass,
    currentSubcategory: Subcategory,
    currentPlace: Place,
    modifier: Modifier = Modifier
) {
    val isShowingDetailPage = windowSize != WindowWidthSizeClass.Expanded && !isShowingListPage
    TopAppBar(
        title = {
            Text(
                text =
                    if (isShowingDetailPage) {  // se muestra la lista y los detalles
                        stringResource(id = currentPlace.nameResourceId)
                    } else {  // sólo se muestra la lista
                        stringResource(id = currentSubcategory.nameResourceId)
                    },
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button)
                )
            }
            },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorResource(R.color.my_normal_purple)
        ),
        modifier = modifier,
    )
}

@Composable
private fun PlacesList(
    places: List<Place>,
    onClick: (Place) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_medium)),
    ) {
        items(places, key = { place -> place.id }) { place ->
            PlaceItem(
                place = place,
                onItemClick = onClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlaceItem(
    place: Place,
    onItemClick: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(place) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            PlaceItemImage(
                place = place,
                modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
            )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
                    .weight(1f)
            ) {
                Spacer(Modifier.height(5.dp))
                Text(
                    text = stringResource(place.nameResourceId),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    text = stringResource(place.dirResourceId),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
            }
        }
    }
}

@Composable
private fun PlaceItemImage(
    place: Place,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(place.imageResourceId),
            contentDescription = stringResource(id = R.string.place_image),
            alignment = Alignment.Center,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun PlaceDetail(
    selectedPlace: Place,
    onBackPressed: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    // Maneja la acción de pulsar el botón de volver atrás
    BackHandler {
        onBackPressed()
    }

    // Estado del scroll
    val scrollState = rememberScrollState()

    // Estructura de la información detallada del lugar
    Box(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(contentPadding)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            // Imagen del lugar
            Image(
                painter = painterResource(selectedPlace.imageResourceId),
                contentDescription = stringResource(R.string.place_image),
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(50.dp))
            )

            // Nombre del lugar
            Text(
                text = stringResource(selectedPlace.nameResourceId),
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 40.sp,
                color = colorResource(id = R.color.my_dark_gray),
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(R.dimen.padding_small))
                    .fillMaxWidth()
            )

            Spacer(Modifier.height(10.dp))

            // Dirección del lugar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 10.dp)
                    .padding(end = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconWithText(
                    icon = painterResource(R.drawable.icon_ubicacion),
                    text = stringResource(selectedPlace.dirResourceId)
                )
            }

            Spacer(Modifier.height(20.dp))

            // Descripción del lugar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 10.dp)
                    .padding(end = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconWithText(
                    icon = painterResource(R.drawable.icon_descripcion),
                    text = stringResource(selectedPlace.descResourceId)
                )
            }
        }
    }
}

@Composable
private fun IconWithText(
    icon: Painter,
    text: String
) {
    // Estructura del icono
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.my_dark_gray),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(3.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            alignment = Alignment.Center,
            modifier = Modifier.size(24.dp)
        )
    }

    Spacer(modifier = Modifier.width(20.dp))

    // Estructura del texto
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .defaultMinSize(minHeight = 40.dp, minWidth = 600.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.my_dark_gray),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = dimensionResource(R.dimen.padding_small))
            .padding(top = 5.dp, bottom = 5.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.my_dark_gray),
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.padding_small))
        )
    }
}

@Composable
private fun PlacesListAndDetail(
    places: List<Place>,
    selectedPlace: Place,
    onClick: (Place) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Row(
        modifier = modifier
    ) {
        PlacesList(
            places = places,
            onClick = onClick,
            contentPadding = contentPadding,
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
        )
        PlaceDetail(
            selectedPlace = selectedPlace,
            modifier = Modifier.weight(3f),
            contentPadding = contentPadding,
            onBackPressed = onBackPressed,
        )
    }
}

@Preview
@Composable
fun PlaceItemPreview() {
    PlaceItem(
        place = PlacesDataSource.getCafeterias()[5],
        onItemClick = {}
    )
}

@Preview
@Composable
fun PlacesListPreview() {
    PlacesList(
        places = PlacesDataSource.getCafeterias(),
        onClick = {}
    )
}

@Preview(device = Devices.TABLET)
@Composable
fun PlacesListAndDetailsPreview() {
    PlacesListAndDetail(
        places = PlacesDataSource.getCafeterias(),
        selectedPlace = PlacesDataSource.getCafeterias()[0],
        onClick = {},
        onBackPressed = {}
    )
}

@Preview
@Composable
fun PlacesListScreenPreviewMobile() {
    PlacesListScreen(
        windowSize = WindowWidthSizeClass.Medium,
        onBackPressed = {}
    )
}

@Preview(device = Devices.TABLET)
@Composable
fun PlacesListScreenPreviewTablet() {
    PlacesListScreen(
        windowSize = WindowWidthSizeClass.Expanded,
        onBackPressed = {}
    )
}