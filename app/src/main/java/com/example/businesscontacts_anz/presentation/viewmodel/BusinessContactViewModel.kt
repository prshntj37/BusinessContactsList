package com.example.businesscontacts_anz.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.businesscontacts_anz.domain.repository.BusinessContactsRepository
import com.example.businesscontacts_anz.domain.utils.Result
import com.example.businesscontacts_anz.presentation.actions.BusinessContactActions
import com.example.businesscontacts_anz.presentation.events.BusinessContactEvents
import com.example.businesscontacts_anz.presentation.mappers.asUiString
import com.example.businesscontacts_anz.presentation.states.BusinessContactState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusinessContactViewModel @Inject constructor(
    val repository: BusinessContactsRepository
): ViewModel() {

    private var _businessContactState = MutableStateFlow(BusinessContactState())
    val businessContactState = _businessContactState.asStateFlow()

    private val businessContactEvent = Channel<BusinessContactEvents>()
    val businessContactEventAsFlow = businessContactEvent.receiveAsFlow()

    init {
        onBusinessContactAction(BusinessContactActions.FetchContacts)
    }

    fun onBusinessContactAction(actions: BusinessContactActions){
        when(actions){
            BusinessContactActions.FetchContacts -> {
                viewModelScope.launch {
                    val fetchContactsResult = repository.fetchBusinessContacts()
                    when(fetchContactsResult){
                        is Result.Error -> {
                            _businessContactState.update { currentState ->
                                currentState.copy(isLoadingFromNetwork = false)
                            }
                            businessContactEvent.send(BusinessContactEvents.FetchFailure(fetchContactsResult.error.asUiString()))
                        }
                        is Result.Success -> {
                            val listOfBusinessContacts = fetchContactsResult.data
                            _businessContactState.update {currentState ->
                                currentState.copy(
                                    isLoadingFromNetwork = false,
                                    listOfContacts = listOfBusinessContacts
                                )
                            }
                            businessContactEvent.send(BusinessContactEvents.FetchSuccess)
                        }
                    }
                }
            }

            is BusinessContactActions.TapOnABusinessContactToViewDetails -> {
                val tappedContact = actions.contact
                _businessContactState.update {currentState ->
                    currentState.copy(
                        currentSelectedContact = tappedContact
                    )
                }
            }
        }
    }

}