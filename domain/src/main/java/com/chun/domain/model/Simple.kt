package com.chun.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Simple(
    var id: Int = 0,
    var type: String = "",
    var name: String = "",
    var url: String = "",
) : Parcelable