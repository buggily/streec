package com.buggily.streec.core.data

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

class GetInstant {

    operator fun invoke(): Instant = Clock.System.now()
}
