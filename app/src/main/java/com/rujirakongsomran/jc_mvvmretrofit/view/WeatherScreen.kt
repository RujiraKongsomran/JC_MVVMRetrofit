package com.rujirakongsomran.jc_mvvmretrofit.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rujirakongsomran.jc_mvvmretrofit.ui.theme.DarkBlue
import com.rujirakongsomran.jc_mvvmretrofit.ui.theme.DarkLightGray
import com.rujirakongsomran.jc_mvvmretrofit.ui.theme.LightGray
import com.rujirakongsomran.jc_mvvmretrofit.model.Weather
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WeatherScreen(weather: Weather) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 64.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderImage()
        MainInfo(weather)
        InfoTable(weather)
    }
}

@Composable
fun HeaderImage() {
    Image(
        painter = painterResource(id = com.rujirakongsomran.jc_mvvmretrofit.R.drawable.ic_couple),
        contentDescription = null,
        modifier = Modifier.width(200.dp)
    )
}

@Composable
fun MainInfo(weather: Weather) {
    Column(
        modifier = Modifier.padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${weather.main.temp} °C",
            color = DarkBlue,
            fontSize = 48.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = weather.name,
            color = DarkBlue,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "${weather.weather[0].main}\n${weather.weather[0].description}",
            color = Color.Gray,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 24.dp)
        )
    }
}

@Composable
fun InfoTable(weather: Weather) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(
                DarkLightGray
            ),
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            InfoItem(
                iconRes = com.rujirakongsomran.jc_mvvmretrofit.R.drawable.humidity,
                title = "Humidity",
                subtitle = "${weather.main.humidity} %",
                modifier = Modifier.weight(
                    1f
                )
            )
            InfoItem(
                iconRes = com.rujirakongsomran.jc_mvvmretrofit.R.drawable.wind,
                title = "Wind",
                subtitle = "${weather.wind.speed}",
                modifier = Modifier.weight(
                    1f
                )
            )
        }
        Divider(
            color = LightGray,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Row(modifier = Modifier.padding(16.dp)) {
            InfoItem(
                iconRes = com.rujirakongsomran.jc_mvvmretrofit.R.drawable.sunrise,
                title = "Sunrise",
                subtitle = SimpleDateFormat(
                    "hh:mm a",
                    Locale.ENGLISH
                ).format(Date(weather.sys.sunrise * 1000)),
                modifier = Modifier.weight(
                    1f
                )
            )
            InfoItem(
                iconRes = com.rujirakongsomran.jc_mvvmretrofit.R.drawable.sunset,
                title = "Sunset",
                subtitle = SimpleDateFormat(
                    "hh:mm a",
                    Locale.ENGLISH
                ).format(Date(weather.sys.sunset * 1000)),
                modifier = Modifier.weight(
                    1f
                )
            )
        }
    }
}

@Composable
fun InfoItem(@DrawableRes iconRes: Int, title: String, subtitle: String, modifier: Modifier) {
    Row(modifier = modifier) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 8.dp)
                .width(40.dp)
                .height(30.dp)
        )
        Column {
            Text(
                text = title,
                color = Color.Gray
            )
            Text(
                text = subtitle,
                color = DarkBlue,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
