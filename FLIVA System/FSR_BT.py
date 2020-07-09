import serial as ser
import time

bluetoothSerial = ser.Serial("/dev/rfcomm0",baudrate=9600)
print("Bluetooth Connected")
try:
    while 1:
        data = bluetoothSerial.readline()
        print(data," - ",type(data))
        time.sleep(1)
        #bluetoothSerial.write(data)
        
except KeyboardInterrupt:
    print("Quit")