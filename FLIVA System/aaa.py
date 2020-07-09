         
if (__name__ == "__main__"):
    main()

def main():
    
    registration_token = 'chUm2MF2-UM:APA91bF8kKdWy6S5yOtk1cDxjW9FwYqnDHqmrBorz6zfa5AI-GoWthO0AzX2EYdaq3lcEkFUtF-qHDIjHpmI1n439rKZ26efXNag7AKNr5OVb2vMHEliD2U0VW0Z_qt8dHiRmBOqSV8q'

    # See documentation on defining a message payload.
    message = messaging.Message(
        data={
            'score': '850',
            'time': '2:45',
        },
        token=registration_token,
    )

    # Send a message to the device corresponding to the provided
    # registration token.
    response = messaging.send(message)
    # Response is a message ID string.
    print('Successfully sent message:', response)
    
    
    
    
    
    
    
def activateFLV():
    
    driver_chair=FSR()
    car_doors=MCSD()
    
    num_of_loops = 5 # run loop time  
    delay_in_sec = 2 # dealy each runable loop  
    
    # while(true):
    # the first subject based on a case that the driver shutdown the car
    # check the chair of the driver are avilable
    count_time_driver_at_car=0
    sec_driver_outside=0
    while(true):
        
        driver_chiar=fsr_driver.check_driver_chair() # is the driver set on the chair
        car_doors_open=car_doors.Check_Open_Doors() # is the door open
                   
        if(driver_chair > 0):
            time.sleep(3)
            #driver_chiar=fsr_driver.check_driver_chair()
            count_time_driver_at_car+=1
            if (count_time_driver_at_car>10):
                # run car alert
                #let the driver control the stop of the alert 
                exit
            continue
        
        if((car_doors_open == 0) and (driver_chiar == 0)):
            
            time.sleep(3)
            car_doors_open=car_doors.Check_Open_Doors()
            if (car_doors_open > 1):
                # save log
                exit
            sec_driver_outside+=1
            if(sec_driver_outside < 5) continue
        
        activate_fliva() # that function that run the system of FLIVA
        #save logs
     
    del fsr_driver    
    del car_doors

def activate_fliva():
    print("activte fliva")
    
