package com.farimarwat.krossuidemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform