package com.buggily.streec.data.streec

import kotlinx.datetime.Instant

data class Streec(
    val id: Long,
    val name: String,
    val resetInstant: Instant,
)
