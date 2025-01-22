package ge.sopovardidze.echojournal.core

import kotlinx.serialization.*
import android.net.Uri
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(forClass = Uri::class)
object UriSerializer : KSerializer<Uri?> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Uri", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Uri? {
        val string = decoder.decodeString()
        return if (string.isEmpty()) null else Uri.parse(string)
    }

    override fun serialize(encoder: Encoder, value: Uri?) {
        encoder.encodeString(value?.toString() ?: "") 
    }
}