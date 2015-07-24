package cola.ide

import com.github.bmsantos.core.cola.story.annotations.IdeEnabler
import org.junit.Test

/**
 * Created with IntelliJ IDEA.
 * Date: 12/2/14
 * Time: 5:15 PM
 */
open class BaseColaTest() {
    @IdeEnabler
    @Test
    public fun iWillBeRemoved() {
        println("This test should have not been executed")
    }
}