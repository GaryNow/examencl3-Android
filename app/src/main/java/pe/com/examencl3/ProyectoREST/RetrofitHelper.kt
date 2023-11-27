package pe.com.examencl3.ProyectoREST

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val baseURL = "https://api.mockfly.dev/mocks/0e36057a-d470-41f7-9200-07aa052cba58/";
    fun getRetroFitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}