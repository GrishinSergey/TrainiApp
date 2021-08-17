package com.sagrishin.traini.presentation.trainings.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sagrishin.common.utils.currentDay
import com.sagrishin.traini.R
import com.sagrishin.traini.databinding.WeekDayItemBinding
import com.sagrishin.traini.presentation.trainings.list.WeekDaysAdapter.OnWeekDaySelectListener
import com.sagrishin.traini.presentation.trainings.list.WeekDaysAdapter.WeekDayHolder
import com.sagrishin.traini.presentation.uimodels.UiWeekSelectableDay
import com.sagrishin.uikit.recyclerview.BaseHolder
import com.sagrishin.uikit.utils.setSafeOnClickListener
import java.time.format.TextStyle
import java.util.*

class WeekDaysAdapter constructor(
    private val listener: OnWeekDaySelectListener
) : PagingDataAdapter<UiWeekSelectableDay, WeekDayHolder>(WeekDayDiffCallbackImpl()) {

    private var selectedPos: Int? = null
    private val _listener = OnWeekDaySelectListener { pos ->
        if (selectedPos != pos) {
            if (selectedPos != null) {
                val selectedDay = this[selectedPos!!]
                selectedDay.isSelected = false
                notifyItemChanged(selectedPos!!)
            }

            val newSelected = this[pos]
            newSelected.isSelected = true
            notifyItemChanged(pos)

            selectedPos = pos
            listener(pos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekDayHolder {
        val inflater = LayoutInflater.from(parent.context)
        return WeekDayHolder(WeekDayItemBinding.inflate(inflater, parent, false), _listener)
    }

    override fun onBindViewHolder(holder: WeekDayHolder, position: Int) {
        val item = getItem(position)!!

        holder.onBind(item)
    }

    operator fun get(position: Int): UiWeekSelectableDay {
        return peek(position)!!
    }


    class WeekDayHolder(
        private val binding: WeekDayItemBinding,
        private val listener: OnWeekDaySelectListener
    ) : BaseHolder<UiWeekSelectableDay>(binding.root) {

        override fun onBind(item: UiWeekSelectableDay) {
            binding.root.setSafeOnClickListener { listener(bindingAdapterPosition) }

            val weekDayName = item.weekDay.day.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
            val monthDayNumber = item.weekDay.day.dayOfMonth

            binding.formattedWeekDay.text = getString(R.string.week_day_format, weekDayName, monthDayNumber)
            binding.notificationIcon.isVisible = item.weekDay.hasTraining

            when {
                item.isSelected -> {
                    binding.dateCard.setCardBackgroundColor(getColor(R.color.colorBlue))
                    binding.formattedWeekDay.setTextColor(getColor(android.R.color.white))
                }
                (item.weekDay.day == currentDay) -> {
                    binding.dateCard.setCardBackgroundColor(getColor(R.color.colorB8D5EF))
                    binding.formattedWeekDay.setTextColor(getColor(R.color.colorBlue))
                }
                else -> {
                    binding.dateCard.setCardBackgroundColor(getColor(android.R.color.white))
                    binding.formattedWeekDay.setTextColor(getColor(android.R.color.black))
                }
            }
        }

    }


    private class WeekDayDiffCallbackImpl : DiffUtil.ItemCallback<UiWeekSelectableDay>() {
        override fun areItemsTheSame(oldItem: UiWeekSelectableDay, newItem: UiWeekSelectableDay): Boolean {
            return oldItem.weekDay.day == newItem.weekDay.day
        }

        override fun areContentsTheSame(oldItem: UiWeekSelectableDay, newItem: UiWeekSelectableDay): Boolean {
            return oldItem == newItem
        }
    }


    fun interface OnWeekDaySelectListener {
        operator fun invoke(position: Int)
    }

}