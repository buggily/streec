package com.buggily.streec.domain.streec

import com.buggily.streec.data.streec.StreecRepositable

class UpdateStreecById(
    private val streecRepository: StreecRepositable,
) {

    suspend operator fun invoke(
        id: Long,
        name: String,
    ) = streecRepository.updateById(
        id = id,
        name = name,
    )
}
