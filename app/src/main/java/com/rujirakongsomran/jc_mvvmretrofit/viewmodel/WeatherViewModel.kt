package com.rujirakongsomran.jc_mvvmretrofit.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rujirakongsomran.jc_mvvmretrofit.repository.WeatherRepository
import com.rujirakongsomran.jc_mvvmretrofit.util.Resource
import com.rujirakongsomran.jc_mvvmretrofit.model.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    var isLoading = mutableStateOf(false)
    private var _getWeatherData: MutableLiveData<Weather> = MutableLiveData<Weather>()
    var getWeatherData: LiveData<Weather> = _getWeatherData

    suspend fun getWeatherData(cityName: String, units: String, apiKey: String): Resource<Weather> {
        val result = weatherRepository.getWeatherResponse(cityName, units, apiKey)
        if (result is Resource.Success) {
            isLoading.value = true
            _getWeatherData.value = result.data!!
        }

        return result
    }
}