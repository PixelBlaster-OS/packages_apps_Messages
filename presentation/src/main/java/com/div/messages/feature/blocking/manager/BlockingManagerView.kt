package com.div.messages.feature.blocking.manager

import com.div.messages.common.base.QkViewContract
import io.reactivex.Observable
import io.reactivex.Single

interface BlockingManagerView : QkViewContract<BlockingManagerState> {

    fun activityResumed(): Observable<*>
    fun qksmsClicked(): Observable<*>
    fun callControlClicked(): Observable<*>
    fun siaClicked(): Observable<*>

    fun showCopyDialog(manager: String): Single<Boolean>

}
