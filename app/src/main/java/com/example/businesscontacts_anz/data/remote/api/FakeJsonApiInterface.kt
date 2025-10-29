package com.example.businesscontacts_anz.data.remote.api

import com.example.businesscontacts_anz.data.remote.dto.BusinessContactDTO
import retrofit2.Response
import retrofit2.http.GET


interface FakeJsonApiInterface {

    @GET("users/")
    suspend fun fetchBusinessContacts(): Response<List<BusinessContactDTO>>
}