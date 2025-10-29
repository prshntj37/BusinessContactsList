package com.example.businesscontacts_anz.di

import com.example.businesscontacts_anz.data.remote.implementations.BusinessContactRepoImpl
import com.example.businesscontacts_anz.domain.repository.BusinessContactsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(businessContactRepoImpl: BusinessContactRepoImpl): BusinessContactsRepository
}