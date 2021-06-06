package com.saket.kotlindelegation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KProperty

/**
 * Delegation is an alternative to inheritance.
 *
 * When to use delegation instead of inheritance?
When class is final, private or not designed for inheritance
When there is no "is-a" relation between the classes
When the child does not want to inherit all the parent class' properties/methods.

Kotlin introduced class delegation via 'by'
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Liskov's substitution works here since Rectangle implements MyShape interface
/*
        val myShape : MyShape = Rectangle(2, 3)
        println("Rectangle has ${myShape.noOfSides} sides. Its area is ${myShape.area}")

        val myRect2 : Rectangle = Rectangle(4, 6)
        println("Rectangle 2 has ${myRect2.noOfSides} sides and its area is ${myRect2.area}")

        //Square implements MyShape using Rectangle as a delegate
        val square : Square = Square(4)
        println("Square has ${square.noOfSides} and its area is ${square.area}")

*/

        //Exploring standard delegates
        val standardDelegate = StandardDelegates()
        println(standardDelegate.lazyValue)
        println(standardDelegate.lazyValue)

        standardDelegate.name = "Saket"
        standardDelegate.name = "Titu"
    }
}

interface MyShape {
    val area: Int

    val noOfSides: Int
}

class Rectangle(length: Int, breadth: Int) : MyShape {
    override val area = length * breadth

    override val noOfSides = 4
}

/*
So here instead of implementing MyShape. We use Rectangle class's implementation of
MyShape as a delegate function of MyShape interface.
*/
class Square(private val side: Int) : MyShape by Rectangle(side, side) {
    //We can also override Rectangle's implementation if required....
    override val area: Int
        get() = side * 2
}


/*
Delegated properties.
Below, String val myString is a String whose is delegated by Delegate class.

For val delegate you need a class that provides at least the getValue()
For var, you need a class which provides both get and set values.
 */
class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

private val myString : String by Delegate()
