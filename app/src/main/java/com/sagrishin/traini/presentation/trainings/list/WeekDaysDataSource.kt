package com.sagrishin.traini.presentation.trainings.list

import android.util.Log
import androidx.paging.PagingSource
import com.sagrishin.common.utils.*
import com.sagrishin.traini.domain.repositories.TrainingsRepository
import com.sagrishin.traini.presentation.uimodels.UiWeekDay
import java.time.LocalDateTime
import javax.inject.Inject

class WeekDaysDataSource @Inject constructor(
    private val trainingsRepository: TrainingsRepository
): PagingSource<LocalDateTime, UiWeekDay>() {

    override suspend fun load(params: LoadParams<LocalDateTime>): LoadResult<LocalDateTime, UiWeekDay> {
        return when (params) {
            is LoadParams.Refresh -> {
                Log.e("Refresh", params.key.toString())

                /// In this case we should send Current date to DataSource. DS will detect
                /// this case and generate 3 weeks (prev, current, next) fow UI

                val firstDateTime = params.key!!.firstDayOfWeek.atStartOfDay()
                val lastDateTime = params.key!!.lastDayOfWeek.atStartOfDay()

                val trainings = trainingsRepository.loadTrainingsBetween(firstDateTime, lastDateTime)
                    .groupBy { it.startDateTime.toLocalDate() }

                val prev = (firstDateTime.minusWeeks(1)..firstDateTime.minusDays(1)).toList()
//                val prev = emptyList<LocalDateTime>()
                val current = (firstDateTime..lastDateTime).toList()
                val next = (lastDateTime.plusDays(1)..lastDateTime.plusWeeks(1)).toList()
//                val next = emptyList<LocalDateTime>()
                val dateTimes = (prev + current + next)

                val data = dateTimes.map { dateTime ->
                    dateTime.toLocalDate().let { UiWeekDay(it, trainings[it]?.isNotEmpty() == true) }
                }
                LoadResult.Page(data, prevKey = dateTimes.first(), nextKey = dateTimes.last())
            }
            is LoadParams.Append -> {
                Log.e("Append", params.key.toString())

                /// In this case UI must send Last visible dateTime, so all what DS will do
                /// is just create list with dateTime + 1 .. dateTime + 7 days

                val dateTimes = params.key.plusDays(1)..params.key.plusWeeks(1)
                val firstDateTime = dateTimes.start.atStartOfDay()
                val lastDateTime = dateTimes.endInclusive.atEndOfDay()

                val trainings = trainingsRepository.loadTrainingsBetween(firstDateTime, lastDateTime)
                    .groupBy { it.startDateTime.toLocalDate() }

                val data = dateTimes.mapLocalDateTimes { dateTime ->
                    dateTime.toLocalDate().let { UiWeekDay(it, trainings[it]?.isNotEmpty() == true) }
                }
                LoadResult.Page(data, prevKey = dateTimes.start, nextKey = dateTimes.endInclusive)
            }
            is LoadParams.Prepend -> {
                Log.e("Prepend", params.key.toString())

                /// In this case UI must send the most earliest dateTime
                /// to DS, so it just creates week from dateTime - 7 .. dateTime - 1 days

                val dateTimes = params.key.minusWeeks(1)..params.key.minusDays(1)
                val firstDateTime = dateTimes.start.atStartOfDay()
                val lastDateTime = dateTimes.endInclusive.atEndOfDay()

                val trainings = trainingsRepository.loadTrainingsBetween(firstDateTime, lastDateTime)
                    .groupBy { it.startDateTime.toLocalDate() }

                val data = dateTimes.mapLocalDateTimes { dateTime ->
                    dateTime.toLocalDate().let { UiWeekDay(it, trainings[it]?.isNotEmpty() == true) }
                }
                LoadResult.Page(data, prevKey = dateTimes.start, nextKey = dateTimes.endInclusive)
            }
        }
    }

}
