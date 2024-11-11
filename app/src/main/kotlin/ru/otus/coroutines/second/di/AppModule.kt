package ru.otus.coroutines.second.di

import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.otus.coroutines.second.data.remote.api.CatFactApi
import ru.otus.coroutines.second.data.remote.api.CatImageApi
import ru.otus.coroutines.second.data.remote.data_sources.CatsRemoteDataSource
import ru.otus.coroutines.second.data.remote.data_sources.CatsRemoteDataSourceImpl
import ru.otus.coroutines.second.data.remote.interceptors.RetryInterceptor
import ru.otus.coroutines.second.data.repositories.CatsRepositoryImpl
import ru.otus.coroutines.second.domain.repositories.CatsRepository
import ru.otus.coroutines.second.presentation.screens.cats.CatsViewModel

private const val CAT_FACT_BASE_URL = "https://catfact.ninja/"
private const val CAT_IMAGE_BASE_URL = "https://api.thecatapi.com/v1/"

internal val appModule = module {
    viewModel<CatsViewModel> {
        CatsViewModel(catsRepository = get())
    }

    single<CatsRepository> {
        CatsRepositoryImpl(catsRemoteDataSource = get())
    }

    single<CatsRemoteDataSource> {
        CatsRemoteDataSourceImpl(
            catFactApi = get(),
            catImageApi = get(),
        )
    }

    single<CatFactApi> {
        (get(named(CAT_FACT_BASE_URL)) as Retrofit).create(CatFactApi::class.java)
    }
    single<Retrofit>(named(CAT_FACT_BASE_URL)) {
        Retrofit
            .Builder()
            .client(get())
            .baseUrl(CAT_FACT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<CatImageApi> {
        (get(named(CAT_IMAGE_BASE_URL)) as Retrofit).create(CatImageApi::class.java)
    }
    single<Retrofit>(named(CAT_IMAGE_BASE_URL)) {
        Retrofit
            .Builder()
            .client(get())
            .baseUrl(CAT_IMAGE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<OkHttpClient> {
        OkHttpClient
            .Builder()
            .addInterceptor(RetryInterceptor())
            .build()
    }
}
