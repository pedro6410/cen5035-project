import json
import os

# File containing employer data
FILENAME = "employers.json"

# Load employer data
def load_employers():
    if not os.path.exists(FILENAME):
        return []
    with open(FILENAME, "r") as file:
        return json.load(file).get("employers", [])

# Save employer data
def save_employers(data):
    with open(FILENAME, "w") as file:
        json.dump({"employers": data}, file, indent=4)

# Add carbon credits to employer's account
def add_carbon_credits(emp_id, amount):
    employers = load_employers()
    employer = next((e for e in employers if e["id"] == emp_id), None)
    
    if employer:
        employer["carbon_credits"] += amount
        save_employers(employers)
        print(f'Added {amount} carbon credits to {employer["name"]} account.')
    else:
        print(f'[!] Employer with ID {emp_id} not found.')

# Main program
if __name__ == "__main__":
    try:
        emp_id = int(input("Enter Employer ID to add carbon credits: "))
        amount = int(input("Enter amount of carbon credits to add: "))
        add_carbon_credits(emp_id, amount)
    except ValueError:
        print("[!] Please enter valid numbers.")
