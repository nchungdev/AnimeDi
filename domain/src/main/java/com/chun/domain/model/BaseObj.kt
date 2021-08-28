package com.chun.domain.model

open class BaseObj {
    open var id: Int = 0
    open var title: String = ""
    open var imageUrl: String = ""
    open var members: Int = 0
    open var rank: Int = 0
    open var score: Double = 0.0
    open var url: String = ""
    open var startDate: String = ""
    open var endDate: String = ""
    open var type: String = ""
}