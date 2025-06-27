package com.buggily.streec.domain.streec

import com.buggily.streec.core.domain.GetStreecText
import com.buggily.streec.data.streec.Streec
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun Streec.toUi(
    getStreecText: GetStreecText,
): StreecUi = StreecUi(
    id = id,
    nameText = name,
    streecText = getStreecText(resetInstant),
)
