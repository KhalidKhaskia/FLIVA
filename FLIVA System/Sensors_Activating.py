import RPi.GPIO as GPIO
import time
from Sensors.FSR_Sensor import FSR
from Sensors.PIR_Sensor import PIR_Sensor as PIR
from Sensors.MCSD_Sensor import MCSD
from Sensors.DHT11_Sensor import DHT_Sensor as DHT
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db
from firebase_admin import firestore
import Notifications.PushNotify as fcm
from datetime import date



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
        #pir_res = 111
        del pir
        
        mcsd=MCSD()
        door_sensor=mcsd.Check_Open_Doors() # result of open door
        #door_sensor = 123
        del mcsd
        
        fsr=FSR()
        fsr_res=fsr.check_wight()
        #fsr_res=1234
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


        
def main():
    print("start")
    fsr_driver=FSR()
    car_doors=MCSD()
    
    # the first subject based on a case that the driver shutdown the car
    # check the chair of the driver are avilable
    count_time_driver_at_car=0
    sec_driver_outside=0
    num_of_loops = 5 # run loop time  
    delay_in_sec = 2 # dealy each runable loop  
    
    # date today
    today = date.today()
    d1 = today.strftime("%Y%m%d")
    
    #firebase db connection
    cred = credentials.Certificate("/home/pi/Documents/Final_Project_FLIVA/backend/FLIVA/FLIVA System/Notifications/fliva-db-firebase-adminsdk-ws3ya-55b3574963.json")
    #firebase_admin.initialize_app(cred)
    
    tokens = ["chUm2MF2-UM:APA91bF8kKdWy6S5yOtk1cDxjW9FwYqnDHqmrBorz6zfa5AI-GoWthO0AzX2EYdaq3lcEkFUtF-qHDIjHpmI1n439rKZ26efXNag7AKNr5OVb2vMHEliD2U0VW0Z_qt8dHiRmBOqSV8q"]
    fcm.sendPush("Forgiten Life", "someone is stuck in your car", tokens)
    
    while(True):
        driver_chiar = fsr_driver.check_driver_chair() # is the driver set on the chair
        car_doors_open = car_doors.Check_Open_Doors() # is the door open
        
        print(driver_chiar,car_doors_open)
        #if(driver_chiar == -1 or driver_chiar == None or car_doors_open == -1 or car_doors_open == None):
        #    continue
        
        if((driver_chiar > 0) or ( (car_doors_open == 1) and (driver_chiar == 0) )):
            time.sleep(2)
            #driver_chiar=fsr_driver.check_driver_chair()
            count_time_driver_at_car+=1
            print(2,count_time_driver_at_car)
            if (count_time_driver_at_car>10):
                # run car alert
                #let the driver control the stop of the alert 
                break
            continue
        
        if((car_doors_open == 0) and (driver_chiar == 0)):
            
            time.sleep(3)
            car_doors_open=car_doors.Check_Open_Doors()
            while (car_doors_open == 0):
                time.sleep(1)
                car_doors_open=car_doors.Check_Open_Doors()
                #save log
                sec_driver_outside+=1
                print(123)
                if(sec_driver_outside > 5):
                    break
            
            activate_fliva(fcm) # that function that run the system of FLIVA
        #save into data base logs
        break
     
    del fsr_driver    
    del car_doors
    
def activate_fliva(fcm):
    print("activte fliva")
    # create an object of the sensors classes
    pir=PIR()
    fsr=FSR()
    mcsd=MCSD() 
    dht=DHT()
    temparture_res=dht.Calculate_DHT()
        
    tuple_results = () # tuple that will have the results of activating the fsr and the pir sensors
    list_results = [0,0]  # list that will contain the resault of calculating the result of the sensors withen each loop
    counter = 0 # let the scan run ttl-time to leave times (loops) to get (deook) 
    ttl = 2 # time to leave the loop
    time.sleep(3) # delay 2 sec to refresh the sensors 
    while(counter < ttl):
        #START
        door_sensor=mcsd.Check_Open_Doors() # result of open door
       
        if (door_sensor > 0):
            exit()
        time.sleep(2) # delay 2 sec to refresh the sensors 
        pir_res=pir.Detect_Motion() # moving scan 
        print(pir_res)
        
        list_results[0]+=pir_res
        time.sleep(1) # delay 1 sec to refresh the sensors 
        fsr_res=fsr.check_wight()   # chair wight scan
        print(fsr_res)
        list_results[1]+=pir_res
        # *********************** mic scan
        # list_results[2]+=mic_res
        counter+=1
        #END while
        print("run")
        
    tuple_results = tuple(list_results)
    if((list_results[0] < ttl) or (list_results[1] < ttl)):
        print("inside")
        # date today
        today = date.today()
        d1 = today.strftime("%Y%m%d")
        #cred = credentials.Certificate("/home/pi/Documents/Final_Project_FLIVA/backend/FLIVA/FLIVA System/fliva-db-firebase-adminsdk-ws3ya-8c78122a1a.json")
        #firebase_admin.initialize_app(cred)
        db = firestore.client()
        doc_ref = db.collection(u'sensors').document(d1)
        doc_ref.set({
            u'door': door_sensor,
            u'seat': list_results[0] ,
            u'movement': list_results[1] ,
            u'temperature': temparture_res[0],
            u'humidity': temparture_res[1],
            u'sound': u'xxx',
            u'location': u'32.22,34.95',
        })
        #exit()
    print("done")
    #!!!!
    # make call
    
    # send sms with gps location
    
    # car alert
    
    # app alert
    tokens = ["chUm2MF2-UM:APA91bF8kKdWy6S5yOtk1cDxjW9FwYqnDHqmrBorz6zfa5AI-GoWthO0AzX2EYdaq3lcEkFUtF-qHDIjHpmI1n439rKZ26efXNag7AKNr5OVb2vMHEliD2U0VW0Z_qt8dHiRmBOqSV8q"]
    fcm.sendPush("Forgiten Life", "someone is stuck in your car", tokens)
    
    # delete the objects
    del pir,mcsd,fsr,dht,fcm

# calling main func
if __name__ == "__main__":
    main()
 
