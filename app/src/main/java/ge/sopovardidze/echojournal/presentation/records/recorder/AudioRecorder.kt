package ge.sopovardidze.echojournal.presentation.records.recorder

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
    fun pause()
    fun resume()
}