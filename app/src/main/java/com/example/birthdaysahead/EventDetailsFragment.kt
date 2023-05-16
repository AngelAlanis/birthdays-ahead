package com.example.birthdaysahead

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.birthdaysahead.databinding.FragmentEventDetailsBinding
import com.example.birthdaysahead.model.Event
import com.example.birthdaysahead.utils.changeBackgroundColor
import com.example.birthdaysahead.viewmodel.EventViewModel

class EventDetailsFragment : Fragment() {

    private lateinit var binding: FragmentEventDetailsBinding
    private lateinit var event: Event
    private val sharedViewModel: EventViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        event = sharedViewModel.event.value!!

        binding.profileIcon.text = event.iconChar.toString()
        binding.profileIcon.background = changeBackgroundColor(requireContext(), event.color)

        binding.profileName.text = event.name

        binding.profileBirthday.text = event.dateString

        binding.profileNotes.text = event.note

        binding.profileWishes.text = event.formattedWishes
        binding.profileLikes.text = event.formattedLikes
        binding.profileDislikes.text = event.formattedDislikes
    }

}