package com.korolev.test.common

import com.korolev.domain.home.model.BinInfo

interface BaseSideEffect

data class IsNotHomeDataNetwork(var errorMessage: String) : BaseSideEffect
data class IsHomeDataNetwork(var data: Long) : BaseSideEffect