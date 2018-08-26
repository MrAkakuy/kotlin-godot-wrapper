package model

import com.beust.klaxon.Json


class Classes(
        @Json(name = "registerClasses")
        val classes: List<Class>
) {
    lateinit var bridges: MutableList<String>
    lateinit var bindings: MutableList<String>


    fun generate(): String {
        return buildString {
            appendln("""
@file:Suppress("PackageDirectoryMismatch", "FunctionName", "UNUSED_ANONYMOUS_PARAMETER")
package kotlin.godot.entry

import konan.internal.CName
import kotlinx.cinterop.*
import godot.gdnative.*
import godot.core.*
import godot.*
import godot.registration.*


// NOTE THIS FILE IS AUTO-GENERATED


@CName("godot_gdnative_init")
fun GDNativeInit(options: godot_gdnative_init_options) {
    godot_wrapper_gdnative_init(options.ptr)
}


@CName("godot_gdnative_terminate")
fun GDNativeTerminate(options: godot_gdnative_terminate_options) {
    godot_wrapper_gdnative_terminate(options.ptr)
}


@CName("godot_nativescript_init")
fun NativeScriptInit(handle: COpaquePointer) {
    godot_wrapper_nativescript_init(handle)

    // Let's do some registration magic.
            """.trimIndent())


            bindings = mutableListOf()
            bridges = mutableListOf()

            for (cl in classes) {
                appendln(cl.generate(bindings, bridges))
                append(cl.generateMethods(bridges))
                append(cl.generateProperties(bridges))
                append(cl.generateSignals())
            }
            appendln("}")
            appendln()
            appendln()
            appendln()


            appendln("// Bindings")
            for (b in bindings)
                appendln(b)
            appendln()
            appendln()
            appendln()


            appendln("// Bridges")
            for (b in bridges)
                appendln(b)
        }
    }
}


fun List<Classes>.unite(): Classes {
    val classes = mutableListOf<Class>()
    for (cls in this)
        for (cl in cls.classes) {
            if (classes.find { clazz -> clazz.name == cl.name } != null)
                error("Classes cannot duplicate! Multiple ${cl.name}")
            classes.add(cl)
        }
    return Classes(classes)
}