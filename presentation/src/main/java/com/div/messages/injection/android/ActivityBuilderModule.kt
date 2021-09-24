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
package com.div.messages.injection.android

import com.div.messages.feature.backup.BackupActivity
import com.div.messages.feature.blocking.BlockingActivity
import com.div.messages.feature.compose.ComposeActivity
import com.div.messages.feature.compose.ComposeActivityModule
import com.div.messages.feature.contacts.ContactsActivity
import com.div.messages.feature.contacts.ContactsActivityModule
import com.div.messages.feature.conversationinfo.ConversationInfoActivity
import com.div.messages.feature.gallery.GalleryActivity
import com.div.messages.feature.gallery.GalleryActivityModule
import com.div.messages.feature.main.MainActivity
import com.div.messages.feature.main.MainActivityModule
import com.div.messages.feature.notificationprefs.NotificationPrefsActivity
import com.div.messages.feature.notificationprefs.NotificationPrefsActivityModule
import com.div.messages.feature.plus.PlusActivity
import com.div.messages.feature.plus.PlusActivityModule
import com.div.messages.feature.qkreply.QkReplyActivity
import com.div.messages.feature.qkreply.QkReplyActivityModule
import com.div.messages.feature.scheduled.ScheduledActivity
import com.div.messages.feature.scheduled.ScheduledActivityModule
import com.div.messages.feature.settings.SettingsActivity
import com.div.messages.injection.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [PlusActivityModule::class])
    abstract fun bindPlusActivity(): PlusActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindBackupActivity(): BackupActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ComposeActivityModule::class])
    abstract fun bindComposeActivity(): ComposeActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ContactsActivityModule::class])
    abstract fun bindContactsActivity(): ContactsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindConversationInfoActivity(): ConversationInfoActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [GalleryActivityModule::class])
    abstract fun bindGalleryActivity(): GalleryActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [NotificationPrefsActivityModule::class])
    abstract fun bindNotificationPrefsActivity(): NotificationPrefsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [QkReplyActivityModule::class])
    abstract fun bindQkReplyActivity(): QkReplyActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ScheduledActivityModule::class])
    abstract fun bindScheduledActivity(): ScheduledActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindSettingsActivity(): SettingsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindBlockingActivity(): BlockingActivity

}
