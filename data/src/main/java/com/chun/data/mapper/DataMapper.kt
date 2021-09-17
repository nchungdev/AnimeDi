package com.chun.data.mapper

import com.chun.domain.model.Display
import com.chun.domain.model.Display.Companion.UNKNOWN_DISPLAY
import com.chun.domain.model.Layout
import com.chun.domain.model.MultiLang
import com.chun.domain.model.Request
import com.chun.domain.model.Request.Companion.DEFAULT_REQUEST
import com.chun.domain.model.type.ObjType
import com.google.firebase.firestore.DocumentSnapshot

class DataMapper {
//
//    fun dbToModel(otakuEntity: OtakuEntity) = Otaku().apply {
//        id = otakuEntity.id
//        title = otakuEntity.title
//        imageUrl = otakuEntity.imageUrl
//        url = otakuEntity.url
//        type = otakuEntity.type
//    }
//
//    fun modelToDb(otaku: Otaku) = OtakuEntity().apply {
//        id = otaku.id
//        title = otaku.title
//        imageUrl = otaku.imageUrl
//        url = otaku.url
//        type = otaku.type
//    }

    fun documentToModel(doc: DocumentSnapshot) = Layout(
        title = doc.get("title", MultiLang::class.java) ?: MultiLang(),
        subtitle = doc.get("subtitle", MultiLang::class.java) ?: MultiLang(),
        display = doc.get("display", Display::class.java) ?: UNKNOWN_DISPLAY,
        request = doc.get("request", Request::class.java) ?: DEFAULT_REQUEST,
        type = doc.getString("objType") ?: ObjType.ANIME,
    )
//
//    fun modelToDb(home: Home) = HomeEntity().apply {
//        title = home.title
//        subtitle = home.subtitle
//        display = home.display
//        items = home.items.map { modelToDb(it) }
//    }
//
//    fun dbToModel(homeEntity: HomeEntity) = Home(
//        title = homeEntity.title,
//        subtitle = homeEntity.subtitle,
//        display = homeEntity.display,
//        items = homeEntity.items.map { dbToModel(it) }
//    )
}