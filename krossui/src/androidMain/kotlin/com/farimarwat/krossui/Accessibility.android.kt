package com.farimarwat.krossui

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

/** Adds [description] as the TalkBack label when it is given. */
internal fun Modifier.labelled(description: String?): Modifier =
    if (description.isNullOrBlank()) this else semantics { contentDescription = description }
