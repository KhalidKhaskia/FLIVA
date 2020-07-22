from sim800l import SIM800L


sim800l=SIM800L('/dev/serial10')
sms = "hello there"
sim800l.send_sms('0542544540',sms)
