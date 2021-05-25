package com.example.remotejobs.di


import com.example.remotejobs.common.INetworkAwareHandler
import com.example.remotejobs.common.NetworkHandler
import com.example.remotejobs.data.repo.PositionsRepository
import com.example.remotejobs.data.source.remote_data.ApiHelperImpl
import com.example.remotejobs.data.source.remote_data.IApiHelper
import org.koin.dsl.module

val repoModule = module {


    single { PositionsRepository(get(), get(), get(),get()) }

    single<INetworkAwareHandler> { NetworkHandler(get()) }

    factory<IApiHelper> { ApiHelperImpl(get()) }


}