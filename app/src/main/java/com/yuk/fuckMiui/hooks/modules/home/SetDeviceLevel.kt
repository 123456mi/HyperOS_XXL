package com.yuk.fuckMiui.hooks.modules.home

import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.yuk.fuckMiui.hooks.modules.BaseHook
import com.yuk.fuckMiui.utils.findClass
import com.yuk.fuckMiui.utils.getBoolean
import com.yuk.fuckMiui.utils.hookBeforeMethod
import com.yuk.fuckMiui.utils.replaceMethod

object SetDeviceLevel : BaseHook() {
    override fun init() {

        if (!getBoolean("miuihome_highend_device", false)) return
        try {
            findMethod("com.miui.home.launcher.common.CpuLevelUtils") {
                name == "getQualcommCpuLevel" && parameterCount == 1
            }
        } catch (e: Exception) {
            findMethod("miuix.animation.utils.DeviceUtils") {
                name == "getQualcommCpuLevel" && parameterCount == 1
            }
        }.hookReturnConstant(2)
        try {
            "com.miui.home.launcher.common.DeviceLevelUtils".hookBeforeMethod("getDeviceLevel") {
                it.result = 2
            }
        } catch (e: Throwable) {
            Log.ex(e)
        }
        try {
            "com.miui.home.launcher.DeviceConfig".hookBeforeMethod("isSupportCompleteAnimation") {
                it.result = true
            }
        } catch (e: Throwable) {
            Log.ex(e)
        }
        try {
            "com.miui.home.launcher.common.DeviceLevelUtils".hookBeforeMethod("isLowLevelOrLiteDevice") {
                it.result = false
            }
        } catch (e: Throwable) {
            Log.ex(e)
        }
        try {
            "com.miui.home.launcher.DeviceConfig".hookBeforeMethod("isMiuiLiteVersion") {
                it.result = false
            }
        } catch (e: Throwable) {
            Log.ex(e)
        }
        try {
            "com.miui.home.launcher.util.noword.NoWordSettingHelperKt".hookBeforeMethod("isNoWordAvailable") {
                it.result = true
            }
        } catch (e: Throwable) {
            Log.ex(e)
        }
        try {
            "android.os.SystemProperties".hookBeforeMethod(
                "getBoolean", String::class.java, Boolean::class.java
            ) {
                if (it.args[0] == "ro.config.low_ram.threshold_gb") it.result = false
            }
        } catch (e: Throwable) {
            Log.ex(e)
        }
        try {
            "android.os.SystemProperties".hookBeforeMethod(
                "getBoolean", String::class.java, Boolean::class.java
            ) {
                if (it.args[0] == "ro.miui.backdrop_sampling_enabled") it.result = true
            }
        } catch (e: Throwable) {
            Log.ex(e)
        }
        try {
            "com.miui.home.launcher.common.Utilities".hookBeforeMethod("canLockTaskView") {
                it.result = true
            }
        } catch (e: Throwable) {
            Log.ex(e)
        }
        try {
            "com.miui.home.launcher.MIUIWidgetUtil".hookBeforeMethod("isMIUIWidgetSupport") {
                it.result = true
            }
        } catch (e: Throwable) {
            Log.ex(e)
        }
        try {
            "com.miui.home.launcher.MiuiHomeLog".findClass().replaceMethod(
                "log", String::class.java, String::class.java
            ) {
                return@replaceMethod null
            }
        } catch (e: Throwable) {
            Log.ex(e)
        }
        try {
            "com.xiaomi.onetrack.OneTrack".hookBeforeMethod("isDisable") {
                it.result = true
            }
        } catch (e: Throwable) {
            Log.ex(e)
        }
    }

}
