package kotlin.godot.core

import kotlinx.cinterop.*
import godot.*

class Dictionary: CoreType {
    var nativeValue = cValue<godot_dictionary> { godot_dictionary_new(this.ptr) }

    constructor()

    constructor(native: CValue<godot_dictionary>) {
        nativeValue = nativeValue.copy { godot_dictionary_new_copy(this.ptr, native) }
    }

    constructor(other: Dictionary) {
        nativeValue = nativeValue.copy { godot_dictionary_new_copy(this.ptr, other.nativeValue) }
    }

    override fun getRawMemory(memScope: MemScope): COpaquePointer {
        return nativeValue.getPointer(memScope)
    }

    override fun setRawMemory(mem: COpaquePointer) {
        nativeValue = mem.reinterpret<godot_dictionary>().pointed.readValue()
    }

    fun clear(){
        nativeValue = nativeValue.copy { godot_dictionary_clear(this.ptr) }
    }

    fun empty(): Boolean = godot_dictionary_empty(nativeValue)

    override fun hashCode(): Int = godot_dictionary_hash(nativeValue)

    fun size(): Int = godot_dictionary_size(nativeValue)

    fun to_json() : GodotString = GodotString(godot_dictionary_to_json(nativeValue))

    fun has_all(keys: GodotArray): Boolean = godot_dictionary_has_all(nativeValue, keys.nativeValue)

    fun keys(): GodotArray = GodotArray(godot_dictionary_keys(nativeValue))

    fun values(): GodotArray = GodotArray(godot_dictionary_values(nativeValue))

    fun erase(key: Variant) = godot_dictionary_erase(nativeValue, key.nativeValue)

    fun has(key: Variant): Boolean = godot_dictionary_has(nativeValue, key.nativeValue)

    /* I don't know will this work or not*/
    fun get(key: Variant) : Variant = Variant(godot_dictionary_operator_index(nativeValue, key.nativeValue)!!.pointed.readValue())


}