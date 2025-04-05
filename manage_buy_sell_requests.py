import json
import os

REQUESTS_FILENAME = "buy_sell_requests.json"

# Load buy/sell requests
def load_requests():
    if not os.path.exists(REQUESTS_FILENAME):
        print(f"[!] {REQUESTS_FILENAME} not found.")
        return []  # Return an empty list if the file doesn't exist
    with open(REQUESTS_FILENAME, "r") as file:
        data = json.load(file)
        print(f"[+] {REQUESTS_FILENAME} loaded successfully.")
        return data.get("buy_sell_requests", [])

# Save buy/sell requests
def save_requests(data):
    with open(REQUESTS_FILENAME, "w") as file:
        json.dump({"buy_sell_requests": data}, file, indent=4)
    print(f"[+] {REQUESTS_FILENAME} saved successfully.")

# List buy/sell requests
def list_requests(buy_sell_requests):
    print("\nListing buy/sell requests...")
    if not buy_sell_requests:
        print("\nNo pending requests.")
        return []
    for r in buy_sell_requests:
        print(f'ID: {r["id"]} | Type: {r["type"]} | Amount: {r["amount"]} | Status: {r["status"]}')
    return buy_sell_requests

# Process a buy/sell request
def process_request(buy_sell_requests, request_id, action):
    request = next((r for r in buy_sell_requests if r["id"] == request_id), None)
    if not request:
        print("[!] Request not found.")
        return buy_sell_requests

    if action == "approve":
        request["status"] = "approved"
        print(f'Request {request_id} has been approved.')
    elif action == "reject":
        request["status"] = "rejected"
        print(f'Request {request_id} has been rejected.')
    else:
        print("[!] Invalid action. Please choose approve or reject.")
    
    save_requests(buy_sell_requests)  # Save the changes to the JSON file
    return buy_sell_requests

# Main function to simulate the process
def main():
    buy_sell_requests = load_requests()

    while True:
        try:
            request_id = int(input("\nEnter Request ID to process (or 0 to exit): ").strip())
            if request_id == 0:
                print("Exiting the system.")
                break

            action = input("Approve (A) or Reject (R) the request? ").strip().lower()
            if action == "a":
                buy_sell_requests = process_request(buy_sell_requests, request_id, "approve")
            elif action == "r":
                buy_sell_requests = process_request(buy_sell_requests, request_id, "reject")
            else:
                print("[!] Invalid option.")
        except ValueError:
            print("[!] Invalid input. Please enter a valid number.")

if __name__ == "__main__":
    main()
