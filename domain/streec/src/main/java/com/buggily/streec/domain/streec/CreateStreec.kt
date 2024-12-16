package com.buggily.streec.domain.streec

import com.buggily.streec.data.streec.StreecRepositable

class CreateStreec(
    private val streecRepository: StreecRepositable,
) {

    suspend operator fun invoke(
        name: String,
    ) = streecRepository.create(
        name = name,
    )
}
