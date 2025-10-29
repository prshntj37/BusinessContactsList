package com.example.businesscontacts_anz.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.businesscontacts_anz.R
import com.example.businesscontacts_anz.presentation.CollectOneTimeEventFlow
import com.example.businesscontacts_anz.presentation.actions.BusinessContactActions
import com.example.businesscontacts_anz.presentation.events.BusinessContactEvents
import com.example.businesscontacts_anz.presentation.screens.components.ContactListItem
import com.example.businesscontacts_anz.presentation.states.BusinessContactState
import com.example.businesscontacts_anz.presentation.viewmodel.BusinessContactViewModel

@Composable
fun ContactListScreenRoot(
    paddingValues: PaddingValues,
    viewModel: BusinessContactViewModel = hiltViewModel<BusinessContactViewModel>(),
    onContactClicked:() -> Unit,
){
    val context = LocalContext.current
    CollectOneTimeEventFlow(
        flow = viewModel.businessContactEventAsFlow
    ){businessContactEvent->
      when(businessContactEvent){
          is BusinessContactEvents.FetchFailure -> {
              Toast.makeText(
                  context,
                  businessContactEvent.error.asString(context),
                  Toast.LENGTH_LONG
              ).show()
          }
          BusinessContactEvents.FetchSuccess -> {
              Toast.makeText(
                  context,
                  context.getString(R.string.fetch_success),
                  Toast.LENGTH_LONG
              ).show()
          }
      }
    }
    val businessContactState by viewModel.businessContactState.collectAsStateWithLifecycle()
    ContactListScreen(
        paddingValues = paddingValues,
        state = businessContactState,
        onAction = {contactActions->
            viewModel.onBusinessContactAction(contactActions)
        },
        onContactClicked = onContactClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(
    paddingValues: PaddingValues,
    state: BusinessContactState,
    onAction:(BusinessContactActions) -> Unit,
    onContactClicked:() -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Text(
            text = stringResource(R.string.business_contacts_List),
            fontSize = 18.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 16.dp).align(Alignment.CenterHorizontally)
        )
        if (state.isLoadingFromNetwork) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Row{
                    CircularProgressIndicator()
                    Text(stringResource(R.string.fetching_contacts))
                }

            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = state.listOfContacts,
                    key = {it.id}

                ) {contact->
                    ContactListItem(
                        contact
                    ) {
                        onAction(BusinessContactActions.TapOnABusinessContactToViewDetails(contact))
                        onContactClicked()
                    }
                }
            }
        }
    }
}
