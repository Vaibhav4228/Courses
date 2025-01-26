class TransactionTask implements Runnable{
    private BankAccount account;

    public TransactionTask(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run(){
        for(int i = 0; i< 5; i++){
            if (Math.random() > 0.5) {
                account.deposit((int) (Math.random() * 100) + 1); 
            } else {
                account.withdraw((int) (Math.random() * 100) + 1); 
            } 
            try {
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}