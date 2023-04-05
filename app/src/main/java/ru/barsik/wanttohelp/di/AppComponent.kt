package ru.barsik.wanttohelp.di

import dagger.Component

@Component(modules = [DataModule::class, DomainModule::class, AppModule::class])
interface AppComponent {
}