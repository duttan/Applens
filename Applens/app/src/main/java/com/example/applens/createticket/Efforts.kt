package com.example.applens.createticket

import androidx.annotation.Nullable

data class Efforts(

        @Nullable val TicketID: String?,
        @Nullable val ticketDesc: String?,
        @Nullable var TimeSheetID: String,
        @Nullable var Logged_efforts: Int,
        @Nullable var StatusID: String,
        @Nullable var effortDate: String?,
        @Nullable var serviceID: String?,
        @Nullable var activityID: String?
    )
