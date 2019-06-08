package com.bocc.recite.kotlin.config

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.OkHttpClient
import java.io.InputStream

/**
 * Created by zouzheng on 18-3-15.
 */
@GlideModule
class GlideConfigModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        //默认DecodeFormat.PREFER_ARGB_8888
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val okHttpClient = OkHttpClient().newBuilder()
                .build()

        registry.append(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(okHttpClient))

    }
}