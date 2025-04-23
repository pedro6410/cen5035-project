import json
import os

# Define file name for credit banks history
CREDIT_HISTORY_FILENAME = "view_credit_banks.json"

# Load credit history data
def load_credit_history():
    if not os.path.exists(CREDIT_HISTORY_FILENAME):
        return []
    with open(CREDIT_HISTORY_FILENAME, "r") as file:
        return json.load(file).get("credit_history", [])

# View credit banks
def view_credit_banks():
    # Load credit history from JSON file
    credit_history = load_credit_history()
    
    if not credit_history:
        print("[!] No credit history found.")
        return

    print("\nCarbon Credit History:")
    
    for record in credit_history:
        print(f"Transaction Date: {record['transaction_date']} | Buy/Sell: {record['buy_sell']} | Amount: {record['amount']} | New Balance: {record['new_balance']}")

# Main function to run the program
if __name__ == "__main__":
    view_credit_banks()
