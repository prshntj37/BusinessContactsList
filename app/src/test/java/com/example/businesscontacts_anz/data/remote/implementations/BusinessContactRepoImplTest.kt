package com.example.businesscontacts_anz.data.remote.implementations

import com.example.businesscontacts_anz.data.remote.api.FakeJsonApiInterface
import com.example.businesscontacts_anz.data.remote.dto.BusinessContactDTO
import com.example.businesscontacts_anz.domain.utils.DataError
import com.example.businesscontacts_anz.domain.utils.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class BusinessContactRepoImplTest {

    private val fakeApi = mockk<FakeJsonApiInterface>()
    private lateinit var repository: BusinessContactRepoImpl

    @Before
    fun setup() {
        repository = BusinessContactRepoImpl(fakeApi)
    }

    @Test
    fun `fetchBusinessContacts returns success when api returns data`() = runTest {
        // Given
        val fakeResponseBody = listOf(
            BusinessContactDTO(id = 1, name = "John")// your DTO model
        )
        val fakeResponse = Response.success(fakeResponseBody)
        coEvery { fakeApi.fetchBusinessContacts() } returns fakeResponse

        // When
        val result = repository.fetchBusinessContacts()

        // Then
        assert(result is com.example.businesscontacts_anz.domain.utils.Result.Success)
        assert((result as com.example.businesscontacts_anz.domain.utils.Result.Success).data.first().name == "John")
    }

    @Test
    fun `fetchBusinessContacts returns error when api returns empty list`() = runTest {
        val emptyResponse = Response.success(emptyList<BusinessContactDTO>())
        coEvery { fakeApi.fetchBusinessContacts() } returns emptyResponse

        val result = repository.fetchBusinessContacts()

        assert(result is com.example.businesscontacts_anz.domain.utils.Result.Error)
        assert((result as Result.Error).error == DataError.Network.NO_DATA_IN_SERVER_ERROR)
    }
}