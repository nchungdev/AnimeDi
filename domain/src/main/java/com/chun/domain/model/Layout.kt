package com.chun.domain.model

import com.chun.domain.model.type.Display

class Layout {
    var title: MultiLang = MultiLang()
    var subtitle: MultiLang = MultiLang()
    var display: Int = Display.UNIDENTIFIED
    var dataType: DataType = DataType()

    override fun toString(): String {
        return "Layout(title=$title, subtitle=$subtitle, display='$display', dataType=$dataType)"
    }
}

class MultiLang {
    var en: String = ""
    var vi: String = ""

    override fun toString(): String {
        return "MultiLang(en='$en', vi='$vi')"
    }
}

class DataType {
    var type: String = ""
    var subtype: String = ""
    var reference: String = ""

    override fun toString(): String {
        return "DataType(type='$type', subtype='$subtype')"
    }
}
