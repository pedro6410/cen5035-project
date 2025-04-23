import json
import mysql.connector
import boto3
from datetime import date
import hashlib
import string

def sanitize_string(text, allowed_chars):
    return ''.join(char for char in text if char in allowed_chars)
    
    

def lambda_handler(event, context):
    #print(json.dumps(event))
    body = event['body']

    payload = json.loads(body)['payload']
    
    user_id = payload['user_id']
    user_password = payload['user_password']
   


    # TODO implement
    secret = boto3.client('secretsmanager')
    response = secret.get_secret_value(SecretId='carbon-admin')
    secret = json.loads(response['SecretString'])
    conn = mysql.connector.connect(user=secret['username'], password=secret['password'],
                                  host=secret['host'],database=secret['dbname'])

    if conn:
        print ("Connected Successfully")
    else:
        print ("Connection Not Established")
        raise Exception('Connection Not Established')
        return

    allowed_chars = string.ascii_letters + string.digits + ' '
    search_id = sanitize_string(user_id, allowed_chars)
    hash_object = hashlib.sha256(user_password.encode('utf-8'))  
    password_hash = hash_object.hexdigest()
    mydb = conn.cursor()
    sql = "SELECT password_hash, EmployeeID, EmployerID FROM Users WHERE id = %s" 
    val = (search_id,)
    mydb.execute(sql, val)
    print(search_id)

    myresult = mydb.fetchone()

    if myresult:
        if myresult[0] == password_hash:
            print("Login Successful")
        else:
            print("Login Failed")
            return {
                'statusCode': 200,
                'body': json.dumps('Login Failed')
            }
    else:
        print("Login Failed")
        return {
            'statusCode': 200,
            'body': json.dumps('Login Failed')
        }

    return {
        'statusCode': 200,
        'body': json.dumps({  'employeeid': myresult[1],
        'employerid': myresult[2]}),
        
    }

    

