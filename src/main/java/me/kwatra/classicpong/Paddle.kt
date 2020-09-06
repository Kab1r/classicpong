package me.kwatra.classicpong

import javafx.scene.canvas.GraphicsContext


class Paddle(var xPos: Double, var yPos: Double) {

    val paddleHeight = 100.0
    val paddleWidth = 15.0
    private val sensitivity = paddleHeight / 10

    fun move(direction: Double) {
        yPos += direction * sensitivity
    }

    fun moveTo(yPos: Double) {
        this.yPos = yPos
    }

    fun draw(context: GraphicsContext) {
        context.fillRect(xPos - paddleWidth, yPos, paddleWidth * 2, paddleHeight)
    }


    fun getYRange(): Double {
        return yPos + paddleHeight
    }

}