package com.bronski.compose.recorder

import java.io.File

interface AudioRecorder {

    fun start(outputFile: File)

    fun stop()
}