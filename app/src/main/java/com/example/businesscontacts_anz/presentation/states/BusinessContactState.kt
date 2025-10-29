package com.example.businesscontacts_anz.presentation.states

import com.example.businesscontacts_anz.domain.model.BusinessContact

data class BusinessContactState(
    val listOfContacts: List<BusinessContact> = emptyList(),
    val currentSelectedContact: BusinessContact?=null,
    val isLoadingFromNetwork: Boolean = true
)
