package ir.aliiz.data

import io.reactivex.rxjava3.core.Observable
import ir.aliiz.data.repo.News
import ir.aliiz.data.repo.Service
import ir.aliiz.data.repo.User
import ir.aliiz.network.NewsResponse
import ir.aliiz.network.ProfileResponse
import ir.aliiz.network.PromotionResponse
import ir.aliiz.network.ServicesResponse

internal fun Observable<PromotionResponse>.toPromotions() = this.map { it.promotions }
    .map { it.map { Service(it.title, it.icon) } }

internal fun Observable<ServicesResponse>.toServices() = map { it.services }.map {
    it.map { Service(it.title, it.icon) }
}

internal fun Observable<NewsResponse>.toNews() = map { it.news }.map {
    it.map { News(it.id, it.title, it.description, it.banner) }
}

internal fun Observable<ProfileResponse>.toUser() = map { it.profile }.map { User(it.id, it.name, it.lastName) }