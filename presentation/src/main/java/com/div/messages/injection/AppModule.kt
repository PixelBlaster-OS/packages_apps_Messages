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

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.lifecycle.ViewModelProvider
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.div.messages.blocking.BlockingClient
import com.div.messages.blocking.BlockingManager
import com.div.messages.common.ViewModelFactory
import com.div.messages.common.util.BillingManagerImpl
import com.div.messages.common.util.NotificationManagerImpl
import com.div.messages.common.util.ShortcutManagerImpl
import com.div.messages.feature.conversationinfo.injection.ConversationInfoComponent
import com.div.messages.feature.themepicker.injection.ThemePickerComponent
import com.div.messages.listener.ContactAddedListener
import com.div.messages.listener.ContactAddedListenerImpl
import com.div.messages.manager.ActiveConversationManager
import com.div.messages.manager.ActiveConversationManagerImpl
import com.div.messages.manager.AlarmManager
import com.div.messages.manager.AlarmManagerImpl
import com.div.messages.manager.AnalyticsManager
import com.div.messages.manager.AnalyticsManagerImpl
import com.div.messages.manager.BillingManager
import com.div.messages.manager.ChangelogManager
import com.div.messages.manager.ChangelogManagerImpl
import com.div.messages.manager.KeyManager
import com.div.messages.manager.KeyManagerImpl
import com.div.messages.manager.NotificationManager
import com.div.messages.manager.PermissionManager
import com.div.messages.manager.PermissionManagerImpl
import com.div.messages.manager.RatingManager
import com.div.messages.manager.ReferralManager
import com.div.messages.manager.ReferralManagerImpl
import com.div.messages.manager.ShortcutManager
import com.div.messages.manager.WidgetManager
import com.div.messages.manager.WidgetManagerImpl
import com.div.messages.mapper.CursorToContact
import com.div.messages.mapper.CursorToContactGroup
import com.div.messages.mapper.CursorToContactGroupImpl
import com.div.messages.mapper.CursorToContactGroupMember
import com.div.messages.mapper.CursorToContactGroupMemberImpl
import com.div.messages.mapper.CursorToContactImpl
import com.div.messages.mapper.CursorToConversation
import com.div.messages.mapper.CursorToConversationImpl
import com.div.messages.mapper.CursorToMessage
import com.div.messages.mapper.CursorToMessageImpl
import com.div.messages.mapper.CursorToPart
import com.div.messages.mapper.CursorToPartImpl
import com.div.messages.mapper.CursorToRecipient
import com.div.messages.mapper.CursorToRecipientImpl
import com.div.messages.mapper.RatingManagerImpl
import com.div.messages.repository.BackupRepository
import com.div.messages.repository.BackupRepositoryImpl
import com.div.messages.repository.BlockingRepository
import com.div.messages.repository.BlockingRepositoryImpl
import com.div.messages.repository.ContactRepository
import com.div.messages.repository.ContactRepositoryImpl
import com.div.messages.repository.ConversationRepository
import com.div.messages.repository.ConversationRepositoryImpl
import com.div.messages.repository.MessageRepository
import com.div.messages.repository.MessageRepositoryImpl
import com.div.messages.repository.ScheduledMessageRepository
import com.div.messages.repository.ScheduledMessageRepositoryImpl
import com.div.messages.repository.SyncRepository
import com.div.messages.repository.SyncRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [
    ConversationInfoComponent::class,
    ThemePickerComponent::class])
class AppModule(private var application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    fun provideContentResolver(context: Context): ContentResolver = context.contentResolver

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun provideRxPreferences(preferences: SharedPreferences): RxSharedPreferences {
        return RxSharedPreferences.create(preferences)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
    }

    @Provides
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory

    // Listener

    @Provides
    fun provideContactAddedListener(listener: ContactAddedListenerImpl): ContactAddedListener = listener

    // Manager

    @Provides
    fun provideBillingManager(manager: BillingManagerImpl): BillingManager = manager

    @Provides
    fun provideActiveConversationManager(manager: ActiveConversationManagerImpl): ActiveConversationManager = manager

    @Provides
    fun provideAlarmManager(manager: AlarmManagerImpl): AlarmManager = manager

    @Provides
    fun provideAnalyticsManager(manager: AnalyticsManagerImpl): AnalyticsManager = manager

    @Provides
    fun blockingClient(manager: BlockingManager): BlockingClient = manager

    @Provides
    fun changelogManager(manager: ChangelogManagerImpl): ChangelogManager = manager

    @Provides
    fun provideKeyManager(manager: KeyManagerImpl): KeyManager = manager

    @Provides
    fun provideNotificationsManager(manager: NotificationManagerImpl): NotificationManager = manager

    @Provides
    fun providePermissionsManager(manager: PermissionManagerImpl): PermissionManager = manager

    @Provides
    fun provideRatingManager(manager: RatingManagerImpl): RatingManager = manager

    @Provides
    fun provideShortcutManager(manager: ShortcutManagerImpl): ShortcutManager = manager

    @Provides
    fun provideReferralManager(manager: ReferralManagerImpl): ReferralManager = manager

    @Provides
    fun provideWidgetManager(manager: WidgetManagerImpl): WidgetManager = manager

    // Mapper

    @Provides
    fun provideCursorToContact(mapper: CursorToContactImpl): CursorToContact = mapper

    @Provides
    fun provideCursorToContactGroup(mapper: CursorToContactGroupImpl): CursorToContactGroup = mapper

    @Provides
    fun provideCursorToContactGroupMember(mapper: CursorToContactGroupMemberImpl): CursorToContactGroupMember = mapper

    @Provides
    fun provideCursorToConversation(mapper: CursorToConversationImpl): CursorToConversation = mapper

    @Provides
    fun provideCursorToMessage(mapper: CursorToMessageImpl): CursorToMessage = mapper

    @Provides
    fun provideCursorToPart(mapper: CursorToPartImpl): CursorToPart = mapper

    @Provides
    fun provideCursorToRecipient(mapper: CursorToRecipientImpl): CursorToRecipient = mapper

    // Repository

    @Provides
    fun provideBackupRepository(repository: BackupRepositoryImpl): BackupRepository = repository

    @Provides
    fun provideBlockingRepository(repository: BlockingRepositoryImpl): BlockingRepository = repository

    @Provides
    fun provideContactRepository(repository: ContactRepositoryImpl): ContactRepository = repository

    @Provides
    fun provideConversationRepository(repository: ConversationRepositoryImpl): ConversationRepository = repository

    @Provides
    fun provideMessageRepository(repository: MessageRepositoryImpl): MessageRepository = repository

    @Provides
    fun provideScheduledMessagesRepository(repository: ScheduledMessageRepositoryImpl): ScheduledMessageRepository = repository

    @Provides
    fun provideSyncRepository(repository: SyncRepositoryImpl): SyncRepository = repository

}