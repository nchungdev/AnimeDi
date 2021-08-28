//package com.chun.domain.usecase
//
//import com.chun.domain.model.BaseObj
//import com.chun.domain.repository.RestRepository
//import com.chun.domain.usecase.base.MediatorUseCase
//import javax.inject.Inject
//
//class FetchGenreUseCase @Inject constructor(
//    private val restRepository: RestRepository
//) :
//    MediatorUseCase<FetchGenreUseCase.Params, List<BaseObj>>() {
//
//    override fun execute(params: Params) {
//        result.addSource(restRepository.fetchTop(params.type, params.subtype, params.page))
//    }
//
//    class Params(val type: String, val subtype: String, val page: Int)
//}