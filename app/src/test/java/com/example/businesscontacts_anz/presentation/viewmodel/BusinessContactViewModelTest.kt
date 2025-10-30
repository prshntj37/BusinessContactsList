package com.example.businesscontacts_anz.presentation.viewmodel

import com.example.businesscontacts_anz.domain.model.BusinessContact
import com.example.businesscontacts_anz.domain.repository.BusinessContactsRepository
import com.example.businesscontacts_anz.domain.utils.DataError
import com.example.businesscontacts_anz.domain.utils.Result
import com.example.businesscontacts_anz.presentation.actions.BusinessContactActions
import com.example.businesscontacts_anz.presentation.events.BusinessContactEvents
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class BusinessContactViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: BusinessContactsRepository
    private lateinit var viewModel: BusinessContactViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `onBusinessContactAction FetchContacts updates state on success`() = runTest {

        val fakeContacts = listOf(BusinessContact(1, "BusinessContact1"))
        coEvery { repository.fetchBusinessContacts() } returns Result.Success(fakeContacts)

        viewModel = BusinessContactViewModel(repository)

       
        advanceUntilIdle() // wait for init{} to finish


        val state = viewModel.businessContactState.value
        assert(!state.isLoadingFromNetwork)
        assert(state.listOfContacts.size == 1)
        assert(state.listOfContacts.first().name == "BusinessContact1")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `onBusinessContactAction FetchContacts emits error event`() = runTest {
        coEvery { repository.fetchBusinessContacts() } returns com.example.businesscontacts_anz.domain.utils.Result.Error((DataError.Network.NO_DATA_IN_SERVER_ERROR))

        viewModel = BusinessContactViewModel(repository)

        val events = mutableListOf<BusinessContactEvents>()
        val job = launch {
            viewModel.businessContactEventAsFlow.toList(events)
        }

        advanceUntilIdle()

        assert(events.any { it is BusinessContactEvents.FetchFailure })

        job.cancel()
    }

    @Test
    fun `TapOnABusinessContact updates currentSelectedContact`() = runTest {
        val fakeContacts = listOf(BusinessContact(1, "BusinessContact1"))
        coEvery { repository.fetchBusinessContacts() } returns Result.Success(fakeContacts)
        val contact = BusinessContact(1, "Bob")
        viewModel = BusinessContactViewModel(repository)

        viewModel.onBusinessContactAction(
            BusinessContactActions.TapOnABusinessContactToViewDetails(contact)
        )

        assert(viewModel.businessContactState.value.currentSelectedContact == contact)
    }
}