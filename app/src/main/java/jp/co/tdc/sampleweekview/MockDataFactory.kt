package jp.co.tdc.sampleweekview

import android.graphics.Color
import com.alamkanak.weekview.WeekViewDisplayable
import com.alamkanak.weekview.WeekViewEvent
import java.util.*

object MockDataFactory {

    fun getData(startDate: Calendar): List<WeekViewDisplayable<WeekData>> {

        val backgroundColor = Color.WHITE
        val color = Color.parseColor("#44ddee")
        val borderWidth = 4
        val style = WeekViewEvent.Style.Builder()
            .setTextColor(color)
            .setBackgroundColor(backgroundColor)
            .setTextStrikeThrough(false)
            .setBorderWidth(borderWidth)
            .setBorderColor(color)
            .build()

        val newYear = startDate.get(Calendar.YEAR)
        val newMonth = startDate.get(Calendar.MONTH)

        val events = ArrayList<WeekViewDisplayable<WeekData>>()
        var event: WeekData

        var startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 8)
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.MONTH, newMonth)
        startTime.set(Calendar.YEAR, newYear)
        var endTime = startTime.clone() as Calendar
        endTime.add(Calendar.MINUTE, 30)
        endTime.set(Calendar.MONTH, newMonth)

        event = WeekData(1, getEventTitle(startTime), startTime, endTime, false, style)
        events.add(event)

        // Add multi-day event
        startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 17)
        startTime.set(Calendar.MINUTE, 30)
        startTime.set(Calendar.MONTH, newMonth)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.add(Calendar.DAY_OF_MONTH, 1)
        endTime.set(Calendar.HOUR_OF_DAY, 4)
        endTime.set(Calendar.MINUTE, 30)
        endTime.set(Calendar.MONTH, newMonth)

        event = WeekData(123, getEventTitle(startTime), startTime, endTime, false, style)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 3)
        startTime.set(Calendar.MINUTE, 30)
        startTime.set(Calendar.MONTH, newMonth)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.set(Calendar.HOUR_OF_DAY, 4)
        endTime.set(Calendar.MINUTE, 30)
        endTime.set(Calendar.MONTH, newMonth)

        event = WeekData(10, getEventTitle(startTime), startTime, endTime, false, style)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 4)
        startTime.set(Calendar.MINUTE, 30)
        startTime.set(Calendar.MONTH, newMonth)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.set(Calendar.HOUR_OF_DAY, 5)
        endTime.set(Calendar.MINUTE, 0)

        event = WeekData(10, getEventTitle(startTime), startTime, endTime, false, style)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 5)
        startTime.set(Calendar.MINUTE, 30)
        startTime.set(Calendar.MONTH, newMonth)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.add(Calendar.HOUR_OF_DAY, 2)
        endTime.set(Calendar.MONTH, newMonth)

        event = WeekData(2, getEventTitle(startTime), startTime, endTime, false, style)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 5)
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.MONTH, newMonth)
        startTime.set(Calendar.YEAR, newYear)
        startTime.add(Calendar.DATE, 1)
        endTime = startTime.clone() as Calendar
        endTime.add(Calendar.HOUR_OF_DAY, 3)
        endTime.set(Calendar.MONTH, newMonth)

        event = WeekData(3, getEventTitle(startTime), startTime, endTime, false, style)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.DAY_OF_MONTH, 15)
        startTime.set(Calendar.HOUR_OF_DAY, 3)
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.MONTH, newMonth)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.add(Calendar.HOUR_OF_DAY, 3)

        event =
            WeekData(4, getEventTitle(startTime), startTime, endTime, false, style)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.DAY_OF_MONTH, 1)
        startTime.set(Calendar.HOUR_OF_DAY, 3)
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.MONTH, newMonth)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.add(Calendar.HOUR_OF_DAY, 3)

        event = WeekData(5, getEventTitle(startTime), startTime, endTime, false, style)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.DAY_OF_MONTH, startTime.getActualMaximum(Calendar.DAY_OF_MONTH))
        startTime.set(Calendar.HOUR_OF_DAY, 15)
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.MONTH, newMonth)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.add(Calendar.HOUR_OF_DAY, 3)

        event = WeekData(5, getEventTitle(startTime), startTime, endTime, false, style)
        events.add(event)

        // AllDay event
        // across month
        startTime = Calendar.getInstance()
        val prevMonth = (startTime.clone() as Calendar).apply {
            add(Calendar.MONTH, -1)
        }
        val prevLastDate = prevMonth.getActualMaximum(Calendar.DATE)
        startTime.set(Calendar.MONTH, prevMonth.get(Calendar.MONTH))
        startTime.set(Calendar.DATE, prevLastDate)
//        startTime.set(Calendar.MONTH, newMonth)
        startTime.set(Calendar.DATE, 1)
        startTime.set(Calendar.HOUR_OF_DAY, 0)
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.set(Calendar.MONTH, prevMonth.get(Calendar.MONTH) + 1)
        endTime.set(Calendar.DATE, 3)

        event = WeekData(7, getEventTitle(startTime), startTime, endTime, true, style)
        events.add(event)

        // All day event until 00:00 next day
        startTime = Calendar.getInstance()
        startTime.set(Calendar.DAY_OF_MONTH, 10)
        startTime.set(Calendar.HOUR_OF_DAY, 0)
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.SECOND, 0)
        startTime.set(Calendar.MILLISECOND, 0)
        startTime.set(Calendar.MONTH, newMonth)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.set(Calendar.DAY_OF_MONTH, 11)

        event = WeekData(8, getEventTitle(startTime), startTime, endTime, true, style)
        events.add(event)

        return events
    }

    private fun getEventTitle(time: Calendar): String {
        val hour = time.get(Calendar.HOUR_OF_DAY)
        val minute = time.get(Calendar.MINUTE)
        val month = time.get(Calendar.MONTH) + 1
        val dayOfMonth = time.get(Calendar.DAY_OF_MONTH)
        return String.format(
            Locale.getDefault(),
            "Event of %02d:%02d %s/%d",
            hour,
            minute,
            month,
            dayOfMonth
        )
    }
}