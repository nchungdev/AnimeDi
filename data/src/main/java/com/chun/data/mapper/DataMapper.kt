package com.chun.data.mapper

import com.chun.domain.model.*
import com.chun.domain.model.type.Display
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

    fun documentToModel(doc: DocumentSnapshot) = Layout().apply {
        title = doc.get("title", MultiLang::class.java) ?: MultiLang()
        subtitle = doc.get("subtitle", MultiLang::class.java) ?: MultiLang()
        display = doc.getLong("display")?.toInt() ?: Display.UNIDENTIFIED
        dataType = doc.get("dataType", DataType::class.java) ?: DataType()
    }
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