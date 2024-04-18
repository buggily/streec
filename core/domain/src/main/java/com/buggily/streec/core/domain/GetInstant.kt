package com.buggily.streec.core.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

class GetInstant {

    operator fun invoke(): Instant = Clock.System.now()
}
