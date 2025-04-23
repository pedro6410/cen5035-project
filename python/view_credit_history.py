import json
import os

# File name
FILENAME = "credit_history.json"

# Function to load credit history from the file
def load_credit_history():
    if not os.path.exists(FILENAME):
        return []
    with open(FILENAME, "r") as file:
        return json.load(file).get("credit_history", [])

# Function to display credit history
def view_credit_history():
    print("Starting to display credit history...")
    
    # Load data from the file
    history = load_credit_history()
    
    # Check if history is empty
    if not history:
        print("[!] No credit history found.")
        return

    print("\nCarbon Credit History for Employers:")
    
    # Print each record in the history
    for record in history:
        print(f"Transaction Date: {record['transaction_date']} | Buy/Sell: {record['buy_sell']} | Amount: {record['amount']} | New Balance: {record['new_balance']}")

    print("Displayed credit history.")

# Run the function when the script is executed
if __name__ == "__main__":
    view_credit_history()
