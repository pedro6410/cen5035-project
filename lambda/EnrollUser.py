import json
import mysql.connector
import boto3
from datetime import date
import hashlib


def lambda_handler(event, context):
    print(json.dumps(event))
    body = event['body']
    print(body)
    print(type(body))
    payload = json.loads(body)['payload']
    print(payload)
    user_id = payload['user_id']
    if 'employee_id' in payload:
        employee_id = payload['employee_id']
    else:
        employee_id = ""
    print(employee_id)
    user_password = payload['user_password']

    if 'employer_id' in payload:
        employer_id = payload['employer_id']
    else:
        employer_id = ""
    print(employer_id)


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
    enroll_date = date.today()
    hash_object = hashlib.sha256(user_password.encode('utf-8'))  
    password_hash = hash_object.hexdigest()
    mydb = conn.cursor()
    sql = "INSERT INTO Users (id, EnrollDate, password_hash,EmployeeID,EmployerID) VALUES(%s,%s,%s,%s,%s)"

    
    val = (user_id, enroll_date, password_hash,employee_id,employer_id)
    mydb.execute(sql, val)
    conn.commit()
    print(mydb.rowcount, "record inserted.")

    return {
        'statusCode': 200,
        'body': json.dumps('User {0} Created'.format(user_id))
        
    }

