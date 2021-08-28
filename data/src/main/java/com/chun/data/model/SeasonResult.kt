package com.chun.data.model

import com.chun.domain.model.Anime
import com.google.gson.annotations.SerializedName

class SeasonResult : RestResult<Anime>() {
    @SerializedName("season_name")
    var sessionName: String = ""

    @SerializedName("season_year")
    var sessionYear: Int = 0
}
