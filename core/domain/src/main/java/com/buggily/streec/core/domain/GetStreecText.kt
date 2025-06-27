package com.buggily.streec.core.domain

import kotlinx.datetime.daysUntil
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
class GetStreecText(
    private val getInstant: GetInstant,
    private val getTimeZone: GetTimeZone,
) {

    operator fun invoke(resetInstant: Instant): String = resetInstant.daysUntil(
        other = getInstant(),
        timeZone = getTimeZone(),
    ).toString()
}
