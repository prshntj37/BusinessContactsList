package com.example.businesscontacts_anz.data.remote.dto

data class BusinessContactDTO(
    val id: Int,
    val name:String?=null,
    val company:String?=null,
    val username:String?=null,
    val email:String?=null,
    val address:String?=null,
    val zip:String?=null,
    val state:String?=null,
    val country:String?=null,
    val phone:String?=null,
    val photo:String?=null
)