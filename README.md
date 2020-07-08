# F.L.I.V.A
  F.L.I.V.A - "Forgeting Lives In vehicle Alert".
  For a few years now people have been making the mistakes of forgetting a child or a pet inside a car , sometimes children or pets get into the cars and lock themselves inside    without knowing how to get out .
   Our project here was developed in order to have a solution to this problem , once a person locks the car and the sensores we install inside sense that there is a child or a    pet locked in , the system will send a notification to that same person's smartphone and let him know that there is a child or a pet inside the vehicle so the driver goes      back and takes the child/pet out and would save their life.
  

## Getting Started
  Our system works with load cell , Motion Detection ,Temperature Sensors and microphones. 
  all those above combined with a raspberry pi and arduino systems becomes the perfect match for our project .
  all the sensors work with the RPI and are connected to it , once a sensor gets activated and senses movement , temperature or even that there is weight left in the car , it     sends a signal to the RPI and the RPI send a notification to the drivers phone.


### Prerequisites

What we used :
1- Load Cell "weight sensor" : A load cell is a sensor or a transducer that converts a load or force acting on it into an electronic signal. This electronic signal can be a voltage change, current change or frequency change depending on the type of load cell and circuitry used. There are many different kinds of load cells. We offer resistive load cells and capacitive load cells

2- PIR "Motion Detection sensors" : A passive infrared sensor (PIR sensor) is an electronic sensor that measures infrared (IR) light radiating from objects in its field of view. They are most often used in PIR-based motion detectors. PIR sensors are commonly used in security alarms and automatic lighting applications.

3- Thermostat "Temperature sensor" : The Thermostat
The Thermostat is a contact type electro-mechanical temperature sensor or switch, that basically consists of two different metals such as nickel, copper, tungsten or aluminium etc, that are bonded together to form a Bi-metallic strip. The different linear expansion rates of the two dissimilar metals produces a mechanical bending movement when the strip is subjected to heat.

4- Raspberry PI : The Raspberry Pi is a low cost, credit-card sized computer that plugs into a computer monitor or TV, and uses a standard keyboard and mouse. It is a capable little device that enables people of all ages to explore computing, and to learn how to program in languages like Scratch and Python.

5- Arduino : Arduino is an open-source hardware and software company, project and user community that designs and manufactures single-board microcontrollers and microcontroller kits for building digital devices.

6- GPS & GSM : There are two modules that work with the RPI , one is a GPS module that alows us to maintain active GPS signals to know where the vehicle is at all times , the GSM module is used to connect our system to the internet using a SIM card with an active connection to a service provider.

### Installing
1- Load Cell "weight sensor" : Under each seat we will be installing a weight sensor that checks when there is weight on the sear , similar to the seatbelt sensor that beeps when someone is sitting in the chair and isn't putting their belt on .

2- PIR "Motion Detection sensors" : These sensors are installed all around the car in the inside corners , which gives them the advantage of having a full IR scan for the car when someone or something moves inside it.

3- Thermostat "Temperature sensor" : this sensor is installed in the center top of the car so it could keep checking the temp of the car all the time and balancing it to know if there is a change in the temprature from the inside "as in there is a body with heat inside and it's not the cars own temp".

4- Raspberry PI & Arduino : will be installed next to the cars ECU unit or in an area in the car where it will be safe from outside breaches like water weather and theives , and also will have a nearby power source for avtivating it even when the car is off.


## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds


## Authors

* **Khalid Khaskia** .

## License

This project is licensed under the RPI and Google .

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
