package com.buggily.streec.core.data

import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
class GetInstant {

    operator fun invoke(): Instant = Clock.System.now()
}
