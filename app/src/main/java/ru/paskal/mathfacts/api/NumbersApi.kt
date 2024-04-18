package ru.paskal.mathfacts.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApi {
    @GET("{number}/trivia")
    fun getTrivia(@Path("number") number: Int): Call<String>

    @GET("{month}/{day}/date")
    fun getDateFact(@Path("month") month: Int, @Path("day") day: Int): Call<String>

    @GET("{number}/math")
    fun getMathFact(@Path("number") number: Int): Call<String>
}