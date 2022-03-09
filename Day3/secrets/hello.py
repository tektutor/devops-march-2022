#!/usr/bin/python3
import flask
import os

app = flask.Flask(__name__)

@app.route('/<string:msg>', methods=['GET'])
def home(msg):
    s2 = os.environ.get('SECRET_USERNAME')
    s3 = os.environ.get('SECRET_PASSWORD')
    return s2 + " " + s3 + " " + msg 

app.run(host='0.0.0.0', port=80)
