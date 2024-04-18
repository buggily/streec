package com.buggily.streec.domain.streec

import com.buggily.streec.core.domain.GetStreecText
import com.buggily.streec.data.streec.StreecRepositable

class GetStreecById(
    private val streecRepository: StreecRepositable,
    private val getStreecText: GetStreecText,
) {

    suspend operator fun invoke(
        id: Long,
    ): StreecUi? = streecRepository.getById(
        id = id,
    )?.toUi(getStreecText)
}
