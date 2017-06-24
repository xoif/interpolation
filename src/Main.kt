package paetow.interpolation

import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame


/**
 * Created by Rolf on 24.06.2017.
 */

fun main(args : Array<String>) {

    val f = JFrame("Load Image Sample")

    f.addWindowListener(object : WindowAdapter() {
        override fun windowClosing(e: WindowEvent?) {
            System.exit(0)
        }
    })
    val pixelGrid = Array(8){ Array(8) { generateRandomPixel()} }
    val image = getImageFromArray(pixelGrid)

    val loadImageApp = ImagePresenter(image)
    f.add(loadImageApp)
    f.pack()
    f.isVisible = true
}


data class Pixel(val r: Int, val g: Int, val b: Int) {
    fun toIntArray(): Array<Int> {
        return arrayOf(r,g,b)
    }
}




