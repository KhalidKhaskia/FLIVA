# F.L.I.V.A
  F.L.I.V.A - "Forgeting Lives In vehicle Alert".
  For a few years now people have been making the mistakes of forgetting a child or a pet inside a car , sometimes children or pets get into the cars and lock themselves inside    without knowing how to get out .
   Our project here was developed in order to have a solution to this problem , once a person locks the car and the sensores we install inside sense that there is a child or a    pet locked in , the system will send a notification to that same person's smartphone and let him know that there is a child or a pet inside the vehicle so the driver goes      back and takes the child/pet out and would save their life.
  
  ![Fliva_Icon](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/FlivaSystemPhotos/fliva%20icon.jpeg)
  
----------------------------

## Getting Started
  Our system works with load cell , Motion Detection ,Temperature Sensors and microphones. 
  all those above combined with a raspberry pi and arduino systems becomes the perfect match for our project .
  all the sensors work with the RPI and are connected to it , once a sensor gets activated and senses movement , temperature or even that there is weight left in the car , it     sends a signal to the RPI and the RPI send a notification to the drivers phone.

----------------------------

### Prerequisites

What we used :
1- Load Cell "weight sensor" : A load cell is a sensor or a transducer that converts a load or force acting on it into an electronic signal. This electronic signal can be a voltage change, current change or frequency change depending on the type of load cell and circuitry used. There are many different kinds of load cells. We offer resistive load cells and capacitive load cells

![FSR](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/FlivaSystemPhotos/images%20(4).jpg)

2- PIR "Motion Detection sensors" : A passive infrared sensor (PIR sensor) is an electronic sensor that measures infrared (IR) light radiating from objects in its field of view. They are most often used in PIR-based motion detectors. PIR sensors are commonly used in security alarms and automatic lighting applications.

![PIR](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/FlivaSystemPhotos/download%20(3).jpg)

3- Thermostat "Temperature & Humidity sensor" : The Thermostat
The Thermostat is a contact type electro-mechanical temperature sensor or switch, that basically consists of two different metals such as nickel, copper, tungsten or aluminium etc, that are bonded together to form a Bi-metallic strip. The different linear expansion rates of the two dissimilar metals produces a mechanical bending movement when the strip is subjected to heat.

![DHT11](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/FlivaSystemPhotos/download%20(4).jpg)

4- Raspberry PI : The Raspberry Pi is a low cost, credit-card sized computer that plugs into a computer monitor or TV, and uses a standard keyboard and mouse. It is a capable little device that enables people of all ages to explore computing, and to learn how to program in languages like Scratch and Python.

![RPI](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/RPI4.jpg)

5- Arduino : Arduino is an open-source hardware and software company, project and user community that designs and manufactures single-board microcontrollers and microcontroller kits for building digital devices.

![arduino](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/ARDUINO%20NANO.png)

6- GPS & GSM : There are two modules that work with the RPI , one is a GPS module that alows us to maintain active GPS signals to know where the vehicle is at all times , the GSM module is used to connect our system to the internet using a SIM card with an active connection to a service provider.

![GPS & GSM](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/FlivaSystemPhotos/download.jpg)![GPS & GSM2](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/FlivaSystemPhotos/images%20(3).jpg)

7- Door & Sensor : The door sensor is widely used in security area, It is used to detect/monitor entrances 

![Door](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/FlivaSystemPhotos/download%20(2).jpg)

### Installing
1- Load Cell "weight sensor" : Under each seat we will be installing a weight sensor that checks when there is weight on the sear , similar to the seatbelt sensor that beeps when someone is sitting in the chair and isn't putting their belt on .

2- PIR "Motion Detection sensors" : These sensors are installed all around the car in the inside corners , which gives them the advantage of having a full IR scan for the car when someone or something moves inside it.

3- Thermostat "Temperature sensor" : this sensor is installed in the center top of the car so it could keep checking the temp of the car all the time and balancing it to know if there is a change in the temprature from the inside "as in there is a body with heat inside and it's not the cars own temp".

4- Raspberry PI & Arduino : will be installed next to the cars ECU unit or in an area in the car where it will be safe from outside breaches like water weather and theives , and also will have a nearby power source for avtivating it even when the car is off.

----------------------------

# Deployment

  ## System Architecture
  
  ![Architecture](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/FlivaSystemPhotos/arch.jpg)
  
  ## hardware connections
  
  ![System ](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/FlivaSystemPhotos/sys1.jpeg)
  
  ![System ](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/FlivaSystemPhotos/sys2.jpeg)
  
  ![System ](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/FlivaSystemPhotos/sys3.jpeg)
  
  
  ## FLIVA App
 
   ### Login Page ![Login Page](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/FlivaSystemPhotos/app1.jpg)
  
   ### Regester ![Regester](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/FlivaSystemPhotos/app2.jpg)
  
   ### Main Page ![Main Page](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/FlivaSystemPhotos/app3.jpg)
  
   ### System Activate History ![System Activate History](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/FlivaSystemPhotos/app4.jpg)
  
   ### Run The System Manualy ![Run The System Manualy](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/FlivaSystemPhotos/app5.jpg)
  
   ### Edit Profile Page ![Edit Profile Page](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/FlivaSystemPhotos/app6.jpg)
  
   ### Sensor Mangement ![Sensor Mangement](https://github.com/KhalidKhaskia/FLIVA/blob/master/Saved%20Pictures/FlivaSystemPhotos/app7.jpg)

----------------------------

## Built With
  ### Hardware
   * [Raspberry Pi 4](https://www.raspberrypi.org/documentation/) - The FLIVA tiny computer 
   * [Arduino](https://www.raspberrypi.org/documentation/) - Arduino is an open-source hardware and software that used to run sensor with wireless conction
   * [GSM 900A](https://wiki.eprolabs.com/index.php?title=SIM_900A_GSM_GPRS_Module) a sensor that used to send sms messeges to useres
   * [GPS](https://www.u-blox.com/sites/default/files/products/documents/NEO-6_DataSheet_(GPS.G6-HW-09005).pdf) a sensor that used to get the location
 
    
  ### Software
   * [Android Studio](https://developer.android.com/docs) - The development environment for Google's Android operating system 
   * [Java](https://docs.oracle.com/javase/7/docs/api/) - used to develop the android app
   * [Volley](https://developer.android.com/training/volley) - is an HTTP library that makes networking for Android apps easier
   * [Python](https://docs.python.org/3/) - used to develop the FLIVA system
   * [Flask](https://flask.palletsprojects.com/en/1.1.x/) - Flask is a web framework that handel a http request betwwen the RPI to the app
   * [RPi.GPIO](https://pypi.org/project/RPi.GPIO/) - This package provides a class to control the GPIO on a Raspberry Pi.
   * [Arduino](https://docs.python.org/3/) - used to develop the sensors the based on wireless connection
   * [FireBase DB](https://firebase.google.com/docs) - the database of FLIVA
 
 ----------------------------
 
## Authors

* **Khalid Khaskia** .

## License

This project is licensed under the RPI and Google .

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
