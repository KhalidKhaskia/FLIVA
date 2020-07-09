import RPi.GPIO as GPIO
from gpiozero import MotionSensor
import time

"""
PINS - LOCATING
20   - right side
19   - right back 
21   - left side
26   - left babk
"""
class PIR_Sensor:
    #
    Pin_RS=20
    Pin_RB=19
    Pin_LS=21
    Pin_LB=20
    #
    def __init__(self):
        #self.PIR_Pin=Rpi_Pin
        GPIO.setmode(GPIO.BCM)
        GPIO.setup(self.Pin_RS, GPIO.IN)
        GPIO.setup(self.Pin_RB, GPIO.IN)
        GPIO.setup(self.Pin_LS, GPIO.IN)
        GPIO.setup(self.Pin_LB, GPIO.IN)
        self.motion_RS=0
        self.motion_RB=0
        self.motion_LS=0
        self.motion_LB=0
    #   
    def Detect_Motion(self):
        try:
            time.sleep(2) # to stabilize sensor
            for n in range(4):
                self.motion_RS=GPIO.input(self.Pin_RS)
                if self.motion_RS != 0 :
                    print("Motion Detected RS...",self.motion_RS)
                    return self.motion_RS
                self.motion_RB=GPIO.input(self.Pin_RB)
                if(self.motion_RB != 0):
                    print("Motion Detected RB...",self.motion_RB)
                    return self.motion_RB
                #self.motion_LS=GPIO.input(self.Pin_LS)
                if(self.motion_LS != 0):
                    print("Motion Detected LS...",self.motion_LS)
                    return self.motion_LS
                #self.motion_LB=GPIO.input(self.Pin_LB)
                if(self.motion_LB != 0):
                    print("Motion Detected LB...",self.motion_LB)
                    return self.motion_LB
                time.sleep(1)
            return 0
            time.sleep(0.1) #loop delay, should be less than detection delay
        except:    
            #GPIO.cleanup()
            return -1

def main():
    pir=PIR_Sensor()
    print(pir.Detect_Motion())
    
    
if __name__ == "__main__":
    main()
