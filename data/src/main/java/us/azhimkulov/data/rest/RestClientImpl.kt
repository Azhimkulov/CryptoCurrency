package us.azhimkulov.data.rest

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import us.azhimkulov.data.rest.api.CryptoApi
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by azamat  on 2/21/21.
 */
class RestClientImpl @Inject constructor(baseUrl: String) : RestClient {

    private var retrofit: Retrofit

    companion object {
        private const val DEFAULT_TIMEOUT_IN_SECONDS: Long = 60
    }

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        this.retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    override fun getCryptoApi(): CryptoApi {
        return retrofit.create(CryptoApi::class.java)
    }
}