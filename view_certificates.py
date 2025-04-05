import json
import os

CERTIFICATES_FILENAME = "certificates.json"

# Load certificates data
def load_certificates():
    if not os.path.exists(CERTIFICATES_FILENAME):
        return []
    with open(CERTIFICATES_FILENAME, "r") as file:
        return json.load(file).get("certificates", [])

# Save certificates data
def save_certificates(certificates):
    with open(CERTIFICATES_FILENAME, "w") as file:
        json.dump({"certificates": certificates}, file, indent=4)

# View certificates
def view_certificates():
    certificates = load_certificates()
    if not certificates:
        print("\n[!] No certificates found.")
        return
    print("\nCarbon Credit Certificates:")
    for cert in certificates:
        print(f"ID: {cert['id']} | Certificate Number: {cert['certificate_number']} | Issued By: {cert['issued_by']} | Date: {cert['date']}")

# Add a new certificate
def add_certificate():
    certificates = load_certificates()
    cert_id = len(certificates) + 1
    certificate_number = input("Enter certificate number: ")
    issued_by = input("Enter the issuer of the certificate: ")
    date = input("Enter the issue date (YYYY-MM-DD): ")
    
    new_certificate = {
        "id": cert_id,
        "certificate_number": certificate_number,
        "issued_by": issued_by,
        "date": date
    }
    certificates.append(new_certificate)
    save_certificates(certificates)
    print(f"Certificate {certificate_number} added successfully.")

def main():
    print("Welcome to the Carbon Credit Marketplace!")
    while True:
        choice = input("Do you want to (V)iew certificates or (A)dd a certificate (or 0 to exit): ").strip().upper()
        
        if choice == "V":
            view_certificates()
        elif choice == "A":
            add_certificate()
        elif choice == "0":
            print("Exiting the system.")
            break
        else:
            print("[!] Invalid option. Please choose 'V' to view or 'A' to add a certificate.")

if __name__ == "__main__":
    main()




