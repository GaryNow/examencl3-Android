package pe.com.examencl3.ProyectoREST

import retrofit2.Response
import retrofit2.http.GET

interface VideoAPI {
    @GET("apiexamen")
    suspend fun getBooks() : Response<ApiResponse>

}