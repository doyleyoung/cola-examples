package cola.ide

import com.github.bmsantos.core.cola.story.annotations.IdeEnabler
import com.github.kittinunf.fuel.core.Manager
import org.junit.Before
import org.junit.Test

open class BaseColaTest() {

    @IdeEnabler
    @Test
    public fun iWillBeRemoved() {
        println("This test should have not been executed")
    }

    @Before
    fun init() {
        Manager.instance.basePath = "http://localhost:4567"
    }
}