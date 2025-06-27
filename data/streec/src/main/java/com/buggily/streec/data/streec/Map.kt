package com.buggily.streec.data.streec

import com.buggily.streec.local.streec.LocalStreec
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun LocalStreec.to(): Streec = Streec(
    id = id,
    name = name,
    resetInstant = resetInstant,
)

@OptIn(ExperimentalTime::class)
fun Streec.toLocal(
    creationInstant: Instant,
    modificationInstant: Instant,
): LocalStreec = LocalStreec(
    id = id,
    name = name,
    resetInstant = resetInstant,
    creationInstant = creationInstant,
    modificationInstant = modificationInstant,
)
