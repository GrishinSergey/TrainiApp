package com.sagrishin.traini.presentation.trainings.list

import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sagrishin.traini.R
import com.sagrishin.traini.databinding.TrainingItemBinding
import com.sagrishin.traini.presentation.uimodels.UiTrainingItem
import com.sagrishin.uikit.recyclerview.BaseHolder
import com.sagrishin.uikit.utils.getTimeFormattedAsDevicesLocale
import com.sagrishin.uikit.utils.setSafeOnClickListener

class TrainingItemHolder constructor(
    view: ViewGroup,
    private val onShowTrainingDetailsBy: (Long) -> Unit
) : BaseHolder<UiTrainingItem>(R.layout.training_item, view) {

    private val binding by viewBinding(TrainingItemBinding::bind)

    override fun onBind(item: UiTrainingItem) {
        super.onBind(item)

        binding.time.text = getTimeFormattedAsDevicesLocale(item.dateTime)
        binding.trainingTitle.text = item.muscleGroups.joinToString { getString(it.nameResId) }
        binding.exercises.text = item.exerciseNames.joinToString()
        binding.root.setSafeOnClickListener { onShowTrainingDetailsBy(item.id) }
    }

}
