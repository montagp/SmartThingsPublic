/**
 *  Improved Smart Nightlight with Dimming
 *
 *  Author: Paul Montag
 *	Date: 2014/01/23
 *
 *  I recommend creating a virtual device... and On/Off Button Tile that you can use to turn on or off this App.
 */

// Automatically generated. Make future change here.
definition(
    name: "Night Light 2",
    namespace: "montagp",
    author: "Paul Montag",
    description: "Night Light 2",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")

preferences {

	section("Control these lights on..."){
		input "lights", "capability.switch", multiple: true }
    
    section  ("Set the following dimmers on..."){ 
        input "dimmers", "capability.switchLevel", multiple: true, required:false
        input "level", "number", title: "How bright?, 0-99", required:false }

	section("Control these lights off..."){
		input "lightsoff", "capability.switch", multiple: true }
    
    section("What do I use for the on/off switch?") {
    input "powerSwitch", "capability.switch" }


section("Turning on when it's dark and there's movement...")
{ input "motionSensor", "capability.motionSensor", title: "Where?" }

section("And then off when it's light or there's been no movement for..."){
input "delayMinutes", "number", title: "Minutes?" }
}



def installed() {
	initialize()
}

def updated() {
    unsubscribe()
    unschedule()
    initialize()
}

def initialize() {
	//subsribe to motion sensor events so we know when it turns on
    subscribe(motionSensor, "motion", motionHandler)

	//schedule a check of the motion sensor to see if it has turned off
	//schedule("0 * * * * ?", scheduleCheck)

}


def motionHandler(evt) {
    log.debug "$evt.name: $evt.value"
    if (evt.value == "active") {
    			log.debug "$powerSwitch.currentSwitch"
		        //only turn lights on if defined PowerSwitch/Night Light is ON
                if (powerSwitch.currentSwitch == "on") {
                    log.debug "turning on lights due to motion"
                    settings.dimmers?.setLevel(level)
                    lights.on()
                    state.lastStatus = "on"
                    state.dimmers = "on"
                }
                else {
                    log.debug "Control switch is off, not turning on light."
                }
	    	state.motionStopTime = null
    	}        
    	else {
        
        //only turn lights off if defined PowerSwitch/Night Light is ON
        if (powerSwitch.currentSwitch == "on")
        	{
			   runIn(delayMinutes * 60, scheduleCheck, [overwrite: false])
               state.lastStatus = "off"
               state.dimmers = "off"
            }

		state.motionStopTime = now()
        }
}

def scheduleCheck() {


	log.debug "schedule check"
	def motionState = motionSensor.currentState("motion")
    if (motionState.value == "inactive") {
        def elapsed = now() - motionState.rawDateCreated.time
    	def threshold = 1000 * 60 * delayMinutes - 1000
    	if (elapsed >= threshold) {
            log.debug "Motion has stayed inactive long enough since last check ($elapsed ms):  turning lights off"
            lightsoff.off()
    	} else {
        	log.debug "Motion has not stayed inactive long enough since last check ($elapsed ms):  doing nothing"
        }
    } else {
    	log.debug "Motion is active, do nothing and wait for inactive"
    }


	//turn off switch if motion sensor has been off for the specified delay time
	//if (state.motionStopTime && state.lastStatus != "off") {
    //    def elapsed = now() - state.motionStopTime
    //    if (elapsed >= (delayMinutes ?: 0) * 60000L) {
    //        if (powerSwitch.currentSwitch == "on") {
    //            lights.off()
    //            state.lastStatus = "off"
    //            state.dimmers= "off"
    //        }
    //    }
    // }
    
}


