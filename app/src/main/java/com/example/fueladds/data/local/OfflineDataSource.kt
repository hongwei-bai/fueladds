package com.example.fueladds.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.fueladds.R
import com.example.fueladds.data.local.OfflineFileRules.FUEL_CARD_NAME_TEMPLATE
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.InputStream
import javax.inject.Inject

class OfflineDataSource @Inject constructor(
    @ApplicationContext val appContext: Context
) {
    fun loadOfflineJson(): String {
        val inputStream: InputStream = appContext.resources.openRawResource(R.raw.offline_fallback)
        return inputStream.bufferedReader().use { it.readText() }
    }

    @SuppressLint("DiscouragedApi")
    fun loadOfflineCardImage(cardId: Int): Bitmap {
        val resourceName = String.format(FUEL_CARD_NAME_TEMPLATE, cardId)
        val resID = appContext.resources.getIdentifier(resourceName, "raw", appContext.packageName)
        val inputStream: InputStream = appContext.resources.openRawResource(resID)
        return BitmapFactory.decodeStream(inputStream)
    }
}