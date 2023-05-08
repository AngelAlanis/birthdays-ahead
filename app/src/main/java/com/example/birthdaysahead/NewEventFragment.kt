package com.example.birthdaysahead

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.birthdaysahead.databinding.NewEventLayoutBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class NewEventFragment : Fragment() {

    private lateinit var binding: NewEventLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewEventLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.dateEditText.setOnClickListener {
            val calendar = Calendar.getInstance()

            val materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select the birthdate")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

            materialDatePicker.addOnPositiveButtonClickListener {
                val date = Date(it)
                updateEditText(date)
            }

            materialDatePicker.show(requireActivity().supportFragmentManager, "materialDatePicker")
        }
    }

    private fun updateEditText(date: Date) {
        val format = "dd/MM/yyyy"
        val formatter = SimpleDateFormat(format, Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("UTC")

        binding.dateEditText.setText(formatter.format(date).toString())
    }
}
