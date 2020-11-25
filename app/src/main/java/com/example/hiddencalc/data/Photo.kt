package com.example.hiddencalc.data

import android.net.Uri
import java.io.Serializable

data class Photo(val photoPath: Uri?, val createDate: String): Serializable
