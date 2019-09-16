package com.example.applens.createticket

import androidx.annotation.Nullable

data class Efforts(

        @Nullable val ticket_Id: String?,
        @Nullable val ticketDesc: String?,
        @Nullable var TimeSheetID: String,
        @Nullable var Logged_efforts: Int,
        @Nullable var ticketStatus: String,
        @Nullable var EffortDate: String?,
        @Nullable var ServiceID: String?,
        @Nullable var ActivityID: String?
    )
