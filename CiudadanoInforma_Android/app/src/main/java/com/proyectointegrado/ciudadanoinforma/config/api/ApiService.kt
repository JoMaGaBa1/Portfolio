package com.proyectointegrado.ciudadanoinforma.config.api

import com.proyectointegrado.ciudadanoinforma.data.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    //Distintas llamadas a la API

    @GET("user")
    fun getLoggedInUser(@Header("Authorization") authHeader: String): Call<LoggedInUser>

    @POST("auth/login")
    fun loginUser(@Body LoginRequest: LoginRequest): Call<LoginResponse>

    @DELETE("auth/logout")
    fun logoutUser(@Header("Authorization") authHeader: String): Call<LogoutResponse>

    @GET("incidencias")
    fun getListaIncidencias(@Header("Authorization") authHeader: String): Call<List<IncidenciasUser>>

    @GET("categorias")
    fun getListaCategoria(@Header("Authorization") authHeader: String): Call<List<MostrarSpinner>>

    @GET("equipos")
    fun getListaEquipos(@Header("Authorization") authHeader: String): Call<List<MostrarSpinner>>

    @GET("incidencias/{id}")
    fun getIncidencia(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int
    ): Call<DataClassParse>


    @POST("incidencias/{id}/comment")
    fun InsertarComentario(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int,
        @Body insertComent: InsertComent
    ): Call<DataClassParse>

    @GET("incidencias")
    fun getIncidenciaXCategoria(
        @Header("Authorization") authHeader: String,
        @Query("categoria") categoria: String
    ): Call<List<IncidenciasUser>>

    @Multipart
    @POST("incidencias/create")
    fun getIncidenciasCrear(
        @Header("Authorization") authHeader: String,
        @Part("titulo") titulo: RequestBody,
        @Part("ubicacion") ubicacion: RequestBody,
        @Part("comentario") comentario: RequestBody,
        @Part("categoria_id") categoria_id: RequestBody,
        @Part("equipo_id") equipo_id: RequestBody,
        @Part("estado_id") estado_id: RequestBody,
        @Part archivo: MultipartBody.Part
    ): Call<IncidenciasUser>

    @POST("usuarios/register")
    fun getUsuarioNuevo(
        @Body Registrarusuario: RegisterUser
    ): Call<UserData>
}