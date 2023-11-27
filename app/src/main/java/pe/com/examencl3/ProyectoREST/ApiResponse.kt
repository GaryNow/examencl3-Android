package pe.com.examencl3.ProyectoREST

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("categories") val categories: List<VideoCategory>
)

