package com.buggily.streec.core.domain

import kotlinx.datetime.Instant
import kotlinx.datetime.daysUntil

class GetStreecText(
    private val getInstant: GetInstant,
    private val getTimeZone: GetTimeZone,
) {

    operator fun invoke(resetInstant: Instant): String = resetInstant.daysUntil(
        other = getInstant(),
        timeZone = getTimeZone(),
    ).toString()
}
