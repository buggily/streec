package com.buggily.streec.domain.streec

import com.buggily.streec.data.streec.StreecRepositable

class DeleteStreecById(
    private val streecRepository: StreecRepositable,
) {

    suspend operator fun invoke(
        id: Long,
    ) = streecRepository.deleteById(
        id = id,
    )
}
