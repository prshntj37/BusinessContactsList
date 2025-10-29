package com.example.businesscontacts_anz.presentation.events

import com.example.businesscontacts_anz.presentation.mappers.UiString

sealed interface BusinessContactEvents {

    data object FetchSuccess: BusinessContactEvents

    data class FetchFailure(val error: UiString): BusinessContactEvents
}