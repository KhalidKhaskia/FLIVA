import RPi.GPIO as GPIO
import time
from Sensors.FSR_Sensor import FSR
from Sensors.PIR_Sensor import PIR_Sensor as PIR
from Sensors.MCSD_Sensor import MCSD
from Sensors.DHT11_Sensor import DHT_Sensor as DHT


class Activate_Sensors:
    
    SensorJson=[]
    Activation_DateTime=None
    
    def Activate(self):
        
        Activation_DateTime=time.strftime("%a %d-%m-%Y * %H:%M:%S")
        
        #each print used for testing the returned object  
        dht=DHT()
        temparture_res=dht.Calculate_DHT()
        print(temparture_res)
        DHT_Temp=temparture_res[0]
        DHT_Humid=temparture_res[1]
        #print(dht.temperature,"\n",dht.humidity,"\n",dht.DHT_PIN,"\n")
        #print(type(dht.temperature),"\n",type(dht.humidity),"\n",type(dht.DHT_PIN),"\n")
        # ght_notice=""
        del dht
        
        #time.sleep(3)
        pir=PIR()
        pir_res=pir.Detect_Motion()
        #pir_res=int(pir.motion) # pir sensor motion result
        del pir
        
        mcsd=MCSD()
        door_sensor=mcsd.Check_Open_Doors() # result of open door
        del mcsd
        
        fsr=FSR()
        fsr_res=fsr.check_wight()
        del fsr
        
        # create json object of the sensors returned value
 
        self.SensorJson=[
                {
                    "Name":"FSR",
                    "Value":fsr_res
                },
                {
                    "Name":"PIR",
                    "Value":pir_res
                },
                {
                    "Name":"DHTtemperature",
                    "Value":DHT_Temp
                },            
                {
                    "Name":"DHThumidity",
                    "Value":DHT_Humid
                },            
                {
                    "Name":"MCSD",
                    "Value":door_sensor
                }
            ]
        #"Sensors":
        #print(self.SensorJson)

            
    