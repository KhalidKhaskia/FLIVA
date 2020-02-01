import RPi.GPIO as GPIO
import time

class Buzz:
    # Pins in bord
    Back_Pin = 17
    Front_pin = 27
    #init function
    def __init__(self):
        GPIO.setmode(GPIO.BCM)
        GPIO.setup(self.Front_pin, GPIO.OUT) #Buzzer on pin 27
        GPIO.setup(self.Back_Pin, GPIO.OUT) #Buzzer on pin 17
        self.back_puzz = GPIO.PWM(self.Back_Pin, 0.5)
        self.front_puzz = GPIO.PWM(self.Front_pin, 0.5)
    #
    def set_freq(self,freq):
        self.back_puzz.ChangeFrequency(freq)
        self.front_puzz.ChangeFrequency(freq)
    #
    def start(self):
        self.back_puzz.start(1) # start the buzzer
        self.front_puzz.start(1) # start the buzzer
        #GPIO.output(self.Back_Pin, GPIO.HIGH)
        #GPIO.output(self.Front_pin, GPIO.HIGH)
    #
    def set_lowToHigh(self):
        for i in range(800,1800,100): #a buzz runs from low to high
            self.back_puzz.ChangeFrequency(i)
            self.front_puzz.ChangeFrequency(i)
            time.sleep(0.05)
        self.back_puzz.ChangeFrequency(1) #buzz hums low while door is open
        self.front_puzz.ChangeFrequency(1) #buzz hums low while door is open
    #
    def stop(self):
        self.back_puzz.stop() #no more buzz buzz
        self.front_puzz.stop() #no more buzz buzz
        print("Stop buzzing.")
        #GPIO.output(self.Back_Pin, GPIO.LOW)
        #GPIO.output(self.Front_pin, GPIO.LOW)   
"""
def main():
    puz=Buzz()
    puz.set_freq(800)
    puz.start()
    time.sleep(5)
    puz.set_lowToHigh()
#
if (__name__ == "__main__"):
    main()
"""