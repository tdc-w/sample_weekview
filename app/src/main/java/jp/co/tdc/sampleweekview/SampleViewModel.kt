package jp.co.tdc.sampleweekview

import androidx.lifecycle.*
import com.alamkanak.weekview.WeekViewDisplayable
import java.util.*

class SampleViewModel : ViewModel(), LifecycleObserver {

    private val allWeekViewEvents = arrayListOf<WeekViewDisplayable<WeekData>>()
    val weekViewEvents = MutableLiveData<List<WeekViewDisplayable<WeekData>>>()
    var currentMonth = -1

    init {
    }

    override fun onCleared() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun loadFirst() {
        val data = MockDataFactory.getData(Calendar.getInstance())
        allWeekViewEvents.addAll(data)
        weekViewEvents.value = allWeekViewEvents
    }


    private fun loadMoreData(newFirstDate: Calendar) {
//        val data = MockDataFactory.getData(newFirstDate)
//        allWeekViewEvents.addAll(data)
//        weekViewEvents.value = allWeekViewEvents
    }

    fun updateFirstVisibleDate(newFirstDate: Calendar) {
        val newMonth = newFirstDate.get(Calendar.MONTH)
        if (currentMonth < 0) {
            currentMonth = newMonth
        } else {
            if (newMonth > currentMonth) {
                loadMoreData(newFirstDate)
            }
        }
    }
}