package com.buggily.streec.data.streec

import com.buggily.streec.local.streec.LocalStreec
import kotlinx.datetime.Instant

fun LocalStreec.to(): Streec = Streec(
    id = id,
    name = name,
    resetInstant = resetInstant,
)

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
