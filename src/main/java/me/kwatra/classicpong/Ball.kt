package me.kwatra.classicpong

import javafx.scene.canvas.GraphicsContext
import kotlin.math.atan2
import kotlin.math.hypot
import kotlin.math.sign

class Ball @JvmOverloads constructor(
    var xPos: Double,
    var yPos: Double,
    var xVelocity: Double = 1.0,
    var yVelocity: Double = 1.0
) {

    val radius = 15.0
    private val velocityIncrement = 0.005

    fun draw(context: GraphicsContext) {
        context.fillOval(xPos, yPos, radius, radius)
    }

    fun move() {
        xPos += xVelocity
        yPos += yVelocity
    }

    fun incrementSpeed() {
        xVelocity += velocityIncrement * xVelocity.sign
        yVelocity += velocityIncrement * yVelocity.sign
    }

    fun getAngle(): Double { // -PI to PI
        return atan2(yVelocity, xVelocity)
    }

    fun getVelocityMagnitude(): Double {
        return hypot(xVelocity, yVelocity)
    }
}