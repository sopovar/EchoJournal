package ge.sopovardidze.echojournal.presentation.create_record.component

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.core.dropShadow
import ge.sopovardidze.echojournal.core.noRippleClickable
import ge.sopovardidze.echojournal.ui.theme.BtnTextColor
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.LightBgGray
import ge.sopovardidze.echojournal.ui.theme.NeutralVariant30
import ge.sopovardidze.echojournal.ui.theme.NeutralVariant80
import ge.sopovardidze.echojournal.ui.theme.Purple80
import ge.sopovardidze.echojournal.ui.theme.Secondary10
import ge.sopovardidze.echojournal.ui.theme.Shadow
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.delay

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TopicTagsCreator(
    modifier: Modifier = Modifier,
) {
    var newTopic by remember { mutableStateOf("") }
    var isTyping by remember { mutableStateOf(false) }
    var existingTopics by remember {
        mutableStateOf<MutableSet<String>>(
            mutableSetOf("Work", "Love", "Family", "Hobby")
        )
    }
    var selectedTopics by remember {
        mutableStateOf<Set<String>>(
            emptySet()
        )
    }
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    LaunchedEffect(isTyping) {
        if (isTyping) {
            focusRequester.requestFocus()
            awaitFrame()
            keyboard?.show()
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_tag),
                contentDescription = null,
                modifier = Modifier.padding(top = 8.dp)
            )

            selectedTopics.forEach { topic ->
                TopicHashTag(
                    text = topic,
                    onDelete = { topicToDelete ->
                        selectedTopics = selectedTopics - topicToDelete
                    }
                )
            }

            if (isTyping.not()) {
                Text(
                    text = "Topic",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = NeutralVariant80
                    ),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .noRippleClickable {
                            isTyping = true
                        }
                )
            } else {
                BasicTextField(
                    value = newTopic,
                    onValueChange = {
                        newTopic = it
                    },
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 8.dp)
                        .focusRequester(focusRequester),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            selectedTopics = selectedTopics + newTopic
                            newTopic = ""
                            isTyping = false
                            keyboard?.hide()
                        }
                    )
                )
            }
        }

        if (isTyping) {
            Box(
                modifier = Modifier
                    .offset(y = -(4.dp))
                    .fillMaxWidth()
                    .dropShadow(
                        shape = RoundedCornerShape(10.dp),
                        color = Shadow.copy(0.2f),
                        blur = 20.dp,
                        offsetY = 6.dp
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 10.dp, vertical = 6.dp)
                    .clip(shape = RoundedCornerShape(10.dp)),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    existingTopics.forEach {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .noRippleClickable {
                                    selectedTopics = selectedTopics + it
                                    isTyping = false
                                    keyboard?.hide()
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_tag),
                                contentDescription = null,
                                modifier = Modifier.padding(end = 8.dp),
                                tint = BtnTextColor
                            )

                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = Secondary10
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopicHashTag(
    modifier: Modifier = Modifier,
    text: String = "Work",
    onDelete: (String) -> Unit,
) {
    AssistChip(
        modifier = modifier
            .padding(bottom = 8.dp)
            .height(32.dp),
        onClick = {},
        label = {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        trailingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_delete),
                contentDescription = null,
                modifier = Modifier
                    .noRippleClickable {
                        onDelete.invoke(text)
                    }
            )
        },
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_tag),
                contentDescription = null
            )
        },
        shape = CircleShape,
        colors = AssistChipDefaults.assistChipColors(
            containerColor = LightBgGray,
            labelColor = NeutralVariant30,
            leadingIconContentColor = NeutralVariant30

        ),
        border = BorderStroke(width = 1.dp, color = LightBgGray)
    )
}

@Preview
@Composable
private fun TopicTagsCreatorPreview() {
    EchoJournalTheme {
        Surface {
            TopicTagsCreator()
        }
    }
}