from flask import Flask, jsonify, request, make_response
from Sensors_Activating import Activate_Sensors as ActS
#from flask_api import FlaskAPI

app=Flask(__name__)
   
#this method is just for testing 
@app.route('/',methods=["GET"])  
def getMethod():    
    return "This is GET method" 

#the get method that activate the sensor manualy to get an indication if there any one stuck in the car
@app.route('/ActivateSensors',methods=["GET"])  
def getMethod2():   
    opj = ActS()
    opj.Activate()
    print(opj.SensorJson)
    res=jsonify(opj.SensorJson)
    return res
    
#A test post method 
@app.route('/TestPostApp',methods=["POST"])  
def postMethod2():   
    opj = ActS()
    opj.Activate()
    print(opj.SensorJson)
    res=jsonify(opj.SensorJson)
    return res
    
#a post method the return a jason object to the app
@app.route('/sensors',methods=["POST"])  
def postMethod():
    print(request)
    if request.is_json:
        req = request.get_json()
        resJson = {
            "name":"sensors_run",
            "value":100
        }
        res = make_response(jsonify(resJson), 200)
        # Code to control hardware
        return res   
    else:    
        return "laa hawla wlaa qoaa ilaa bellah"


if __name__=='__main__':
    app.run(debug=True ,host='192.168.68.116')
