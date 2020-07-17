package lamb.rebecca.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Api {

    companion object {
        fun retrofit(baseUrl: String): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            return Retrofit.Builder()
                .client(client)
                //http://slowwly.robertomurray.co.uk/delay/5000/url/
                .baseUrl("https://adfasdasds") //TODO: Move me
                .addConverterFactory(
                    MoshiConverterFactory.create(
                        Moshi.Builder()
                            .add(MealEntityParser())
                            .add(KotlinJsonAdapterFactory())
                            .build()
                    )
                )
                .build()
        }

    }

}