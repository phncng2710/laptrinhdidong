package com.example.baitap

//3.1: Xác định 1 lớp
class SmartDevice {
    // empty body
}

fun main() {
}

//3.2: Tạo một thực thể của lớp
fun main() {
    val smartTvDevice = SmartDevice()
}
//3.3: Xác định các phương thức của lớp
class SmartDevice {
    fun turnOn() {
        println("Smart device is turned on.")
    }

    fun turnOff() {
        println("Smart device is turned off.")
    }
}
fun main() {
    val smartTvDevice = SmartDevice()
    smartTvDevice.turnOn()
    smartTvDevice.turnOff()
}
//3.4
class SmartDevice {

    val name = "Android TV"
    val category = "Entertainment"
    var deviceStatus = "online"

    fun turnOn() {
        println("Smart device is turned on.")
    }

    fun turnOff() {
        println("Smart device is turned off.")
    }
}
fun main() {
    val smartTvDevice = SmartDevice()
    println("Device name is: ${smartTvDevice.name}")
    smartTvDevice.turnOn()
    smartTvDevice.turnOff()
}
//3.5
var speakerVolume = 2
    get() = field
    set(value) {
        field = value
    }
var speakerVolume = 2
    set(value) {
        field = value
    }
var speakerVolume = 2
    set(value) {
        if (value in 0..100) {
            field = value
        }
    }
//3.6
class SmartDevice(val name: String, val category: String) {
    var deviceStatus = "online"

    constructor(name: String, category: String, statusCode: Int) : this(name, category) {
        deviceStatus = when (statusCode) {
            0 -> "offline"
            1 -> "online"
            else -> "unknown"
        }
    }
    ...
}
//3.7
class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {

    var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
}
//3.8
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice(val name: String, val category: String) {

    var deviceStatus = "online"
        protected set

    open val deviceType = "unknown"

    open fun turnOn() {
        deviceStatus = "on"
    }

    open fun turnOff() {
        deviceStatus = "off"
    }
}

class SmartTvDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart TV"

    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

    fun increaseSpeakerVolume() {
        speakerVolume++
        println("Speaker volume increased to $speakerVolume.")
    }

    fun nextChannel() {
        channelNumber++
        println("Channel number increased to $channelNumber.")
    }

    override fun turnOn() {
        super.turnOn()
        println(
            "$name is turned on. Speaker volume is set to $speakerVolume and channel number is " +
                    "set to $channelNumber."
        )
    }

    override fun turnOff() {
        super.turnOff()
        println("$name turned off")
    }
}

class SmartLightDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart Light"

    private var brightnessLevel by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)

    fun increaseBrightness() {
        brightnessLevel++
        println("Brightness increased to $brightnessLevel.")
    }

    override fun turnOn() {
        super.turnOn()
        brightnessLevel = 2
        println("$name turned on. The brightness level is $brightnessLevel.")
    }

    override fun turnOff() {
        super.turnOff()
        brightnessLevel = 0
        println("Smart Light turned off")
    }
}

class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice
) {

    var deviceTurnOnCount = 0
        private set

    fun turnOnTv() {
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }

    fun turnOffTv() {
        deviceTurnOnCount--
        smartTvDevice.turnOff()
    }

    fun increaseTvVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }

    fun changeTvChannelToNext() {
        smartTvDevice.nextChannel()
    }

    fun turnOnLight() {
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }

    fun turnOffLight() {
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }

    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {

    var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
}

fun main() {
    var smartDevice: SmartDevice = SmartTvDevice("Android TV", "Entertainment")
    smartDevice.turnOn()

    smartDevice = SmartLightDevice("Google Light", "Utility")
    smartDevice.turnOn()
}

//3.9
// Lớp cha
open class SmartDevice(
    val name: String,
    val category: String,
    val deviceType: String
) {
    var deviceStatus: String = "off" // mặc định tắt
    companion object {
        var deviceTurnOnCount: Int = 0
    }

    open fun turnOn() {
        if (deviceStatus != "on") {
            deviceStatus = "on"
            deviceTurnOnCount++
        }
    }

    open fun turnOff() {
        if (deviceStatus == "on") {
            deviceStatus = "off"
            deviceTurnOnCount--
        }
    }

    fun printDeviceInfo() {
        println("Device name: $name, category: $category, type: $deviceType")
    }
}

// Lớp TV
class SmartTvDevice(
    name: String,
    category: String
) : SmartDevice(name, category, "TV") {

    private var volume: Int = 50
    private var channel: Int = 1

    fun increaseVolume() {
        if (deviceStatus == "on" && volume < 100) {
            volume++
            println("TV volume increased to $volume")
        }
    }

    fun decreaseVolume() {
        if (deviceStatus == "on" && volume > 0) {
            volume--
            println("TV volume decreased to $volume")
        }
    }

    fun nextChannel() {
        if (deviceStatus == "on") {
            channel++
            println("TV channel changed to $channel")
        }
    }

    fun previousChannel() {
        if (deviceStatus == "on" && channel > 1) {
            channel--
            println("TV channel changed to $channel")
        }
    }

    fun printTvStatus() {
        println("TV status: $deviceStatus, volume: $volume, channel: $channel")
    }
}

// Lớp Light
class SmartLightDevice(
    name: String,
    category: String
) : SmartDevice(name, category, "Light") {

    private var brightness: Int = 50

    fun increaseBrightness() {
        if (deviceStatus == "on" && brightness < 100) {
            brightness++
            println("Light brightness increased to $brightness")
        }
    }

    fun decreaseBrightness() {
        if (deviceStatus == "on" && brightness > 0) {
            brightness--
            println("Light brightness decreased to $brightness")
        }
    }

    fun printLightStatus() {
        println("Light status: $deviceStatus, brightness: $brightness")
    }
}

// Lớp SmartHome
class SmartHome(
    private val tv: SmartTvDevice,
    private val light: SmartLightDevice
) {
    fun decreaseTvVolume() {
        if (tv.deviceStatus == "on") {
            tv.decreaseVolume()
        } else {
            println("Cannot decrease volume. TV is off.")
        }
    }

    fun changeTvChannelToPrevious() {
        if (tv.deviceStatus == "on") {
            tv.previousChannel()
        } else {
            println("Cannot change channel. TV is off.")
        }
    }

    fun printSmartTvInfo() {
        tv.printDeviceInfo()
        tv.printTvStatus()
    }

    fun printSmartLightInfo() {
        light.printDeviceInfo()
        light.printLightStatus()
    }

    fun decreaseLightBrightness() {
        if (light.deviceStatus == "on") {
            light.decreaseBrightness()
        } else {
            println("Cannot decrease brightness. Light is off.")
        }
    }
}

// Hàm main() để kiểm thử
fun main() {
    val tv = SmartTvDevice("Living Room TV", "Entertainment")
    val light = SmartLightDevice("Bedroom Light", "Lighting")
    val smartHome = SmartHome(tv, light)

    // Bật TV và Light
    tv.turnOn()
    light.turnOn()

    // Kiểm thử TV
    smartHome.printSmartTvInfo()
    smartHome.decreaseTvVolume()
    smartHome.changeTvChannelToPrevious()

    // Kiểm thử Light
    smartHome.printSmartLightInfo()
    smartHome.decreaseLightBrightness()

    println("Devices currently ON: ${SmartDevice.deviceTurnOnCount}")
}

