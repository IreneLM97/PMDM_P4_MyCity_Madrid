package com.example.pmdm_p4_mycity_madrid.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.pmdm_p4_mycity_madrid.model.CityUiState
import com.example.pmdm_p4_mycity_madrid.model.Place
import com.example.pmdm_p4_mycity_madrid.model.Subcategory
import com.example.pmdm_p4_mycity_madrid.utils.PlacesContentType

/**
 * Función que representa la pantalla principal de la lista de lugares.
 *
 * @param viewModel ViewModel que gestiona el estado de la interfaz de usuario
 * @param windowSize clasificación del tamaño de la ventana
 * @param onBackPressed lambda que se invoca cuando se pulsa el botón de retroceso
 * @param onSendButtonClicked lambda que se invoca cuando se hace click en el botón de enviar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacesListScreen(
    viewModel: CityViewModel = viewModel(),
    windowSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit,
    onSendButtonClicked: (String) -> Unit = {}
){
    // Observamos el estado de la interfaz de usuario actualizando constantemente uiState
    val cityUiState by viewModel.uiState.collectAsState()

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
            // Barra superior personalizada
            PlacesListBar(
                isShowingListPage = cityUiState.isShowingListPage,
                onBackButtonClick = {
                    if (contentType == PlacesContentType.ListOnly && !cityUiState.isShowingListPage) {
                        viewModel.navigateToListPlacesPage()
                    } else {
                        onBackPressed()
                    }
                },
                windowSize = windowSize,
                currentSubcategory = cityUiState.currentSubcategory,
                currentPlace = cityUiState.currentPlace
            )
        }
    ) { innerPadding ->
        // Contenido principal de la pantalla en función del tamaño de la pantalla
        if (contentType == PlacesContentType.ListAndDetail) { // tamaño pantalla expanded
            // Mostramos lista y detalles
            PlacesListAndDetail(
                cityUiState = cityUiState,
                places = cityUiState.currentSubcategory.places,
                selectedPlace = cityUiState.currentPlace,
                onClick = {
                    viewModel.updateCurrentPlace(it)
                },
                onSendButtonClicked = onSendButtonClicked,
                contentPadding = innerPadding,
                contentType = contentType,
                modifier = Modifier.fillMaxWidth()
            )
        } else { // tamaño pantalla standard
            if (cityUiState.isShowingListPage) {
                // Mostramos lista de lugares
                PlacesList(
                    cityUiState = cityUiState,
                    places = cityUiState.currentSubcategory.places,
                    onClick = {
                        viewModel.updateCurrentPlace(it)
                        viewModel.navigateToDetailPlacePage()
                    },
                    contentPadding = innerPadding,
                    contentType = contentType,
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                )
            } else {
                // Mostramos detalles de un lugar específico
                PlaceDetail(
                    onSendButtonClicked = onSendButtonClicked,
                    selectedPlace = cityUiState.currentPlace,
                    contentPadding = innerPadding,
                )
            }
        }
    }
}

/**
 * Función que representa la barra superior de la pantalla.
 *
 * @param onBackButtonClick lambda que se invoca cuando se pulsa el botón de retroceso
 * @param isShowingListPage indica si se está mostrando la lista de lugares
 * @param windowSize clasificación del tamaño de la ventana
 * @param currentSubcategory subcategoría actual seleccionada
 * @param currentPlace lugar actual seleccionado
 * @param modifier modificador opcional para aplicar al diseño
 */
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
    // Variable que determina si se está mostrando la página de detalles del lugar
    val isShowingDetailPage = windowSize != WindowWidthSizeClass.Expanded && !isShowingListPage

    // Barra superior personalizada
    TopAppBar(
        // Título de la barra en función de que pantalla se esté mostrando
        title = {
            Text(
                text =
                    if (isShowingDetailPage) {  // se muestra solo la información del lugar
                        stringResource(id = currentPlace.nameResourceId)
                    } else {  // se muestra la lista y la información del lugar
                        stringResource(id = currentSubcategory.nameResourceId)
                    },
                fontWeight = FontWeight.Bold
            )
        },
        // Icono de navegación que corresponde a una flecha hacia atrás
        navigationIcon = {
            IconButton(
                onClick = onBackButtonClick
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button)
                )
            }
            },
        // Color personalizado para la barra superior
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorResource(R.color.my_dark_purple)
        ),
        modifier = modifier,
    )
}

/**
 * Función que representa la lista de lugares.
 *
 * @param cityUiState estado de la interfaz de usuario
 * @param places lista de lugares a mostrar
 * @param onClick lambda que se invoca cuando se hace click en un lugar
 * @param modifier modificador opcional para aplicar al diseño de la lista
 * @param contentPadding espaciado alrededor del contenido de la lista
 * @param contentType indica tipo de contenido de la pantalla (ListOnly o ListAndDetail)
 */
@Composable
private fun PlacesList(
    cityUiState: CityUiState,
    places: List<Place>,
    onClick: (Place) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    contentType: PlacesContentType = PlacesContentType.ListOnly,
) {
    // Muestra la lista de lugares
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier
            .padding(top = dimensionResource(R.dimen.padding_small))
            .padding(bottom = dimensionResource(R.dimen.padding_small)),
    ) {
        items(places, key = { place -> place.id }) { place ->
            // Representa un elemento de la lista
            PlaceItem(
                cityUiState = cityUiState,
                place = place,
                onItemClick = onClick,
                contentType = contentType
            )
        }
    }
}

/**
 * Función que representa un elemento individual en la lista de lugares.
 *
 * @param cityUiState estado de la interfaz de usuario
 * @param place lugar que se está representando
 * @param onItemClick lambda que se invoca cuando se hace click en un lugar
 * @param modifier modificador opcional para aplicar al diseño del elemento
 * @param contentType indica tipo de contenido de la pantalla (ListOnly o ListAndDetail)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlaceItem(
    cityUiState: CityUiState,
    place: Place,
    onItemClick: (Place) -> Unit,
    modifier: Modifier = Modifier,
    contentType: PlacesContentType = PlacesContentType.ListOnly
) {
    // Comprobamos si está seleccionado el item y estamos en vista ListAndDetail
    // para personalizar el fondo del item cuando esté seleccionado
    val isSelected = cityUiState.currentPlace.id == place.id && contentType == PlacesContentType.ListAndDetail

    // Diseño del item de la lista
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = {
            onItemClick(place)
        }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
                .background(
                    // Personalizamos color de fondo en función de si está seleccionado o no
                    color = if (isSelected) {
                        colorResource(id = R.color.my_normal_purple)
                    } else {
                        colorResource(id = R.color.my_light_purple)
                    }
                )
        ) {
            // Imagen del lugar
            PlaceItemImage(
                place = place,
                modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
            )

            // Información del lugar
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
                    .weight(1f)
            ) {
                Spacer(Modifier.height(5.dp))

                // Nombre del lugar
                Text(
                    text = stringResource(place.nameResourceId),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
                Spacer(Modifier.height(15.dp))

                // Dirección del lugar
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

/**
 * Función que representa la imagen de un lugar en un elemento de la lista de lugares.
 *
 * @param place lugar del cual se obtiene la imagen
 * @param modifier modificador opcional para aplicar al diseño de la imagen
 */
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

/**
 * Función que representa la pantalla de detalles de un lugar concreto.
 *
 * @param selectedPlace lugar seleccionado para mostrar detalles
 * @param onSendButtonClicked lambda que se invoca cuando se hace click en el botón de enviar
 * @param modifier modificador opcional para aplicar al diseño
 * @param contentPadding espaciado alrededor del contenido de la pantalla de detalles
 */
@Composable
private fun PlaceDetail(
    selectedPlace: Place,
    onSendButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues
) {
    // Estado del scroll
    val scrollState = rememberScrollState()
    
    // Resumen de la información del lugar
    val placeSummary = stringResource(
        R.string.place_summary,
        stringResource(id = selectedPlace.nameResourceId),
        stringResource(selectedPlace.dirResourceId),
        stringResource(selectedPlace.descResourceId)
    )

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
                fontSize = 30.sp,
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
            Spacer(Modifier.height(5.dp))

            // Botón para enviar a otra aplicación
            Row(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_big))
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        onSendButtonClicked(placeSummary)
                    },
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.my_darkest_purple)),
                ) {
                    Text(stringResource(R.string.send_button))
                }
            }
        }
    }
}

/**
 * Función que representa un componente compuesto por un icono y un texto.
 *
 * @param icon icono a representar
 * @param text texto a representar
 */
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

/**
 * Función que representa la pantalla de lista de lugares y detalles de un lugar.
 *
 * @param cityUiState estado actual de la interfaz de usuario
 * @param places lista de lugares a mostrar
 * @param selectedPlace lugar seleccionado para mostrar detalles
 * @param onClick lambda que se invoca cuando se hace click en un lugar de la lista
 * @param onSendButtonClicked lambda que se invoca cuando se hace click en el botón de enviar
 * @param modifier modificador opcional para aplicar al diseño
 * @param contentPadding espaciado alrededor del contenido de la pantalla
 * @param contentType indica tipo de contenido de la pantalla (ListOnly o ListAndDetail)
 */
@Composable
private fun PlacesListAndDetail(
    cityUiState: CityUiState,
    places: List<Place>,
    selectedPlace: Place,
    onClick: (Place) -> Unit,
    onSendButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    contentType: PlacesContentType = PlacesContentType.ListOnly
) {
    Row(
        modifier = modifier
    ) {
        // Representa la lista de lugares
        PlacesList(
            cityUiState = cityUiState,
            places = places,
            onClick = onClick,
            contentPadding = contentPadding,
            contentType = contentType,
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
        )

        // Representa los detalles de un lugar específico
        PlaceDetail(
            onSendButtonClicked = onSendButtonClicked,
            selectedPlace = selectedPlace,
            modifier = Modifier.weight(3f),
            contentPadding = contentPadding
        )
    }
}

/**
 * Función que previsualiza un elemento de la lista de lugares.
 */
@Preview
@Composable
fun PlaceItemPreview() {
    val viewModel: CityViewModel = viewModel()
    val cityUiState by viewModel.uiState.collectAsState()
    PlaceItem(
        cityUiState = cityUiState,
        place = PlacesDataSource.getCafeterias()[5],
        onItemClick = {}
    )
}

/**
 * Función que previsualiza la lista de lugares.
 */
@Preview
@Composable
fun PlacesListPreview() {
    val viewModel: CityViewModel = viewModel()
    val cityUiState by viewModel.uiState.collectAsState()
    PlacesList(
        cityUiState = cityUiState,
        places = PlacesDataSource.getCafeterias(),
        onClick = {},
    )
}

/**
 * Función que previsualiza la lista de lugares y detalles del lugar en una TABLET.
 */
@Preview(device = Devices.TABLET)
@Composable
fun PlacesListAndDetailsPreview() {
    val viewModel: CityViewModel = viewModel()
    val cityUiState by viewModel.uiState.collectAsState()
    PlacesListAndDetail(
        cityUiState = cityUiState,
        places = PlacesDataSource.getCafeterias(),
        selectedPlace = PlacesDataSource.getCafeterias()[0],
        onClick = {},
        onSendButtonClicked = {}
    )
}

/**
 * Función que previsualiza la pantalla en dispositivo móvil.
 */
@Preview
@Composable
fun PlacesListScreenPreviewMobile() {
    PlacesListScreen(
        windowSize = WindowWidthSizeClass.Medium,
        onBackPressed = {}
    )
}

/**
 * Función que previsualiza la pantalla en dispositivo tablet.
 */
@Preview(device = Devices.TABLET)
@Composable
fun PlacesListScreenPreviewTablet() {
    PlacesListScreen(
        windowSize = WindowWidthSizeClass.Expanded,
        onBackPressed = {}
    )
}