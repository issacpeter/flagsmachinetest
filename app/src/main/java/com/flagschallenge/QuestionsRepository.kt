package com.flagschallenge

import android.content.Context
import com.flagschallenge.data.Question
import com.flagschallenge.data.QuestionsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionsRepository @Inject constructor(
    private val context: Context
) {

    fun getQuestions(): List<Question> {
        val jsonString = Utils.readJsonFromAssets(context, "questions.json")
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(QuestionsResponse::class.java)

        return jsonAdapter.fromJson(jsonString)?.questions?: emptyList()
    }
}