"""
apitest.py

Test API with 2 endpoints for GET and POST testing
"""

import flask as fsk
from flask import request, jsonify

app = fsk.Flask(__name__)
app.config['DEBUG'] = True


@app.route('/get', methods=['GET'])
def get_test():
    res = {'nome': 'yuriel-v', 'idade': 24}
    return jsonify(res)

@app.route('/post', methods=['POST'])
def post_test():
    print("Printing request JSON")
    
    req = request.get_json()
    # print(req)
    print(type(req))

    for key, value in req.items():
        print(f"key = {key}; value = {value}")

    return jsonify({'status': 'post received'})

app.run(host='0.0.0.0')
