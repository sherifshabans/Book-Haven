package com.elsharif.bookhaven.Models

import androidx.annotation.DrawableRes
import com.elsharif.bookhaven.enums.ToolsType

data class ToolsModel (
    val title:String,
    @DrawableRes
    val image:Int,
    val type: ToolsType
)