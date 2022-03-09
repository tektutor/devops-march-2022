#!/usr/bin/python3
import flask
import os

app = flask.Flask(__name__)

@app.route('/<string:msg>', methods=['GET'])
def home(msg):
    s1 = os.environ.get('MESSAGE')
    s2 = os.environ.get('JDK_HOME')
    s3 = os.environ.get('M2_HOME')
    return s1 + " " + s2 + " " + s3 + " " + msg 

app.run(host='0.0.0.0', port=80)
