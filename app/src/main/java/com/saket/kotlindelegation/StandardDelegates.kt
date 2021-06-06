package com.saket.kotlindelegation

import kotlin.properties.Delegates

/**
 *
 */
class StandardDelegates {

    val lazyValue: String by lazy {
        println("Computed!")    //Called only first time when property is initialized...
        "Hello!"    //Value returned for this property
    }

    /*
    Delegates.observable() takes two arguments: the initial value and a handler for modifications.

The handler is called every time you assign to the property (after the assignment has been performed).
It has three parameters: a property being assigned to, the old value and the new one:
     */
    var name : String by Delegates.observable("No Name") {
        property, oldValue, newValue ->
        println("$oldValue -> $newValue")
    }

    /*
If you want to intercept assignments and veto them, use vetoable() instead of observable().
The handler passed to the vetoable is called before the assignment of a new property value.
     */
}