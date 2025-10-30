package com.example.businesscontacts_anz.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.businesscontacts_anz.R
import com.example.businesscontacts_anz.domain.model.BusinessContact
import com.example.businesscontacts_anz.presentation.states.BusinessContactState
import com.example.businesscontacts_anz.presentation.viewmodel.BusinessContactViewModel


@Composable
fun ContactDetailsScreenRoot(
    paddingValues: PaddingValues,
    viewModel: BusinessContactViewModel = hiltViewModel<BusinessContactViewModel>(),
    onBackButtonClicked:() -> Unit,
){
    val businessContactState by viewModel.businessContactState.collectAsStateWithLifecycle()
    ContactDetailsScreen(
        paddingValues,
        state = businessContactState,
        onBackButtonClicked = onBackButtonClicked
    )
}

@Composable
fun ContactDetailsScreen(
    paddingValues: PaddingValues,
    state: BusinessContactState,
    onBackButtonClicked:() -> Unit
){
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 8.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                { onBackButtonClicked() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back_button),
                    "Back button"
                )
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Contact details card",
                    fontWeight = FontWeight.Bold, fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxSize()
                .weight(1f)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ){
            Row(
                modifier = Modifier.padding(8.dp)
            ) {
                AsyncImage(
                    model = state.currentSelectedContact?.photo,
                    contentDescription = "Contact profile pic",
                    modifier = Modifier.fillMaxSize(0.5f),
                    alignment = Alignment.Center
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.spacedBy(4.dp)

                ) {
                    AnnotatedText("ID: ",
                        state.currentSelectedContact?.id.toString()
                    )
                    AnnotatedText("Name: ",
                        state.currentSelectedContact?.name ?: "Server has no details"
                    )
                    AnnotatedText("Company: ",
                        state.currentSelectedContact?.company ?: "Server has no details"
                    )
                    AnnotatedText("UserName: ",
                        state.currentSelectedContact?.username ?: "Server has no details"
                    )
                    AnnotatedText("Email: ",
                        state.currentSelectedContact?.email ?: "Server has no details"
                    )
                    AnnotatedText("Address: ",
                        state.currentSelectedContact?.address ?: "Server has no details"
                    )
                    AnnotatedText("Zip: ",
                        state.currentSelectedContact?.zip ?: "Server has no details"
                    )
                    AnnotatedText("State: ",
                        state.currentSelectedContact?.state ?: "Server has no details"
                    )
                    AnnotatedText("Country: ",
                        state.currentSelectedContact?.country ?: "Server has no details"
                    )
                    AnnotatedText("Phone: ",
                        state.currentSelectedContact?.phone ?: "Server has no details"
                    )
                    AnnotatedText("Photo: ",
                        state.currentSelectedContact?.photo ?: "Server has no details"
                    )
                }
            }
        }
    }
}

@Composable
private fun AnnotatedText(
    boldPartOfString: String,
    normalPartOfString:String
){
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(boldPartOfString)
            }
            append(normalPartOfString)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview(){
    ContactDetailsScreen(
        paddingValues = PaddingValues(16.dp),
        state = BusinessContactState(
            currentSelectedContact = BusinessContact(
                9,
                "Alexandrine O&#x27;Connell",
                "Raymundo.Connelly",
                "Adrian57@hotmail.com",
                "Adrian57@hotmail.com",
                "790 Botsford Lake",
                "61973-5960",
                "Arkansas",
                "Bosnia and Herzegovina",
                "561.804.3154 x65388",
                "https://json-server.dev/ai-profiles/63.png"

            )
        ),
        onBackButtonClicked = {}
    )
}