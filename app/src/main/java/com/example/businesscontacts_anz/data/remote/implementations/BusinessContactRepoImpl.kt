package com.example.businesscontacts_anz.data.remote.implementations

import com.example.businesscontacts_anz.data.remote.api.FakeJsonApiInterface
import com.example.businesscontacts_anz.data.remote.mappers.toBusinessContactModel
import com.example.businesscontacts_anz.domain.model.BusinessContact
import com.example.businesscontacts_anz.domain.repository.BusinessContactsRepository
import com.example.businesscontacts_anz.domain.utils.DataError
import com.example.businesscontacts_anz.domain.utils.Result
import com.example.businesscontacts_anz.domain.utils.safeCall
import javax.inject.Inject

class BusinessContactRepoImpl @Inject constructor(
    val api: FakeJsonApiInterface
): BusinessContactsRepository {
    override suspend fun fetchBusinessContacts(): Result<List<BusinessContact>, DataError.Network> {
        val networkResponse = safeCall {
            api.fetchBusinessContacts()
        }
        return when (networkResponse){
            is Result.Error -> {
                Result.Error(networkResponse.error)
            }

            is Result.Success ->{
                if (networkResponse.data.isNullOrEmpty()){
                    Result.Error(DataError.Network.NO_DATA_IN_SERVER_ERROR)
                }else{
                    val listOfBusinessContactDomainModel = networkResponse.data.map {
                        it.toBusinessContactModel()
                    }
                    return Result.Success(listOfBusinessContactDomainModel)
                }

            }
        }
    }
}