package paetow.interpolation

import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame


/**
 * Created by Rolf on 24.06.2017.
 */

fun main(args : Array<String>) {

    val f = JFrame("Result")

    f.addWindowListener(object : WindowAdapter() {
        override fun windowClosing(e: WindowEvent?) {
            System.exit(0)
        }
    })
 //   val pixelGrid = Array(8){ Array(8) { generateRandomPixel()} }
    val pixelGrid = calculateGridWithInterpolation(generateRandomPixel(), generateRandomPixel(), generateRandomPixel(), generateRandomPixel(), 8, 8)

    val image = getImageFromArray(pixelGrid)

    val loadImageApp = ImagePresenter(image)
    f.add(loadImageApp)
    f.pack()
    f.isVisible = true
}

fun calculateGridWithInterpolation(topLeftA: Pixel, topRightB: Pixel, bottomLeftC: Pixel, bottomRightD: Pixel, width: Int, height: Int): Array<Array<Pixel?>?> {

    val interpolatedColorGrid = arrayOfNulls<Array<Pixel?>>(8)

    for (x in 0 until width) {
        val column = arrayOfNulls<Pixel>(8)
        for (y in 0 until height) {
            column[y] = Pixel(255,255,255)
        }
        interpolatedColorGrid[x] = column
    }
    return interpolatedColorGrid
}


data class Pixel(val r: Int, val g: Int, val b: Int) {

    fun toIntArray(): Array<Int> {
        return arrayOf(r,g,b)
    }
}




