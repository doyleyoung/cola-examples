package com.github.bmsantos.cola.kotlin.handler

import com.beust.jcommander.Parameter

class CommandLineOptions {

    @Parameter(names = arrayOf("--debug"))
    var debug: Boolean = false

    @Parameter(names = arrayOf("--servicePort"))
    var servicePort: Int = 4567
}