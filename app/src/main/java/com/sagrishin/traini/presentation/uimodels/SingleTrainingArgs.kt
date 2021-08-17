package com.sagrishin.traini.presentation.uimodels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

sealed class SingleTrainingArgs : Parcelable


@Parcelize
class NewSingleTrainingArgs(
    val createdOn: LocalDateTime
) : SingleTrainingArgs()


@Parcelize
class ExistingSingleTrainingArgs(
    val id: Long
) : SingleTrainingArgs()
