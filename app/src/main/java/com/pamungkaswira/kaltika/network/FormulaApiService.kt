package com.pamungkaswira.kaltika.network

import com.pamungkaswira.kaltika.ui.menu.Formula
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" + "pamungkaswira27/kaltika-kotlin/static-api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FormulaApiService {
    @GET("formula-api.json")
    suspend fun getFormula(): List<Formula>
}

object FormulaApi {
    val service: FormulaApiService by lazy {
        retrofit.create(FormulaApiService::class.java)
    }

    fun getFormulaUrl(name: String): String {
        return "$BASE_URL$name.png"
    }
}

enum class ApiStatus {
    LOADING, SUCCESS, FAILED
}