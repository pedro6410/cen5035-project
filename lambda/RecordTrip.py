import json
import mysql.connector
import boto3

def lambda_handler(event, context):
    print(json.dumps(event))
    body = event['body']
    print(body)
    print(type(body))
    payload = json.loads(body)['payload']
    print(payload)
    trip_id = payload['trip_id']
    trip_date = payload['trip_date']
    emp_id = payload['emp_id']
    employer_id = payload['employer_id']
    distance_miles = payload['distance_miles']
    mode_of_transport = payload['mode_of_transport']
    start_time = payload['start_time']
    end_time = payload['end_time']
    carbon_credits = payload['carbon_credits']
   


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
    sql = "insert into carbon_bank.Trip(TripID, TripDate, StartTime , EndTime, Method, DistanceMiles, EmployeeID,EmployerID,TripCarbonCredits,StartLocation,EndLocation) VALUES(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
    
    val = (trip_id, trip_date, start_time, end_time, mode_of_transport, distance_miles, emp_id, employer_id,carbon_credits,'na','na')
    mydb.execute(sql, val)
    conn.commit()
    print(mydb.rowcount, "record inserted.")

    return {
        'statusCode': 200,
        'body': json.dumps('Trip recorded')
    }

