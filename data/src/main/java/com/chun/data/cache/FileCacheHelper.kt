package com.chun.data.cache

import android.content.Context
import android.text.TextUtils
import com.chun.domain.model.AppInfo
import java.io.*
import javax.inject.Inject

class FileCacheHelper @Inject constructor(private val appContext: Context, private val appInfo: AppInfo) {

    private fun getHeader(): String {
        return "${appInfo.version}"
    }

    fun putCache(name: String, data: String?) {
        if (data == null) {
            clearCache(name)
        } else {
            save(makeNewFile(name) ?: return, data)
        }
    }

    fun clearCache(name: String) {
        val file = File(appContext.cacheDir, name)
        if (file.exists()) {
            file.delete()
        }
    }

    fun getCache(name: String): String? {
        val file = File(appContext.cacheDir, name)
        return when {
            file.exists() -> get(file)
            else -> null
        }
    }

    private fun makeNewFile(name: String): File? {
        val file = File(appContext.cacheDir, name)
        if (file.exists()) return file
        return try {
            file.createNewFile()
            file
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun save(file: File, text: String) {
        var bw: BufferedWriter? = null
        try {
            bw = BufferedWriter(OutputStreamWriter(FileOutputStream(file)))
            bw.write(getHeader())
            bw.newLine()
            bw.write(text)
            bw.newLine()
            bw.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                bw?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun get(file: File): String? {
        var br: BufferedReader? = null
        try {
            br = BufferedReader(InputStreamReader(FileInputStream(file)))
            if (TextUtils.equals(br.readLine(), appInfo.version.toString())) {
                return br.readLine()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: OutOfMemoryError) {
            e.printStackTrace()
        } finally {
            try {
                br?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return null
    }
}