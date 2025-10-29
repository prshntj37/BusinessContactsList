package com.example.businesscontacts_anz.data.remote.mappers

import com.example.businesscontacts_anz.data.remote.dto.BusinessContactDTO
import com.example.businesscontacts_anz.domain.model.BusinessContact

fun BusinessContactDTO.toBusinessContactModel(): BusinessContact{
    return BusinessContact(
        this.id,
        this.name,
        this.company,
        this.username,
        this.email,
        this.address,
        this.zip,
        this.state,
        this.country,
        this.phone,
        this.photo
    )
}