package com.example.businesscontacts_anz.domain.repository

import com.example.businesscontacts_anz.domain.model.BusinessContact
import com.example.businesscontacts_anz.domain.utils.DataError
import com.example.businesscontacts_anz.domain.utils.Result

interface BusinessContactsRepository {
    suspend fun fetchBusinessContacts(): Result<List<BusinessContact>, DataError.Network>
}