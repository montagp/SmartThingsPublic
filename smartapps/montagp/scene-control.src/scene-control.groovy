definition(
    name: "Scene Control",
    namespace: "montagp",
    author: "Paul Montag",
    description: "This app sets a lighting scene for 1 to 5 groups of your HUE light switches to specific colors / levels, and/or turn 1 to 5 sets of switches on / off.",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png"
)

preferences {
	section("Select Switch to monitor"){
		input "theSwitch", "capability.switch"
	}
	section("Which HUE light(s) to put into Group 1:") {
		input "group1", "capability.colorControl", multiple: true, required: false
	}
	section("Group 1's detailed light settings") {
        input ("group1Lvl", "integer",title: "Level", required: false, multiple: false)  
        input ("group1Color", "enum", multiple: false, title: "Color (Default is Soft White)", required: false, metadata:[values:["Soft White","White","Daylight","Warm White","Red","Green","Blue","Yellow","Orange","Purple","Pink"]])
    }
	section("Which HUE light(s) to put into Group 2:") {
		input "group2", "capability.colorControl", multiple: true, required: false
	}
	section("Group 2's detailed light settings") {
        input ("group2Lvl", "integer",title: "Level", required: false, multiple: false)  
        input ("group2Color", "enum", multiple: false, title: "Color (Default is Soft White)", required: false, metadata:[values:["Soft White","White","Daylight","Warm White","Red","Green","Blue","Yellow","Orange","Purple","Pink"]])

    }

	section("Which HUE light(s) to put into Group 3:") {
		input "group3", "capability.colorControl", multiple: true, required: false
	}
	section("Group 3's detailed light settings") {
        input ("group3Lvl", "integer",title: "Level", required: false, multiple: false)  
        input ("group3Color", "enum", multiple: false, title: "Color (Default is Soft White)", required: false, metadata:[values:["Soft White","White","Daylight","Warm White","Red","Green","Blue","Yellow","Orange","Purple","Pink"]])

    }


	section("Which HUE light(s) to put into Group 4:") {
		input "group4", "capability.colorControl", multiple: true, required: false
	}
	section("Group 4's detailed light settings") {
        input ("group4Lvl", "integer",title: "Level", required: false, multiple: false)  
        input ("group4Color", "enum", multiple: false, title: "Color (Default is Soft White)", required: false, metadata:[values:["Soft White","White","Daylight","Warm White","Red","Green","Blue","Yellow","Orange","Purple","Pink"]])

    }

	section("Which HUE light(s) to put into Group 5:") {
		input "group5", "capability.colorControl", multiple: true, required: false
	}
	section("Group 5's detailed light settings") {
        input ("group5Lvl", "integer",title: "Level", required: false, multiple: false)  
        input ("group5Color", "enum", multiple: false, title: "Color (Default is Soft White)", required: false, metadata:[values:["Soft White","White","Daylight","Warm White","Red","Green","Blue","Yellow","Orange","Purple","Pink"]])

    }

    section  ("Dimmer Group 1's light settings..."){ 
        input "dimmers1", "capability.switchLevel", multiple: true, required:false
        input "dimmerslevel1", "number", title: "How bright?, 0-99", required:false }

    section  ("Dimmer Group 2's light settings..."){ 
        input "dimmers2", "capability.switchLevel", multiple: true, required:false
        input "dimmerslevel2", "number", title: "How bright?, 0-99", required:false }


	section("Turn ON these lights..."){
		input "switch1", "capability.switch", multiple: true, required: false
	}
    
    
    section("Turn OFF these lights..."){
		input "switch2", "capability.switch", multiple: true, required: false
	}
}

def installed()
{

    initialize()
}

def updated() {

	unsubscribe()
    initialize()

}


def initialize() {
	//subscribe(app, appTouch)
	subscribe(theSwitch, "switch", onHandler)

    colorSelection1()
    colorSelection2()
    colorSelection3()
    colorSelection4()
    colorSelection5()

}



def onHandler(evt) {
	log.info evt.value
    

    
    if (evt.value == "on") {   

            if(switch2){
                switch2.off()
                switch2.off()
                switch2.off()
            }


            if(switch1){
                switch1.on()
            }
		
		  
          if(dimmers1)
          {
          settings.dimmers1?.setLevel(dimmerslevel1)
          }

          if(dimmers2)
          {
          settings.dimmers2?.setLevel(dimmerslevel2)
          }

			// TURN ON LIGHTS AND SET ATTRIBUTES, COMMANDS ARE REPEATED FOR RELIABILITY REASONS

            if (group1){
                def myLightLevel = state.lightLevel1 as Integer	
                def myhueColor = state.hueColor1 as Integer
                def mySat = state.saturation1 as Integer
                def newValue = [hue: myhueColor, saturation: mySat, level: myLightLevel]

                group1.each {    
                    it.setColor(newValue)       
                }

            }

            if (group2){
                def myLightLevel = state.lightLevel2 as Integer	
                def myhueColor = state.hueColor2 as Integer
                def mySat = state.saturation2 as Integer
                def newValue = [hue: myhueColor, saturation: mySat, level: myLightLevel]

                group2.each {    
                    it.setColor(newValue)       
                    }

            }

            if (group3){
                def myLightLevel = state.lightLevel3 as Integer	
                def myhueColor = state.hueColor3 as Integer
                def mySat = state.saturation3 as Integer
                def newValue = [hue: myhueColor, saturation: mySat, level: myLightLevel]

                group3.each {    
                    it.setColor(newValue)       
                    }


            }

            if (group4){
                def myLightLevel = state.lightLevel4 as Integer	
                def myhueColor = state.hueColor4 as Integer
                def mySat = state.saturation4 as Integer
                def newValue = [hue: myhueColor, saturation: mySat, level: myLightLevel]

                group4.each {    
                    it.setColor(newValue)       
                }
            }

            if (group5){
                def myLightLevel = state.lightLevel5 as Integer	
                def myhueColor = state.hueColor5 as Integer
                def mySat = state.saturation5 as Integer
                def newValue = [hue: myhueColor, saturation: mySat, level: myLightLevel]

                group5.each {    
                    it.setColor(newValue)       
                }
            }

            if (group1){
                def myLightLevel = state.lightLevel1 as Integer	
                def myhueColor = state.hueColor1 as Integer
                def mySat = state.saturation1 as Integer
                def newValue = [hue: myhueColor, saturation: mySat, level: myLightLevel]

                group1.each {    
                    it.setColor(newValue)       
                }

            }

            if (group2){
                def myLightLevel = state.lightLevel2 as Integer	
                def myhueColor = state.hueColor2 as Integer
                def mySat = state.saturation2 as Integer
                def newValue = [hue: myhueColor, saturation: mySat, level: myLightLevel]

                group2.each {    
                    it.setColor(newValue)       
                    }

            }

            if (group3){
                def myLightLevel = state.lightLevel3 as Integer	
                def myhueColor = state.hueColor3 as Integer
                def mySat = state.saturation3 as Integer
                def newValue = [hue: myhueColor, saturation: mySat, level: myLightLevel]

                group3.each {    
                    it.setColor(newValue)       
                    }


            }

            if (group4){
                def myLightLevel = state.lightLevel4 as Integer	
                def myhueColor = state.hueColor4 as Integer
                def mySat = state.saturation4 as Integer
                def newValue = [hue: myhueColor, saturation: mySat, level: myLightLevel]

                group4.each {    
                    it.setColor(newValue)       
                }
            }

            if (group5){
                def myLightLevel = state.lightLevel5 as Integer	
                def myhueColor = state.hueColor5 as Integer
                def mySat = state.saturation5 as Integer
                def newValue = [hue: myhueColor, saturation: mySat, level: myLightLevel]

                group5.each {    
                    it.setColor(newValue)       
                }
            }

            if (group1){
                def myLightLevel = state.lightLevel1 as Integer	
                def myhueColor = state.hueColor1 as Integer
                def mySat = state.saturation1 as Integer
                def newValue = [hue: myhueColor, saturation: mySat, level: myLightLevel]

                group1.each {    
                    it.setColor(newValue)       
                }

            }

            if (group2){
                def myLightLevel = state.lightLevel2 as Integer	
                def myhueColor = state.hueColor2 as Integer
                def mySat = state.saturation2 as Integer
                def newValue = [hue: myhueColor, saturation: mySat, level: myLightLevel]

                group2.each {    
                    it.setColor(newValue)       
                    }

            }

            if (group3){
                def myLightLevel = state.lightLevel3 as Integer	
                def myhueColor = state.hueColor3 as Integer
                def mySat = state.saturation3 as Integer
                def newValue = [hue: myhueColor, saturation: mySat, level: myLightLevel]

                group3.each {    
                    it.setColor(newValue)       
                    }


            }

            if (group4){
                def myLightLevel = state.lightLevel4 as Integer	
                def myhueColor = state.hueColor4 as Integer
                def mySat = state.saturation4 as Integer
                def newValue = [hue: myhueColor, saturation: mySat, level: myLightLevel]

                group4.each {    
                    it.setColor(newValue)       
                }
            }

            if (group5){
                def myLightLevel = state.lightLevel5 as Integer	
                def myhueColor = state.hueColor5 as Integer
                def mySat = state.saturation5 as Integer
                def newValue = [hue: myhueColor, saturation: mySat, level: myLightLevel]

                group5.each {    
                    it.setColor(newValue)       
                }
            }

 


		}
        
        else if (evt.value == "off")
        
        {
    	    //switch was turned off
 
			  //TURN OFF all the lights, repeating off commands to ensure reliability
              
           /*   if (group1) { group1.off() }
              if (group2) { group2.off() }
              if (group3) { group3.off() }
              if (group4) { group4.off() }
              if (group5) { group5.off() }

              if (group1) { group1.off()  }
              if (group2) { group2.off()  }
              if (group3) { group3.off()  }
              if (group4) { group4.off()  }
              if (group5) { group5.off()  } 
 
              if (group1) { group1.off()  }
              if (group2) { group2.off()  }
              if (group3) { group3.off()  }
              if (group4) { group4.off()  }
              if (group5) { group5.off()  }  */
 
 
        }


}




def colorSelection5() {
	state.lightLevel5 = group5Lvl
	state.hueColor5 = 23
	state.saturation5 = 56

	switch(group5Color) {
		case "White":
			state.hueColor5 = 52
			state.saturation5 = 19
			break;
		case "Daylight":
			state.hueColor5 = 53
			state.saturation5 = 91
			break;
		case "Soft White":
			state.hueColor5 = 23
			state.saturation5 = 56
			break;
		case "Warm White":
			state.hueColor5 = 20
			state.saturation5 = 80 //83
			break;
		case "Blue":
			state.hueColor5 = 70
			state.saturation5 = 100            
			break;
		case "Green":
			state.hueColor5 = 39
            state.saturation5 = 100
			break;
		case "Yellow":
			state.hueColor5 = 25
            state.saturation5 = 100
			break;
		case "Orange":
			state.hueColor5 = 10
            state.saturation5 = 100
			break;
		case "Purple":
			state.hueColor5 = 75
            state.saturation5 = 100
			break;
		case "Pink":
			state.hueColor5 = 83
            state.saturation5 = 100
			break;
		case "Red":
			state.hueColor5 = 100
            state.saturation5 = 100
			break;

	}
}



def colorSelection4() {
	state.lightLevel4 = group4Lvl
	state.hueColor4 = 23
	state.saturation4 = 56

	switch(group4Color) {
		case "White":
			state.hueColor4 = 52
			state.saturation4 = 19
			break;
		case "Daylight":
			state.hueColor4 = 53
			state.saturation4 = 91
			break;
		case "Soft White":
			state.hueColor4 = 23
			state.saturation4 = 56
			break;
		case "Warm White":
			state.hueColor4 = 20
			state.saturation4 = 80 //83
			break;
		case "Blue":
			state.hueColor4 = 70
			state.saturation4 = 100            
			break;
		case "Green":
			state.hueColor4 = 39
            state.saturation4 = 100
			break;
		case "Yellow":
			state.hueColor4 = 25
            state.saturation4 = 100
			break;
		case "Orange":
			state.hueColor4 = 10
            state.saturation4 = 100
			break;
		case "Purple":
			state.hueColor4 = 75
            state.saturation4 = 100
			break;
		case "Pink":
			state.hueColor4 = 83
            state.saturation4 = 100
			break;
		case "Red":
			state.hueColor4 = 100
            state.saturation4 = 100
			break;

	}
}


def colorSelection3() {
	state.lightLevel3 = group3Lvl
	state.hueColor3 = 23
	state.saturation3 = 56

	switch(group3Color) {
		case "White":
			state.hueColor3 = 52
			state.saturation3 = 19
			break;
		case "Daylight":
			state.hueColor3 = 53
			state.saturation3 = 91
			break;
		case "Soft White":
			state.hueColor3 = 23
			state.saturation3 = 56
			break;
		case "Warm White":
			state.hueColor3 = 20
			state.saturation3 = 80 //83
			break;
		case "Blue":
			state.hueColor3 = 70
			state.saturation3 = 100            
			break;
		case "Green":
			state.hueColor3 = 39
            state.saturation3 = 100
			break;
		case "Yellow":
			state.hueColor3 = 25
            state.saturation3 = 100
			break;
		case "Orange":
			state.hueColor3 = 10
            state.saturation3 = 100
			break;
		case "Purple":
			state.hueColor3 = 75
            state.saturation3 = 100
			break;
		case "Pink":
			state.hueColor3 = 83
            state.saturation3 = 100
			break;
		case "Red":
			state.hueColor3 = 100
            state.saturation3 = 100
			break;

	}
}



def colorSelection2() {
	state.lightLevel2 = group2Lvl
	state.hueColor2 = 23
	state.saturation2 = 56

	switch(group2Color) {
		case "White":
			state.hueColor2 = 52
			state.saturation2 = 19
			break;
		case "Daylight":
			state.hueColor2 = 53
			state.saturation2 = 91
			break;
		case "Soft White":
			state.hueColor2 = 23
			state.saturation2 = 56
			break;
		case "Warm White":
			state.hueColor2 = 20
			state.saturation2 = 80 //83
			break;
		case "Blue":
			state.hueColor2 = 70
			state.saturation2 = 100            
			break;
		case "Green":
			state.hueColor2 = 39
            state.saturation2 = 100
			break;
		case "Yellow":
			state.hueColor2 = 25
            state.saturation2 = 100
			break;
		case "Orange":
			state.hueColor2 = 10
            state.saturation2 = 100
			break;
		case "Purple":
			state.hueColor2 = 75
            state.saturation2 = 100
			break;
		case "Pink":
			state.hueColor2 = 83
            state.saturation2 = 100
			break;
		case "Red":
			state.hueColor2 = 100
            state.saturation2 = 100
			break;

	}
}

def colorSelection1() {
	state.lightLevel1 = group1Lvl 
	state.hueColor1 = 23
	state.saturation1 = 56

	switch(group1Color) {
		case "White":
			state.hueColor1 = 52
			state.saturation1 = 19
			break;
		case "Daylight":
			state.hueColor1 = 53
			state.saturation1 = 91
			break;
		case "Soft White":
			state.hueColor1 = 23
			state.saturation1 = 56
			break;
		case "Warm White":
			state.hueColor1 = 20
			state.saturation1 = 80 //83
			break;
		case "Blue":
			state.hueColor1 = 70
			state.saturation1 = 100
            break;
		case "Green":
			state.hueColor1 = 39
			state.saturation1 = 100
            break;
		case "Yellow":
			state.hueColor1 = 25
			state.saturation1 = 100			
            break;
		case "Orange":
			state.hueColor1 = 10
			state.saturation1 = 100
            break;
		case "Purple":
			state.hueColor1 = 75
			state.saturation1 = 100
            break;
		case "Pink":
			state.hueColor1 = 83
			state.saturation1 = 100
            break;
		case "Red":
			state.hueColor1 = 100
			state.saturation1 = 100                       
			break;
	}
    
			state.lastStatus = "on"

}

