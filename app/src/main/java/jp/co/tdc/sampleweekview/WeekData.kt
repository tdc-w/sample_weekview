package jp.co.tdc.sampleweekview

import com.alamkanak.weekview.WeekViewDisplayable
import com.alamkanak.weekview.WeekViewEvent
import java.util.*

data class WeekData(
    val id: Long = 0L,
    val title: String,
    val startTime: Calendar,
    val endTime: Calendar,
    val isAllDay: Boolean = false,
    val style: WeekViewEvent.Style
) : WeekViewDisplayable<WeekData> {

    override fun toWeekViewEvent(): WeekViewEvent<WeekData> {

        return WeekViewEvent.Builder<WeekData>()
            .setTitle(title)
            .setStartTime(startTime)
            .setEndTime(endTime)
            .setAllDay(isAllDay)
            .setStyle(style)
            .setData(this)
            .build()
    }
}