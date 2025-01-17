package ge.sopovardidze.echojournal.presentation.records.components.comp_playground

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TimelineNode(
    circleParameters: CircleParameters,
    lineParameters: LineParameters?,
    contentStartOffset: Dp = 16.dp,
    spacerBetweenNodes: Dp = 32.dp,
    iconSize: Dp? = 32.dp,
    content: @Composable BoxScope.(modifier: Modifier) -> Unit
) {
    val iconPainter = circleParameters.icon?.let { painterResource(id = it) }
    Box(
        modifier = Modifier
            .wrapContentSize()
            .drawBehind {
                val circleRadiusInPx = circleParameters.radius.toPx()

                lineParameters?.let {
                    drawLine(
                        brush = it.brush,
                        start = Offset(x = circleRadiusInPx, y = circleRadiusInPx * 2),
                        end = Offset(x = circleRadiusInPx, y = this.size.height),
                        strokeWidth = it.strokeWidth.toPx()
                    )
                }

                iconPainter?.let { painter ->
                    val sizeInPx = iconSize?.toPx() ?: painter.intrinsicSize.width
                    this.withTransform(
                        transformBlock = {
                            translate(
                                left = circleRadiusInPx - sizeInPx / 2f,
                                top = circleRadiusInPx - sizeInPx / 2f
                            )
                        },
                        drawBlock = {
                            this.drawIntoCanvas {
                                with(painter) {
                                    draw(Size(sizeInPx, sizeInPx))
                                }
                            }
                        })
                }
            }
    ) {
        content(
            Modifier
                .defaultMinSize(minHeight = circleParameters.radius * 2)
                .padding(
                    start = circleParameters.radius * 2 + contentStartOffset,
                    bottom = spacerBetweenNodes
                )
        )
    }
}