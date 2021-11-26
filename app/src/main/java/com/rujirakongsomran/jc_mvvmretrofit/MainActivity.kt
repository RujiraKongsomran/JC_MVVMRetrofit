package com.rujirakongsomran.jc_mvvmretrofit

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rujirakongsomran.jc_mvvmretrofit.ui.theme.JC_MVVMRetrofitTheme
import com.rujirakongsomran.jc_mvvmretrofit.ui.theme.Purple500
import com.rujirakongsomran.jc_mvvmretrofit.util.Resource
import com.rujirakongsomran.jc_mvvmretrofit.view.WeatherScreen
import com.rujirakongsomran.jc_mvvmretrofit.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC_MVVMRetrofitTheme {
                CallApi()
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun CallApi(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    //val scaffoldState = rememberScaffoldState()
    val getWeatherData = viewModel.getWeatherData.observeAsState()

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {

            scope.launch {
                val result = viewModel.getWeatherData(
                    "Bangkok",
                    "metric",
                    "2836efc2a9c6e43e9f89fa9510aa41dd"
                )
                if (result is Resource.Success) {
                    Toast.makeText(context, "Fetching data success!", Toast.LENGTH_SHORT).show()
                } else if (result is Resource.Error) {
                    Toast.makeText(context, "Error: ${result.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            if (!viewModel.isLoading.value) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
            if (viewModel.isLoading.value) {
                if (viewModel.getWeatherData.value!!.name.isNotEmpty())
                    WeatherScreen(getWeatherData.value!!)
            }
        }
    }

}