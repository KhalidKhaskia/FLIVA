import Adafruit_DHT as DHTS
import RPi.GPIO as GPIO
import time

class DHT_Sensor:
    # variables
    Dht_Pin = 18
    #
    def __init__(self):
        self.DHT_PIN = self.Dht_Pin
        self.DHT_SENSOR = DHTS.DHT11
        self.temperature=0 # temprature
        self.humidity=0 # humadity
        
    def Calculate_DHT(self):
       # while True:
        self.humidity, self.temperature = DHTS.read(self.DHT_SENSOR, self.DHT_PIN)
        DT=self.temperature
        DH=self.humidity
        if DT is not None and DH is not None:
            print('Temp={0:0.1f}*C  Humidity={1:0.1f}%'.format(DT, DH))
            result=[DT,DH]
            return result
        else:
            print('Failed to get reading. Try again!')
            result=[0,0]
            return result
        #time.sleep(3)

def main():
    dht=DHT_Sensor()
    print(dht.Calculate_DHT())
#
if (__name__ == "__main__"):
    main()