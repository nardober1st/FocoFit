package com.fitfoco.focofit.di

import com.fitfoco.focofit.datasource.DataSourceAuth
import com.fitfoco.focofit.repository.RepositoryAuth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun providesDataSource(
        firestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth
    ): DataSourceAuth {
        return DataSourceAuth(firebaseAuth, firestore)
    }

    @Provides
    @Singleton
    fun providesAuthRepositoryImpl(dataSourceAuth: DataSourceAuth): RepositoryAuth {
        return RepositoryAuth(dataSourceAuth)
    }
}