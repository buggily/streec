package com.buggily.streec.domain.streec

import com.buggily.streec.core.domain.GetStreecText
import com.buggily.streec.data.streec.Streec
import com.buggily.streec.data.streec.StreecRepositable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetStreecs(
    private val streecRepository: StreecRepositable,
    private val getStreecText: GetStreecText,
) {

    operator fun invoke(): Flow<List<StreecUi>> = streecRepository.get().map {
        it.map { streec: Streec -> streec.toUi(getStreecText) }
    }
}
