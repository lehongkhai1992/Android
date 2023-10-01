package com.example.test_1intro.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.s16mvvmcleanachitecture.R
import com.example.test_1intro.api.ArtRetrofitAPI
import com.example.test_1intro.model.util.Util.BASE_URL
import com.example.test_1intro.room.ArtDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, ArtDataBase::class.java, "ArtBookDb"
    ).build()

    @Singleton
    @Provides
    fun injectDao(dataBase: ArtDataBase) = dataBase.artDao()

    @Singleton
    @Provides
    fun injectRetrofitApi():ArtRetrofitAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ArtRetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesGlide(@ApplicationContext context: Context) =
        Glide.with(context)
            .setDefaultRequestOptions(
                RequestOptions().placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
            )
}