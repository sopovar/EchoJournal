package ge.sopovardidze.echojournal.presentation.create_record.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.ui.theme.BtnGradientEnd
import ge.sopovardidze.echojournal.ui.theme.BtnGradientStart
import ge.sopovardidze.echojournal.ui.theme.BtnTextColor
import ge.sopovardidze.echojournal.ui.theme.LightBgBlue

@Composable
fun EchoButton(
    modifier: Modifier = Modifier,
    text: String = "Sample text",
    textColor: Color = BtnTextColor,
    bgColor: Color = LightBgBlue,
    gradientStart: Color = BtnGradientStart,
    gradientEnd: Color = BtnGradientEnd,
    isEnabled: Boolean = true,
    icon: Int? = null,
) {
    Box(
        modifier = modifier
            .height(44.dp)
            .then(
                if (isEnabled) {
                    Modifier.background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                gradientStart,
                                gradientEnd
                            )
                        ),
                        shape = CircleShape
                    )
                } else {
                    Modifier.background(
                        color = bgColor,
                        shape = CircleShape
                    )
                }
            )
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row {
            if (icon != null) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = "check"
                )
            }
            Text(
                text = text,
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium.copy(color = textColor),
            )
        }
    }
}