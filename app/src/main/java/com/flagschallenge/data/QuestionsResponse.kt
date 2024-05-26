package com.flagschallenge.data

import com.squareup.moshi.Json

data class Question(
    @Json(name = "answer_id") val answerId: Int,
    @Json(name = "countries") val countries: List<Country>,
    @Json(name = "country_code") val countryCode: String
)

data class Country(
    @Json(name = "country_name") val countryName: String,
    @Json(name = "id") val id: Int
)

data class QuestionsResponse(
    @Json(name = "questions") val questions: List<Question>
)
