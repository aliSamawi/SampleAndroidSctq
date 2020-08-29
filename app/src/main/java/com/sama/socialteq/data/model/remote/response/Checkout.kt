package com.sama.socialteq.data.model.remote.response

data class Checkout(
    val extraNoteField: ExtraNoteField,
    val paymentMethods: List<String>,
    val scheduling: Scheduling
)