// Handler interface for the chain of responsibility
interface SnackDispenseHandler {
    void handleRequest(String snackName);
    void setNextHandler(SnackDispenseHandler nextHandler);
}

// Snack dispense handler
class SpecificSnackDispenseHandler implements SnackDispenseHandler {
    private Snack snack;
    private SnackDispenseHandler nextHandler;
    private VendingMachine vendingMachine;

    public SpecificSnackDispenseHandler(Snack snack, VendingMachine vendingMachine) {
        this.snack = snack;
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void handleRequest(String snackName) {
        if (snack.getName().equals(snackName)) {
            vendingMachine.setSelectedSnack(snackName);
            System.out.println("Handling request for " + snackName);
            vendingMachine.getState().selectSnack(snackName);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(snackName);
        }
    }

    @Override
    public void setNextHandler(SnackDispenseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
