package com.buggily.streec.domain.streec

import androidx.paging.PagingData
import androidx.paging.map
import com.buggily.streec.core.domain.GetStreecText
import com.buggily.streec.data.streec.Streec
import com.buggily.streec.data.streec.StreecRepositable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetStreecPaging(
    private val streecRepository: StreecRepositable,
    private val getStreecText: GetStreecText,
) {

    operator fun invoke(): Flow<PagingData<StreecUi>> = streecRepository.getPaging().map {
        it.map { streec: Streec -> streec.toUi(getStreecText) }
    }
}