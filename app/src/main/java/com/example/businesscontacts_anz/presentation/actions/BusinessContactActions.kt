package com.example.businesscontacts_anz.presentation.actions

import com.example.businesscontacts_anz.domain.model.BusinessContact

sealed interface BusinessContactActions {

    data object FetchContacts: BusinessContactActions

    data class TapOnABusinessContactToViewDetails(val contact: BusinessContact): BusinessContactActions

}