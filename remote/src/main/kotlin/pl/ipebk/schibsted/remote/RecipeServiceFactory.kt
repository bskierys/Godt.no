package pl.ipebk.schibsted.remote

import com.github.simonpercic.oklog3.OkLogInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Provide "make" methods to create instances of [RecipeService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
object RecipeServiceFactory {

  fun makeRecipeService(isDebug: Boolean): RecipeService {
    val okHttpClient = makeOkHttpClient(
      makeLoggingInterceptor(isDebug),
      OkLogInterceptor.builder().build())

    return makeService(okHttpClient, makeGson())
  }

  private fun makeService(okHttpClient: OkHttpClient, gson: Gson): RecipeService {
    val retrofit = Retrofit.Builder()
      .baseUrl("https://www.godt.no/api/")
      .client(okHttpClient)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build()
    return retrofit.create(RecipeService::class.java)
  }

  private fun makeOkHttpClient(vararg interceptors: Interceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
      .connectTimeout(120, TimeUnit.SECONDS)
      .readTimeout(120, TimeUnit.SECONDS)

    interceptors.forEach { builder.addInterceptor(it) }

    return builder.build()
  }

  private fun makeGson(): Gson {
    return GsonBuilder()
      .setLenient()
      .setDateFormat("yyyy/MM/dd HH:mm:ss")
      .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
      .create()
  }

  private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (isDebug)
      HttpLoggingInterceptor.Level.BASIC
    else
      HttpLoggingInterceptor.Level.NONE
    return logging
  }
}