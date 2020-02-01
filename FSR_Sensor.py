import RPi.GPIO as GPIO
import time


class FSR:
    Left_Pin = 16
    Right_Pin = 12
    Center_Pin = 25

    def __init__(self):
        GPIO.setmode(GPIO.BCM)
        GPIO.setup(self.Left_Pin,GPIO.IN)
        GPIO.setup(self.Center_Pin,GPIO.IN)
        GPIO.setup(self.Right_Pin,GPIO.IN)
        self.fsr_L=0
        self.fsr_R=0
        self.fsr_C=0
        
    def check_wight(self):
        prev_input = 0
        count = 0
        try:
            while(count < 1):
                #take a reading
                self.fsr_L = GPIO.input(self.Left_Pin)
                self.fsr_R = GPIO.input(self.Right_Pin)
                self.fsr_C = GPIO.input(self.Center_Pin)
                #if the last reading was low and this one high,    alert us
                print(self.fsr_L,"\n",self.fsr_R,"\n",self.fsr_C,"\n")
                if (prev_input > 0):
                    print("Under Pressure")
                #update previous input
                if (self.fsr_L > 0):
                    prev_input = self.fsr_L
                    return prev_input 
                if (self.fsr_R > 0):
                    prev_input = self.fsr_R
                    return prev_input
                if (self.fsr_C > 0):
                    prev_input = self.fsr_C
                    return prev_input
                #slight pause
                count+=1
                time.sleep(1)
            #return prev_input
        #
        except KeyboardInterrupt:
            return -1
            pass
        #
        finally:
            GPIO.cleanup()
            return 0

"""
def main():
    fsr=FSR()
    fsr.check_wight()

if __name__ == "__main__":
    main()
"""