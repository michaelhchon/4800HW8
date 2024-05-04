
public class VendingMachineDriver {
	
    public static void main(String[] args) {
        // Create a vending machine
        VendingMachine vendingMachine = new VendingMachine();

        // Add snacks to the vending machine
        vendingMachine.addSnack("Coke", 1.25, 5);
        vendingMachine.addSnack("Pepsi", 1.15, 5);
        vendingMachine.addSnack("Cheetos", 1.0, 3);
        vendingMachine.addSnack("Doritos", 1.0, 3);
        vendingMachine.addSnack("KitKat", 1.5, 4);
        vendingMachine.addSnack("Snickers", 1.5, 1);
        
        // Set up chain of responsibility        
        SnackDispenseHandler cokeHandler = new SpecificSnackDispenseHandler(vendingMachine.getSnackByName("Coke"), vendingMachine);
        SnackDispenseHandler pepsiHandler = new SpecificSnackDispenseHandler(vendingMachine.getSnackByName("Pepsi"), vendingMachine);
        SnackDispenseHandler cheetosHandler = new SpecificSnackDispenseHandler(vendingMachine.getSnackByName("Cheetos"), vendingMachine);
        SnackDispenseHandler doritosHandler = new SpecificSnackDispenseHandler(vendingMachine.getSnackByName("Doritos"), vendingMachine);
        SnackDispenseHandler kitkatHandler = new SpecificSnackDispenseHandler(vendingMachine.getSnackByName("KitKat"), vendingMachine);
        SnackDispenseHandler snickersHandler = new SpecificSnackDispenseHandler(vendingMachine.getSnackByName("Snickers"), vendingMachine);
        
        cokeHandler.setNextHandler(pepsiHandler);
        pepsiHandler.setNextHandler(cheetosHandler);
        cheetosHandler.setNextHandler(doritosHandler);
        doritosHandler.setNextHandler(kitkatHandler);
        kitkatHandler.setNextHandler(snickersHandler);
        
        vendingMachine.setSnackDispenseHandler(cokeHandler);
        
        // Simulate vending machine, with snickers being out of stock
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(2.0);
        vendingMachine.dispenseSnack();
        
        vendingMachine.selectSnack("Doritos");
        vendingMachine.insertMoney(0.5);
        vendingMachine.dispenseSnack();

        vendingMachine.selectSnack("Doritos");
        vendingMachine.insertMoney(1.0);
        vendingMachine.dispenseSnack();

        vendingMachine.selectSnack("Snickers");
        vendingMachine.insertMoney(2.0);
        vendingMachine.dispenseSnack();
        
        vendingMachine.selectSnack("Snickers");
        vendingMachine.insertMoney(2.0);
        vendingMachine.dispenseSnack();
    }
}
