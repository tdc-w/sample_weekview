package jp.co.tdc.sampleweekview

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.alamkanak.weekview.ScrollListener
import com.alamkanak.weekview.WeekView
import com.alamkanak.weekview.WeekViewEvent
import com.alamkanak.weekview.setMonthChangeListener
import java.util.*

class SampleActivity : AppCompatActivity() {

    private lateinit var weekView: WeekView<WeekData>

    private val viewModel = SampleViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weekView = findViewById(R.id.weekView)

        setupWeekView()
        setupViewModel()
    }

    private fun setupWeekView() {

        weekView.setMonthChangeListener { startDate, endDate ->
            val newYear = startDate.get(Calendar.YEAR)
            val newMonth = startDate.get(Calendar.MONTH)
            viewModel.weekViewEvents.value?.let {
                it.filter { eventMatches(it.toWeekViewEvent(), newYear, newMonth) }
            } ?: listOf()
        }

        weekView.scrollListener = object : ScrollListener {
            override fun onFirstVisibleDayChanged(
                @NonNull newFirstVisibleDay: Calendar,
                @Nullable oldFirstVisibleDay: Calendar?
            ) {
                viewModel.updateFirstVisibleDate(newFirstVisibleDay)
            }
        }
    }

    private fun setupViewModel() {
        lifecycle.addObserver(viewModel)

        viewModel.weekViewEvents.observeNonNull(this) {
            weekView.notifyDataSetChanged()
        }
    }

    private fun eventMatches(event: WeekViewEvent<*>, year: Int, month: Int): Boolean {
        val startsOnDate = event.startTime.get(Calendar.YEAR) == year &&
                event.startTime.get(Calendar.MONTH) == month
        val endsOnDate = event.endTime.get(Calendar.YEAR) == year &&
                event.endTime.get(Calendar.MONTH) == month
        return startsOnDate || endsOnDate
    }

//    private fun eventMatches(event: WeekViewEvent<*>, year: Int, month: Int): Boolean {
//        return event.startTime.get(Calendar.YEAR) == year
//                && event.startTime.get(Calendar.MONTH) == month - 1
//                || event.endTime.get(Calendar.YEAR) == year
//                && event.endTime.get(Calendar.MONTH) == month - 1
//    }
}