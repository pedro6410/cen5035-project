import json
import os
from datetime import datetime

FILENAME = "employers.json"

# Load employer data from JSON file
def load_employers():
    if not os.path.exists(FILENAME):
        return []
    with open(FILENAME, "r") as file:
        return json.load(file).get("employers", [])

# Save employer data to JSON file
def save_employers(data):
    with open(FILENAME, "w") as file:
        json.dump({"employers": data}, file, indent=4)

# List all pending employer registrations
def list_pending(employers):
    # We now check for "approved: false" to list pending registrations
    pending = [e for e in employers if e.get("approved") is False]
    if not pending:
        print("\nNo pending employer registrations.")
        return []
    print("\nPending Employers:")
    for e in pending:
        print(f'ID: {e["id"]} | Name: {e["name"]} | Email: {e["email"]}')
    return pending

# Find employer by ID
def find_employer_by_id(employers, emp_id):
    return next((e for e in employers if e["id"] == emp_id), None)

# Review employer registrations and approve/reject them
def review_employer():
    employers = load_employers()
    while True:
        pending = list_pending(employers)
        if not pending:
            break

        try:
            emp_id = int(input("\nEnter Employer ID to review (or 0 to exit): ").strip())
        except ValueError:
            print("[!] Please enter a valid number.")
            continue

        if emp_id == 0:
            print("Exiting the review process.")
            break

        employer = find_employer_by_id(employers, emp_id)
        if employer and employer.get("approved") is False:
            decision = input(f'Approve (A) or Reject (R) employer "{employer["name"]}"? ').strip().upper()
            if decision == "A":
                employer["approved"] = True
                employer["reviewed_by"] = "admin"
                employer["review_date"] = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
                print(f'✔ Employer "{employer["name"]}" has been approved.')
            elif decision == "R":
                employer["approved"] = False
                employer["reviewed_by"] = "admin"
                employer["review_date"] = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
                print(f'✖ Employer "{employer["name"]}" has been rejected.')
            else:
                print("[!] Invalid input. Please enter A or R.")
            save_employers(employers)

if __name__ == "__main__":
    review_employer()
