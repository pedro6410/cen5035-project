import json
import os
from datetime import datetime

FILENAME = "employers.json"

def load_employers():
    if not os.path.exists(FILENAME):
        return []
    with open(FILENAME, "r") as file:
        try:
            return json.load(file)
        except json.JSONDecodeError:
            print("[!] Error: Invalid JSON format.")
            return []

def save_employers(data):
    with open(FILENAME, "w") as file:
        json.dump(data, file, indent=4)

def list_pending(employers):
    pending = [e for e in employers if e.get("approved") is None]
    if not pending:
        print("\nNo pending employer registrations.")
        return []
    print("\nPending Employers:")
    for e in pending:
        print(f'ID: {e["id"]} | Name: {e["name"]} | Email: {e["email"]}')
    return pending

def find_employer_by_id(employers, emp_id):
    return next((e for e in employers if e["id"] == emp_id), None)

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
            print("Exiting review process.")
            break

        employer = find_employer_by_id(employers, emp_id)
        if not employer:
            print("[!] Employer not found.")
            continue

        if employer.get("approved") is not None:
            status = "approved" if employer["approved"] else "rejected"
            print(f'[!] Employer "{employer["name"]}" has already been {status}.')
            continue

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
            print("[!] Invalid input. Enter A or R only.")
            continue

        save_employers(employers)

if __name__ == "__main__":
    review_employer()


