package com.rujirakongsomran.jc_mvvmretrofit.repository

import com.rujirakongsomran.jc_mvvmretrofit.network.ApiInterface
import com.rujirakongsomran.jc_mvvmretrofit.util.Resource
import com.rujirakongsomran.jc_mvvmretrofit.model.Weather
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class WeatherRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {
    suspend fun getWeatherResponse(cityName: String, units: String, apiKey: String): Resource<Weather> {
        val response = try {
            apiInterface.getWeather(cityName, units, apiKey)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }
}