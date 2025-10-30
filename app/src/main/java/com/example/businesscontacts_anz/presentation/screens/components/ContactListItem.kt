package com.example.businesscontacts_anz.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscontacts_anz.domain.model.BusinessContact

@Composable
fun ContactListItem(
    contact: BusinessContact,
    onContactClick: () -> Unit
){
    Text(
        text = contact.name?:"Name missing in server",
        fontSize = 14.sp,
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(16.dp)
            .clickable{
                onContactClick()
            }

    )
}

@Preview(showBackground = true)
@Composable
private fun ContactListItemPreview(

){
    ContactListItem(
        BusinessContact(
            1,
            "BusinessContact1",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
        ),
        onContactClick = {}
    )
}