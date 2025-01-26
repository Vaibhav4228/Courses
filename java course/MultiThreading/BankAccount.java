

class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    
    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " deposit: " + amount+ " balance: " + balance);
    }

    public synchronized void withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ". Balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " tried to withdraw " + amount + " but insufficient balance. Balance: " + balance);
        }
    }

    public int getBalance(){
        return balance;
    }
}