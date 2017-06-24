package paetow.interpolation

import java.awt.Dimension
import java.awt.Graphics
import java.awt.Image
import java.awt.image.BufferedImage
import java.awt.image.WritableRaster
import java.util.*
import javax.swing.JPanel

/**
 * Created by Rolf on 24.06.2017.
 */

fun generateRandomPixel(): Pixel {
    val random = Random()
    return Pixel(random.nextInt(256),random.nextInt(256),random.nextInt(256))
}

fun getImageFromArray(pixels: Array<Array<Pixel>>): Image {
    val width = pixels.size
    val height = pixels[0].size

    var imageFlatMap = pixels.flatMap { it.flatMap { it.toIntArray().map { it } } }
    print(imageFlatMap)

    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val raster = image.data as WritableRaster

    raster.setPixels(0, 0, width, height, imageFlatMap.toIntArray())
    return image
}

class ImagePresenter(val image: Image): JPanel() {

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        g!!.drawImage(image, 0,0, width, height, null)
    }
    override fun getPreferredSize(): Dimension = Dimension(1000, 1000)
}