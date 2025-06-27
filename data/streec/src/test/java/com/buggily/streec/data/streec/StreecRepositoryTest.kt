package com.buggily.streec.data.streec

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.buggily.streec.core.data.GetInstant
import com.buggily.streec.core.test.CoroutineTestRule
import com.buggily.streec.local.streec.LocalStreec
import com.buggily.streec.local.streec.LocalStreecSourceable
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
class StreecRepositoryTest {

    private val localStreecSource: LocalStreecSourceable = mockk()
    private val getInstant: GetInstant = mockk()
    private lateinit var streecRepository: StreecRepository

    @get:Rule
    val rule = CoroutineTestRule(StandardTestDispatcher())

    @Before
    fun before() {
        clearAllMocks()
        every { getInstant() } returns INSTANT

        streecRepository = StreecRepository(
            localStreecSource = localStreecSource,
            getInstant = getInstant,
        )
    }

    @Test
    fun getByIdReturnsStreecById() = runTest {
        val localStreec = LocalStreec(
            id = ID,
            name = NAME,
            resetInstant = Clock.System.now(),
            creationInstant = Clock.System.now(),
            modificationInstant = Clock.System.now(),
        )

        coEvery {
            localStreecSource.getById(ID)
        } returns localStreec

        Assert.assertEquals(
            localStreec.to(),
            streecRepository.getById(ID),
        )
    }

    @Test
    fun getPagingReturnsStreecPages() = runTest {
        val localStreec = LocalStreec(
            id = ID,
            name = NAME,
            resetInstant = Clock.System.now(),
            creationInstant = Clock.System.now(),
            modificationInstant = Clock.System.now(),
        )

        val localStreecs: List<LocalStreec> = listOf(
            localStreec,
        )

        coEvery {
            localStreecSource.getPaging()
        } returns flowOf(PagingData.from(localStreecs))

        Assert.assertEquals(
            localStreecs.map { it.to() },
            streecRepository.getPaging().asSnapshot(),
        )
    }

    @Test
    fun getReturnsStreecs() = runTest {
        val localStreec = LocalStreec(
            id = ID,
            name = NAME,
            resetInstant = Clock.System.now(),
            creationInstant = Clock.System.now(),
            modificationInstant = Clock.System.now(),
        )

        val localStreecs: List<LocalStreec> = listOf(
            localStreec,
        )

        coEvery {
            localStreecSource.get()
        } returns flowOf(localStreecs)

        Assert.assertEquals(
            localStreecs.map { it.to() },
            streecRepository.get().first(),
        )
    }

    @Test
    fun createCreatesStreec() = runTest {
        val localStreec = LocalStreec(
            id = ID,
            name = NAME,
            resetInstant = INSTANT,
            creationInstant = INSTANT,
            modificationInstant = INSTANT,
        )

        coEvery {
            localStreecSource.insert(any())
        } returns Unit

        streecRepository.create(NAME)
        coVerify { localStreecSource.insert(localStreec) }
    }

    @Test
    fun updateByIdUpdatesStreecById() = runTest {
        val localStreec = LocalStreec(
            id = ID,
            name = "newname",
            resetInstant = Clock.System.now(),
            creationInstant = Clock.System.now(),
            modificationInstant = Clock.System.now(),
        )

        val streec: LocalStreec = localStreec.copy(
            name = NAME,
            modificationInstant = INSTANT,
        )

        coEvery {
            localStreecSource.getById(ID)
        } returns localStreec

        coEvery {
            localStreecSource.update(any())
        } returns Unit

        streecRepository.updateById(
            id = ID,
            name = NAME,
        )

        coVerify { localStreecSource.update(streec) }
    }

    @Test
    fun resetByIdResetsStreecById() = runTest {
        val localStreec = LocalStreec(
            id = ID,
            name = NAME,
            resetInstant = Clock.System.now(),
            creationInstant = Clock.System.now(),
            modificationInstant = Clock.System.now(),
        )

        val streec: LocalStreec = localStreec.copy(
            resetInstant = INSTANT,
            modificationInstant = INSTANT,
        )

        coEvery {
            localStreecSource.getById(ID)
        } returns localStreec

        coEvery {
            localStreecSource.update(any())
        } returns Unit

        streecRepository.resetById(
            id = ID,
        )

        coVerify { localStreecSource.update(streec) }
    }

    @Test
    fun deleteByIdDeletesStreecById() = runTest {
        val localStreec = LocalStreec(
            id = ID,
            name = NAME,
            resetInstant = Clock.System.now(),
            creationInstant = Clock.System.now(),
            modificationInstant = Clock.System.now(),
        )

        coEvery {
            localStreecSource.getById(ID)
        } returns localStreec

        coEvery {
            localStreecSource.delete(any())
        } returns Unit

        streecRepository.deleteById(
            id = ID,
        )

        coVerify { localStreecSource.delete(localStreec) }
    }

    private companion object {
        private const val ID = 0L
        private const val NAME = "name"
        private val INSTANT = Instant.DISTANT_PAST
    }
}
