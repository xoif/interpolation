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
    val pixelGrid = PixelGrid(generateRandomPixel(), generateRandomPixel(), generateRandomPixel(), generateRandomPixel(), 128, 128)

    val image = getImageFromArray(pixelGrid.interpolatedColorGrid)

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


data class PixelGrid(val topLeftA: Pixel, val topRightB: Pixel, val bottomLeftC: Pixel, val bottomRightD: Pixel, val height: Int, val width: Int) {

    val interpolatedColorGrid = arrayOfNulls<Array<Pixel?>>(width)

    init {
        for (x in 0 until width) {
            val column = arrayOfNulls<Pixel>(height)
            for (y in 0 until height) {
                //don't calculate Pixel color for already existing edge Pixels
                if (x == 0 && y == 0) {column[y] = topLeftA;}
                else if (x == 0 && y == height - 1) {column[y] = bottomLeftC;}
                else if (x == width - 1 && y == 0) {column[y] = topRightB;}
                else if (x == width - 1 && y == height -1 ) {column[y] = bottomRightD;}
                else {
                    val red = interpolatePixelColor(topLeftA.r, topRightB.r, bottomLeftC.r, bottomRightD.r, x, y)
                    val green = interpolatePixelColor(topLeftA.g, topRightB.g, bottomLeftC.g, bottomRightD.g, x, y)
                    val blue = interpolatePixelColor(topLeftA.b, topRightB.b, bottomLeftC.b, bottomRightD.b, x, y)
                    column[y] = Pixel(red, green, blue)
                }
            }
            interpolatedColorGrid[x] = column
        }
    }

    fun interpolatePixelColor(a: Int, b: Int, c: Int, d: Int, x: Int, y: Int): Int {
        return ((width - x) * (height - y) * a + x * (height -y) * b + (width - x) * y * c + x * y * d + (2* (width + height)) / (width * height))
    }
}




