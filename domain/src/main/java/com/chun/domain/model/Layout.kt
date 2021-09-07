package com.chun.domain.model

import com.chun.domain.model.type.ObjType

data class Layout(
    val title: MultiLang = MultiLang(),
    val subtitle: MultiLang = MultiLang(),
    val display: Display = Display.UNKNOWN_DISPLAY,
    val request: Request = Request.DEFAULT_REQUEST,
    @ObjType val type: String = ObjType.UNKNOWN
)
