package jk.labs.dev.betsson.group.technical.assesment.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jk.labs.dev.betsson.group.technical.assesment.BuildConfig
import jk.labs.dev.betsson.group.technical.assesment.data.local.FavoritePlaceLocalDataSource
import jk.labs.dev.betsson.group.technical.assesment.data.local.db.AppDatabase
import jk.labs.dev.betsson.group.technical.assesment.data.local.db.FavoritePlacesDao
import jk.labs.dev.betsson.group.technical.assesment.data.local.sources.FavoritePlaceLocalDataSourceImpl
import jk.labs.dev.betsson.group.technical.assesment.data.mappers.PlaceDetailResultMapper
import jk.labs.dev.betsson.group.technical.assesment.data.mappers.PlacePhotoResultMapper
import jk.labs.dev.betsson.group.technical.assesment.data.mappers.PlaceSearchResultMapper
import jk.labs.dev.betsson.group.technical.assesment.data.mappers.PlaceTipsResultMapper
import jk.labs.dev.betsson.group.technical.assesment.data.remote.SearchAndDataCloudDataSource
import jk.labs.dev.betsson.group.technical.assesment.data.remote.api.SearchAndDataService
import jk.labs.dev.betsson.group.technical.assesment.data.remote.sources.SearchAndDataCloudDataSourceImpl
import jk.labs.dev.betsson.group.technical.assesment.data.repositories.FavoritePlaceRepositoryImpl
import jk.labs.dev.betsson.group.technical.assesment.data.repositories.SearchAndDataRepositoryImpl
import jk.labs.dev.betsson.group.technical.assesment.domain.FilterPlacesUseCase
import jk.labs.dev.betsson.group.technical.assesment.domain.GetNearbyPlacesUseCase
import jk.labs.dev.betsson.group.technical.assesment.domain.GetPlaceDetailUseCase
import jk.labs.dev.betsson.group.technical.assesment.domain.SetPlaceAsFavoriteUseCase
import jk.labs.dev.betsson.group.technical.assesment.domain.repositories.FavoritePlaceRepository
import jk.labs.dev.betsson.group.technical.assesment.domain.repositories.SearchAndDataRepository
import jk.labs.dev.betsson.group.technical.assesment.domain.usecases.FilterPlacesUseCaseImpl
import jk.labs.dev.betsson.group.technical.assesment.domain.usecases.GetNearbyPlacesUseCaseImpl
import jk.labs.dev.betsson.group.technical.assesment.domain.usecases.GetPlaceDetailUseCaseImpl
import jk.labs.dev.betsson.group.technical.assesment.domain.usecases.SetPlaceAsFavoriteUseCaseImpl
import jk.labs.dev.betsson.group.technical.assesment.ui.mappers.PlaceDetailModelMapper
import jk.labs.dev.betsson.group.technical.assesment.ui.mappers.PlaceModelMapper
import jk.labs.dev.betsson.group.technical.assesment.ui.mappers.PlacePhotoModelMapper
import jk.labs.dev.betsson.group.technical.assesment.ui.mappers.PlaceTipModelMapper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        gson: Gson,
    ): Retrofit = Retrofit.Builder().baseUrl(BuildConfig.FSQ_BASE_URL).client(getOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create(gson)).build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun providesSetPlaceAsFavoriteUseCase(
        favoritePlaceRepository: FavoritePlaceRepository
    ): SetPlaceAsFavoriteUseCase = SetPlaceAsFavoriteUseCaseImpl(
        favoritePlaceRepository = favoritePlaceRepository
    )

    @Singleton
    @Provides
    fun providesGetNearbyPlacesUseCase(
        searchAndDataRepository: SearchAndDataRepository
    ): GetNearbyPlacesUseCase = GetNearbyPlacesUseCaseImpl(
        searchAndDataRepository = searchAndDataRepository
    )

    @Singleton
    @Provides
    fun provideGetPlaceDetailUseCase(
        searchAndDataRepository: SearchAndDataRepository
    ): GetPlaceDetailUseCase = GetPlaceDetailUseCaseImpl(
        searchAndDataRepository = searchAndDataRepository
    )

    @Singleton
    @Provides
    fun providesFilterPlacesUseCase(
        searchAndDataRepository: SearchAndDataRepository
    ): FilterPlacesUseCase = FilterPlacesUseCaseImpl(
        searchAndDataRepository = searchAndDataRepository
    )

    @Singleton
    @Provides
    fun providesFavoritePlacesRepository(
        favoritePlaceLocalDataSource: FavoritePlaceLocalDataSource
    ) = FavoritePlaceRepositoryImpl(
        favoritePlaceLocalDataSource = favoritePlaceLocalDataSource
    ) as FavoritePlaceRepository


    @Singleton
    @Provides
    fun providesSearchAndDataRepository(
        searchAndDataCloudDataSource: SearchAndDataCloudDataSource,
        favoritePlaceLocalDataSource: FavoritePlaceLocalDataSource,
        placeSearchResultMapper: PlaceSearchResultMapper,
        placeTipsResultMapper: PlaceTipsResultMapper,
        placePhotosResultMapper: PlacePhotoResultMapper,
        placeDetailResultMapper: PlaceDetailResultMapper
    ): SearchAndDataRepository = SearchAndDataRepositoryImpl(
        searchAndDataCloudDataSource = searchAndDataCloudDataSource,
        placeSearchResultMapper = placeSearchResultMapper,
        placeTipsResultMapper = placeTipsResultMapper,
        placePhotoResultMapper = placePhotosResultMapper,
        placeDetailResultMapper = placeDetailResultMapper,
        favoritePlaceLocalDataSource = favoritePlaceLocalDataSource
    )

    @Singleton
    @Provides
    fun provideFavoritePlacesLocalDataSource(
        favoritePlacesDao: FavoritePlacesDao
    ) = FavoritePlaceLocalDataSourceImpl(
        dao = favoritePlacesDao
    ) as FavoritePlaceLocalDataSource

    @Singleton
    @Provides
    fun provideSearchAndDataCloudDataSource(
        service: SearchAndDataService
    ) = SearchAndDataCloudDataSourceImpl(
        service = service
    ) as SearchAndDataCloudDataSource

    @Provides
    fun provideSearchAndDataService(
        retrofit: Retrofit
    ): SearchAndDataService = retrofit.create(SearchAndDataService::class.java)

    @Singleton
    @Provides
    fun providesPlaceSearchResultMapper() = PlaceSearchResultMapper()

    @Singleton
    @Provides
    fun providesPlaceModelMapper() = PlaceModelMapper()

    @Singleton
    @Provides
    fun providesPlacePhotosResultMapper() = PlacePhotoResultMapper()

    @Singleton
    @Provides
    fun providesPlaceTipResultMapper() = PlaceTipsResultMapper()

    @Singleton
    @Provides
    fun providesPlaceDetailResulMapper() = PlaceDetailResultMapper()

    @Singleton
    @Provides
    fun providesPlaceDetailModelMapper() = PlaceDetailModelMapper()

    @Singleton
    @Provides
    fun providesPlacePhotoModelMapper() = PlacePhotoModelMapper()

    @Singleton
    @Provides
    fun providesPlaceTipsModelMapper() = PlaceTipModelMapper()

    private fun getOkHttpClient(): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(AuthInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideFavoritePlacesDao(db: AppDatabase) = db.favoritePlacesDao()

}

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader(AUTHORIZATION, BuildConfig.FSQ_API_KEY)
        return chain.proceed(requestBuilder.build())
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }
}