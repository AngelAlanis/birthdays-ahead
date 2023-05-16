package com.example.birthdaysahead.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.birthdaysahead.model.Event

class EventViewModel : ViewModel() {

    private val _event = MutableLiveData<Event>()

    val event: LiveData<Event> = _event

    fun setEvent(event: Event) {
        _event.value = event
    }

}