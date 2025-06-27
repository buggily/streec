package com.buggily.streec.data.streec

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
data class Streec(
    val id: Long,
    val name: String,
    val resetInstant: Instant,
)
