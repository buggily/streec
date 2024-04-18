package com.buggily.streec.local.core

import com.buggily.streec.local.streec.LocalStreecDao

interface StreecDatabaseable {
    fun getLocalStreecDao(): LocalStreecDao
}