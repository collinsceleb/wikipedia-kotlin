package com.bdn.collinsceleb.myapplication.providers

import android.util.Log
import com.bdn.collinsceleb.myapplication.models.Urls
import com.bdn.collinsceleb.myapplication.models.WikiResult
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import java.io.Reader
import java.lang.reflect.InvocationTargetException
import kotlin.Exception

class ArticleDataProvider {

    init {
        FuelManager.instance.baseHeaders = mapOf("User-agent" to "Collinsceleb Wikipedia")
    }

    fun getSearch(
        term: String,
        skip: Int,
        take: Int,
        responseHandler: (result: WikiResult) -> Unit?
    ) {
        Urls.getSearchUrl(term, skip, take).httpGet()
            .responseObject(WikipediaDataDeserializer()) { _, _, result ->
//
//                if (response.statusCode != 200) {
//                    throw Exception("Unable to get articles")
//                }
//                val (data, _) = result
//                responseHandler.invoke(data as WikiResult)
                when(result) {
                    is Result.Failure -> {
                        Log.i("ErrorMsg", result.getException().message)
                        result.getException().stackTrace
                        throw Exception(result.getException())
                    }
                    is Result.Success -> {
                        val(data, _) = result
                        responseHandler.invoke(data as WikiResult)
                    }
                }
            }
    }

    fun getRandom(take: Int, responseHandler: (result: WikiResult) -> Unit?) {
        Urls.getRandomUrl(take).httpGet()
            .responseObject(WikipediaDataDeserializer()) { _, _, result ->

//                if (response.statusCode != 200) {
//                    throw Exception("Unable to get articles")
//                }
//                try {
//                    val (data, _) = result
////                    responseHandler.invoke(data as WikiResult)
//                    if (data != null) {
//                        responseHandler.invoke(data)
//                    }
//                }
//               catch (exception : InvocationTargetException) {
//                   exception.cause
//               }
                when(result) {
                    is Result.Failure -> {
                        Log.i("ErrorMsg", result.getException().message)
                        result.getException().stackTrace
                        throw Exception(result.getException())
                    }
                    is Result.Success -> {
                        val(data, _) = result
                        responseHandler.invoke(data as WikiResult)
                    }
                }
            }
    }

    class WikipediaDataDeserializer : ResponseDeserializable<WikiResult> {
        override fun deserialize(reader: Reader): WikiResult? =
            Gson().fromJson(reader, WikiResult::class.java)
    }
}