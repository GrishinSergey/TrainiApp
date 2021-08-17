package com.sagrishin.traini.presentation.trainings.details

import android.os.Bundle
import androidx.navigation.navArgs
import com.sagrishin.common.activities.NavHostActivity
import com.sagrishin.traini.R

class SingleTrainingNavActivity : NavHostActivity() {

    private val args by navArgs<SingleTrainingNavActivityArgs>()
    override val params: Params
        get() = Params(
            graphId = R.navigation.nav_single_training,
            startParams = TrainingDetailsFragmentArgs(args.trainingId).toBundle()
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}
