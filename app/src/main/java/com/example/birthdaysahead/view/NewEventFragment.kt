package com.example.birthdaysahead.view

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.birthdaysahead.R
import com.example.birthdaysahead.databinding.NewEventLayoutBinding
import com.example.birthdaysahead.model.TypeOfEvent
import com.example.birthdaysahead.utils.changeBackgroundColor
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.nvt.color.ColorPickerDialog
import java.text.SimpleDateFormat
import java.util.*


class NewEventFragment : Fragment() {

    private lateinit var binding: NewEventLayoutBinding

    private val typeOfEvents: Array<String> =
        TypeOfEvent.values().map { it.getFormattedName() }.toTypedArray()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewEventLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (binding.eventEditText as? MaterialAutoCompleteTextView)?.setSimpleItems(typeOfEvents)

        initListeners()
    }

    private fun initListeners() {
        binding.dateEditText.setOnClickListener {
            val calendar = Calendar.getInstance()

            val currentYear = calendar.get(Calendar.YEAR)
            val maxYear = currentYear + 5

            val maxDate = calendar.apply {
                set(Calendar.YEAR, maxYear)
            }.timeInMillis

            val calendarConstraints = CalendarConstraints.Builder()
                .setEnd(maxDate)
                .build()

            val materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select the birthdate")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setCalendarConstraints(calendarConstraints)
                .build()

            materialDatePicker.addOnPositiveButtonClickListener {
                val date = Date(it)
                updateEditText(date)
            }

            materialDatePicker.show(requireActivity().supportFragmentManager, "materialDatePicker")
        }

        binding.nameEditText.addTextChangedListener {
            val text = binding.nameEditText.text?.toString()
            if (!text.isNullOrEmpty()) {
                binding.profileChar.text = text[0].toString()
            }
        }

        binding.colorSelector.setOnClickListener {
            val currentColor: Int = (binding.colorSelector.background as ColorDrawable).color

            val colorPicker = ColorPickerDialog(
                context,
                currentColor, // color init
                false, // true is show alpha
                object : ColorPickerDialog.OnColorPickerListener {
                    override fun onCancel(dialog: ColorPickerDialog?) {

                    }

                    override fun onOk(dialog: ColorPickerDialog?, colorPicker: Int) {
                        binding.profileBackground.background =
                            changeBackgroundColor(requireContext(), colorPicker)
                        binding.colorSelector.setBackgroundColor(colorPicker)
                    }
                })
            colorPicker.show()
        }

        binding.btnCancel.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun updateEditText(date: Date) {
        val format = "dd/MM/yyyy"
        val formatter = SimpleDateFormat(format, Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("UTC")

        binding.dateEditText.setText(formatter.format(date).toString())
    }
}
