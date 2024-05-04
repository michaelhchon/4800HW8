interface StateOfVendingMachine {
    void selectSnack(String snackName);
    void insertMoney(double amount);
    void dispenseSnack();
}

// Idle State
class IdleState implements StateOfVendingMachine {
    private VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectSnack(String snackName) {
        vendingMachine.setSelectedSnack(snackName);
        System.out.println("Selected snack: " + snackName);
        vendingMachine.setState(vendingMachine.getWaitingForMoneyState());
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Please select a snack first.");
    }

    @Override
    public void dispenseSnack() {
        System.out.println("Please select a snack first.");
    }
}

// Waiting for Money State
class WaitingForMoneyState implements StateOfVendingMachine {
    private VendingMachine vendingMachine;

    public WaitingForMoneyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectSnack(String snackName) {
    	Snack currentSnack = vendingMachine.getSelectedSnack();
        System.out.println("Snack already selected: " + currentSnack.getName());
    }

    @Override
    public void insertMoney(double amount) {
        vendingMachine.addMoney(amount);
        System.out.println("Inserted money: $" + amount);
        double totalAmount = vendingMachine.getCurrentMoney();
        double snackPrice = vendingMachine.getSelectedSnack().getPrice();
        
        if (totalAmount >= snackPrice) {
            System.out.println("Transaction complete. Dispensing snack...");
            vendingMachine.setState(vendingMachine.getDispensingSnackState());
            vendingMachine.dispenseSnack();
        } else {
            System.out.println("Insert more money. Snack price is $" + snackPrice);
        }
    }

    @Override
    public void dispenseSnack() {
        System.out.println("Insert money first.");
    }
}

// Dispensing Snack State
class DispensingSnackState implements StateOfVendingMachine {
    private VendingMachine vendingMachine;

    public DispensingSnackState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectSnack(String snackName) {
        System.out.println("Currently dispensing a snack...");
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Currently dispensing a snack...");
    }

    @Override
    public void dispenseSnack() {
        Snack snack = vendingMachine.getSelectedSnack();
        if (snack.getQuantity() > 0) {
            // Reduce the quantity and refund any change if necessary
            snack.setQuantity(snack.getQuantity() - 1);
            double change = vendingMachine.getCurrentMoney() - snack.getPrice();
            vendingMachine.setCurrentMoney(0);
            
            System.out.println("Dispensing " + snack.getName() + ".");
            System.out.println("Change returned: $" + change);
        } else {
            System.out.println("Sorry..." + snack.getName() + " is out of stock. Returning money...");
        }

        // Reset the state to idle
        vendingMachine.setState(vendingMachine.getIdleState());
    }
}
