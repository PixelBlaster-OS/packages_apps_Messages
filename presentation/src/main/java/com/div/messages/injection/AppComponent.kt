/*
 * Copyright (C) 2017 Moez Bhatti <moez.bhatti@gmail.com>
 *
 * This file is part of QKSMS.
 *
 * QKSMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * QKSMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with QKSMS.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.div.messages.injection

import com.div.messages.common.QKApplication
import com.div.messages.common.QkDialog
import com.div.messages.common.util.QkChooserTargetService
import com.div.messages.common.widget.AvatarView
import com.div.messages.common.widget.PagerTitleView
import com.div.messages.common.widget.PreferenceView
import com.div.messages.common.widget.QkEditText
import com.div.messages.common.widget.QkSwitch
import com.div.messages.common.widget.QkTextView
import com.div.messages.common.widget.RadioPreferenceView
import com.div.messages.feature.backup.BackupController
import com.div.messages.feature.blocking.BlockingController
import com.div.messages.feature.blocking.manager.BlockingManagerController
import com.div.messages.feature.blocking.messages.BlockedMessagesController
import com.div.messages.feature.blocking.numbers.BlockedNumbersController
import com.div.messages.feature.compose.editing.DetailedChipView
import com.div.messages.feature.conversationinfo.injection.ConversationInfoComponent
import com.div.messages.feature.settings.SettingsController
import com.div.messages.feature.settings.about.AboutController
import com.div.messages.feature.settings.swipe.SwipeActionsController
import com.div.messages.feature.themepicker.injection.ThemePickerComponent
import com.div.messages.feature.widget.WidgetAdapter
import com.div.messages.injection.android.ActivityBuilderModule
import com.div.messages.injection.android.BroadcastReceiverBuilderModule
import com.div.messages.injection.android.ServiceBuilderModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class,
    BroadcastReceiverBuilderModule::class,
    ServiceBuilderModule::class])
interface AppComponent {

    fun conversationInfoBuilder(): ConversationInfoComponent.Builder
    fun themePickerBuilder(): ThemePickerComponent.Builder

    fun inject(application: QKApplication)

    fun inject(controller: AboutController)
    fun inject(controller: BackupController)
    fun inject(controller: BlockedMessagesController)
    fun inject(controller: BlockedNumbersController)
    fun inject(controller: BlockingController)
    fun inject(controller: BlockingManagerController)
    fun inject(controller: SettingsController)
    fun inject(controller: SwipeActionsController)

    fun inject(dialog: QkDialog)

    fun inject(service: WidgetAdapter)

    /**
     * This can't use AndroidInjection, or else it will crash on pre-marshmallow devices
     */
    fun inject(service: QkChooserTargetService)

    fun inject(view: AvatarView)
    fun inject(view: DetailedChipView)
    fun inject(view: PagerTitleView)
    fun inject(view: PreferenceView)
    fun inject(view: RadioPreferenceView)
    fun inject(view: QkEditText)
    fun inject(view: QkSwitch)
    fun inject(view: QkTextView)

}
