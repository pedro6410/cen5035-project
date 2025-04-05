# Transaction logic for buying and selling carbon credits
def buy_transaction(employer, amount):
    employer['carbon_credits'] += amount
    return employer

def sell_transaction(employer, amount):
    if employer['carbon_credits'] >= amount:
        employer['carbon_credits'] -= amount
        return employer
    else:
        return None
