package com.chun.data.typeadapter

import com.chun.domain.model.BaseObj

class BaseObjectTypeAdapter : BaseObjTypeAdapter<BaseObj>() {
    override fun createObj() = BaseObj()
}