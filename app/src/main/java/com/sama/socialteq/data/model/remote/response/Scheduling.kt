package com.sama.socialteq.data.model.remote.response

data class Scheduling(
    val _id: String,
    val asapTitle: String,
    val asapTitles: AsapTitles,
    val hasAsapFeature: Boolean,
    val hasReturnDate: Boolean,
    val hasScheduleFeature: Boolean,
    val id: String,
    val scheduleTitle: String,
    val scheduleTitles: ScheduleTitles
)