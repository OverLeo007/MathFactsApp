package ru.paskal.mathfacts.api

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


fun createRetrofit(): Retrofit {

    return Retrofit.Builder()
        .baseUrl("http://numbersapi.com/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

}