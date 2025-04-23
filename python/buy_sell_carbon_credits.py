import json
import os

# Ensure the correct path
FILENAME = "employers.json"

# Load employer data
def load_employers():
    if not os.path.exists(FILENAME):
        print("[!] employers.json not found.")
        return []
    with open(FILENAME, "r") as file:
        data = json.load(file)
        return data.get("employers", [])

# Save employer data
def save_employers(data):
    with open(FILENAME, "w") as file:
        json.dump({"employers": data}, file, indent=4)
    print("[+] Employers data saved to employers.json.")

# Display employer information
def display_employer_info(employer):
    print(f"ID: {employer['id']} | Name: {employer['name']} | Email: {employer['email']} | Carbon Credits: {employer['carbon_credits']}")

# Buy carbon credits
def buy_carbon_credits(employers, emp_id, amount):
    employer = next((e for e in employers if e["id"] == emp_id), None)
    if employer:
        employer["carbon_credits"] += amount
        print(f"{amount} carbon credits have been added to {employer['name']}'s account.")
        save_employers(employers)
    else:
        print("[!] Employer not found.")

# Sell carbon credits
def sell_carbon_credits(employers, emp_id, amount):
    employer = next((e for e in employers if e["id"] == emp_id), None)
    if employer and employer["carbon_credits"] >= amount:
        employer["carbon_credits"] -= amount
        print(f"{amount} carbon credits have been sold from {employer['name']}'s account.")
        save_employers(employers)
    else:
        print("[!] Not enough carbon credits to sell.")

# Execute the transaction
def main():
    employers = load_employers()
    print("Welcome to the Carbon Credit Marketplace!")
    
    while True:
        try:
            emp_id = int(input("\nEnter Employer ID to buy/sell credits (or 0 to exit): ").strip())
            if emp_id == 0:
                print("Exiting the system.")
                break

            employer = next((e for e in employers if e["id"] == emp_id), None)
            if not employer:
                print("[!] Employer not found.")
                continue

            print(f"\nCurrent info for {employer['name']}:")
            display_employer_info(employer)

            action = input("Do you want to (B)uy or (S)ell credits? ").strip().upper()

            if action == "B":
                amount = int(input("Enter the number of carbon credits to buy: ").strip())
                buy_carbon_credits(employers, emp_id, amount)
            elif action == "S":
                amount = int(input("Enter the number of carbon credits to sell: ").strip())
                sell_carbon_credits(employers, emp_id, amount)
            else:
                print("[!] Invalid option. Please choose B or S.")
        
        except ValueError:
            print("[!] Invalid input. Please enter a valid number.")

if __name__ == "__main__":
    main()
