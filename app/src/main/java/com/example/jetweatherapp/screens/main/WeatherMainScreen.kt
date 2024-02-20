package com.example.jetweatherapp.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.jetweatherapp.R
import com.example.jetweatherapp.data.DataOrException
import com.example.jetweatherapp.model.Weather
import com.example.jetweatherapp.model.WeatherItem
import com.example.jetweatherapp.utils.formatDate
import com.example.jetweatherapp.utils.formatDateTime
import com.example.jetweatherapp.utils.formatDecimals
import com.example.jetweatherapp.widgets.HumidityWindPressureRow
import com.example.jetweatherapp.widgets.SunsetSunRiseRow
import com.example.jetweatherapp.widgets.WeatherAppBar
import com.example.jetweatherapp.widgets.WeatherDetailsRow
import com.example.jetweatherapp.widgets.WeatherStateImage

@Composable
fun WeatherMainScreen(
    navController: NavController,
    mainViewModel: WeatherMainViewModel = hiltViewModel()
) {
    Surface {
        Column(
            modifier = Modifier.padding(1.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
                initialValue = DataOrException(loading = true)) {
                value = mainViewModel.getWeatherData(city = "Seattle")
            }.value

            if (weatherData.loading == true) {
                CircularProgressIndicator()
            } else if (weatherData.data != null) {
                MainScaffold(
                    weather = weatherData.data!!,
                    navController
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(
    weather: Weather,
    navController: NavController
) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "${weather.city.name}-${weather.city.country}",
                icon = Icons.Default.ArrowBack,
                navController = navController,
                elevation = 5.dp
            ) { //onButtonClicked Lambda
                Log.d("ALELOG", "MainScaffold: Nav Clicked")
            }
        }
    ) {
        MainContent(data = weather)
    }
}

@Composable
fun MainContent(data: Weather) {
    val weatherItem = data.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"

    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatDate(weatherItem.dt), //dia, mes data
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                WeatherStateImage(imageUrl = imageUrl)

                Text(
                    text = formatDecimals(weatherItem.temp.day) + "º",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = weatherItem.weather[0].main,
                    style = MaterialTheme.typography.h4,
                    fontStyle = FontStyle.Italic
                )
            }
        }

        HumidityWindPressureRow(weather = weatherItem)

        Divider()

        SunsetSunRiseRow(weather = weatherItem)

        Divider()

        Text(
            modifier = Modifier.padding(8.dp),
            text = "This week",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFEEF1EF),
            shape = RoundedCornerShape(size = 14.dp)
        ) {
            LazyColumn(
                modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)
            ) {
                items(items = data.list) { item ->
                    WeatherDetailsRow(weather = item)
//                    Text(text = item.temp.max.toString())
                }
            }
        }
    }
}
