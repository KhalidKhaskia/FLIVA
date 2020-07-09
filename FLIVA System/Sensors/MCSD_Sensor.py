import RPi.GPIO as GPIO
import time

class MCSD:
    #
    Pin_R=23
    Pin_L=24
    #
    def __init__(self):
        #magnet_pin1 = 23
        GPIO.setmode(GPIO.BCM)
        self.magnet_pin1=self.Pin_R
        self.input_state1=None
        #magnet_pin2 = 24
        self.magnet_pin2=self.Pin_L
        self.input_state2=None
        
        GPIO.setup(self.magnet_pin1, GPIO.IN, pull_up_down=GPIO.PUD_UP) #Reed Switch on pin 23
        GPIO.setup(self.magnet_pin2, GPIO.IN, pull_up_down=GPIO.PUD_UP) #Reed Switch on pin 24        
    #
    def Check_Open_Doors(self):
        result=0
        try:        
            self.input_state1 = GPIO.input(self.magnet_pin1)
            self.input_state2 = GPIO.input(self.magnet_pin2)
            #print(self.input_state1,"\n",self.input_state2)
            result=self.input_state1+self.input_state2
            return result
        
        except KeyboardInterrupt:
            return -1
            pass
        
    
def main():
    door=MCSD()
    print(door.Check_Open_Doors())
#
if (__name__ == "__main__"):
    main()