import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private StateOfVendingMachine idleState;
    private StateOfVendingMachine waitingForMoneyState;
    private StateOfVendingMachine dispensingSnackState;

    private StateOfVendingMachine currentState;
    private Snack selectedSnack;
    private double currentMoney;
    
    private Map<String, Snack> snacks;
    private SnackDispenseHandler firstHandler;

    public VendingMachine() {
        idleState = new IdleState(this);
        waitingForMoneyState = new WaitingForMoneyState(this);
        dispensingSnackState = new DispensingSnackState(this);
        
        currentState = idleState;

        snacks = new HashMap<>();
        currentMoney = 0;
    }

    public void addSnack(String name, double price, int quantity) {
        Snack snack = new Snack(name, price, quantity);
        snacks.put(name, snack);
    }
    
    public Snack getSnackByName(String name) {
        return snacks.get(name);
    }

    public void setSelectedSnack(String snackName) {
        this.selectedSnack = snacks.get(snackName);
    }

    public Snack getSelectedSnack() {
        return selectedSnack;
    }

    public void setState(StateOfVendingMachine state) {
        this.currentState = state;
    }

    public StateOfVendingMachine getIdleState() {
        return idleState;
    }

    public StateOfVendingMachine getWaitingForMoneyState() {
        return waitingForMoneyState;
    }

    public StateOfVendingMachine getDispensingSnackState() {
        return dispensingSnackState;
    }

    public StateOfVendingMachine getState() {
        return currentState;
    }

    public void addMoney(double amount) {
        this.currentMoney += amount;
    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(double money) {
        this.currentMoney = money;
    }

    public void selectSnack(String snackName) {
        firstHandler.handleRequest(snackName);
    }

    public void insertMoney(double amount) {
        currentState.insertMoney(amount);
    }

    public void dispenseSnack() {
        currentState.dispenseSnack();
    }

    public void setSnackDispenseHandler(SnackDispenseHandler handler) {
        this.firstHandler = handler;
    }
}
