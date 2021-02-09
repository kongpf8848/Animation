package com.github.kongpf8848.animation.utils

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class ShakeListener(private val mContext: Context) : SensorEventListener {

    private var sensorManager: SensorManager? = null
    private var sensor: Sensor? = null
    private var onShakeListener: OnShakeListener? = null
    private var lastX = 0f
    private var lastY = 0f
    private var lastZ = 0f
    private var lastUpdateTime: Long = 0L

    init {
        sensorManager = mContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        start()
    }

    fun start() {
        if (sensorManager != null) {
            sensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        }
        if (sensor != null) {
            sensorManager?.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    fun stop() {
        sensorManager?.unregisterListener(this)
    }

    fun setOnShakeListener(listener: OnShakeListener) {
        onShakeListener = listener
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent) {
        val currentUpdateTime = System.currentTimeMillis()
        val timeInterval = currentUpdateTime - lastUpdateTime
        if (timeInterval < UPTATE_INTERVAL_TIME) return
        lastUpdateTime = currentUpdateTime
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]
        val deltaX = x - lastX
        val deltaY = y - lastY
        val deltaZ = z - lastZ
        lastX = x
        lastY = y
        lastZ = z
        val speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY + (deltaZ * deltaZ).toDouble())/ timeInterval * 10000
        if (speed >= SPEED_SHRESHOLD) {
            onShakeListener?.onShake()
        }
    }

    companion object {
        private const val SPEED_SHRESHOLD = 3000
        private const val UPTATE_INTERVAL_TIME = 70
    }

    interface OnShakeListener {
        fun onShake()
    }

}