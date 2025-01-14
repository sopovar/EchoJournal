package ge.sopovardidze.echojournal.presentation.entries.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.core.dropShadow
import ge.sopovardidze.echojournal.ui.theme.NeutralVariant80
import ge.sopovardidze.echojournal.ui.theme.Secondary10
import ge.sopovardidze.echojournal.ui.theme.Secondary50

@Composable
fun GeneralChip(
    modifier: Modifier = Modifier,
    title: String,
    isSelected: Boolean,
    isEmpty: Boolean,
    shape: Shape = CircleShape,
    onChipClick: () -> Unit,
    onClearAll: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    val borderStrokeColor = if (isSelected) {
        Secondary50
    } else if (isEmpty.not()) {
        Transparent
    } else {
        NeutralVariant80
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(
                vertical = 2.dp,
                horizontal = 4.dp
            )
            .then(
                if (!isSelected && isEmpty) {
                    Modifier
                } else {
                    Modifier.dropShadow(
                        shape = shape,
                        blur = 12.dp,
                        offsetY = 4.dp
                    )
                }
            )
            .border(
                width = 1.dp,
                color = borderStrokeColor,
                shape = shape
            )
            .background(
                color = if (isSelected || !isEmpty) White else MaterialTheme.colorScheme.inverseOnSurface,
                shape = shape
            )
            .clip(shape = shape)
            .clickable {
                onChipClick.invoke()
            }
            .padding(vertical = 6.dp, horizontal = 12.dp)
    ) {
        if (isEmpty) {
            Text(
                text = title,
                color = Secondary10
            )
        } else {
            content()
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.ic_trailing_icon),
                contentDescription = "clearAll",
                modifier = Modifier.clickable {
                    onClearAll.invoke()
                }
            )
        }
    }
}
