package com.buggily.streec.domain.streec

import com.buggily.streec.data.streec.StreecRepositable

class ResetStreecById(
    private val streecRepository: StreecRepositable,
) {

    suspend operator fun invoke(
        id: Long,
    ) = streecRepository.resetById(
        id = id,
    )
}
