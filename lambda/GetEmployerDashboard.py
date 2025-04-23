import json
import mysql.connector
import boto3
from datetime import date
import hashlib
import string


    

def lambda_handler(event, context):
    #print(json.dumps(event))
    body = event['body']

    payload = json.loads(body)['payload']
    
    employer_id = payload['employer_id']
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


    mydb = conn.cursor()
    sql = "Select TripID, TripDate, StartTime, EndTime, StartLocation, EndLocation, Method, DistanceMiles, Trip.EmployeeID, Trip.EmployerID, TripCarbonCredits, Employee.EmployeeName FROM Trip left join Employee on Trip.EmployeeID = Employee.EmployeeID  WHERE Trip.EmployerID = %s" 


    val = (employer_id,)
    mydb.execute(sql, val)
    transactions = mydb.fetchall()
    print(len(transactions))
    if len(transactions) > 0:
        print(transactions)
        transactions_list = []
        for t in transactions:
            if t[6] == '1':
                activity = 'driving'
            elif t[6] == '2':
                activity = 'ridesharing'
            else:
                activity = 'carpooling'
            transaction = {
                'trip_id': t[0],
                'date': str(t[1]),
                'activityType' : activity,
                'employee_id': t[8],
                'creditAmounts': t[10],
                'employeeName' : t[11] 
            }
            transactions_list.append(transaction)
    else:
        print("Dashboard not found for employer")
        return {
            'statusCode': 200,
            'body': json.dumps('Records not found')
        }

    return {
        'statusCode': 200,
        'body': json.dumps( { 'employer_id': employer_id,
        'credits': transactions_list })
    }

    

