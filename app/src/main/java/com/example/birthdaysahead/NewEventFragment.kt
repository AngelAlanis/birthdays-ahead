package com.example.birthdaysahead

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.birthdaysahead.databinding.NewEventLayoutBinding


class NewEventFragment : Fragment() {

    private lateinit var binding: NewEventLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewEventLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

}