package jp.co.tdc.sampleweekview

import androidx.lifecycle.*
import com.alamkanak.weekview.WeekViewDisplayable
import java.util.*

class SampleViewModel : ViewModel(), LifecycleObserver {

    private val allWeekViewEvents = arrayListOf<WeekViewDisplayable<WeekData>>()
    val weekViewEvents = MutableLiveData<List<WeekViewDisplayable<WeekData>>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun loadFirst() {
        val data = MockDataFactory.getData(Calendar.getInstance())
        allWeekViewEvents.addAll(data)
        weekViewEvents.value = allWeekViewEvents
    }
}