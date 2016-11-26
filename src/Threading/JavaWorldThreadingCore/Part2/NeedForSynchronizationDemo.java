package Threading.JavaWorldThreadingCore.Part2;

/**
 * Created by chetan on 26/11/16.
 */
// NeedForSynchronizationDemo.java
class NeedForSynchronizationDemo {
    public static void main(String[] args) {
        FinTrans ft = new FinTrans();
        //Two threads one is used for depositing and oine used for widthdrawing threads
       //the second argument is the name of thread while the first argument is the FinTrans instance
        TransThread tt1 = new TransThread(ft, "Deposit Thread");
        TransThread tt2 = new TransThread(ft, "Withdrawal Thread");
        tt1.start();
        tt2.start();
    }
}

class FinTrans {
    public static String transName;
    public static double amount;
}

class TransThread extends Thread {
    private FinTrans ft;
//Here Fintrans transname and Fintrans amount are sharted variable and probable culprits
    TransThread(FinTrans ft, String name) {
        super(name); // Save thread's name
        this.ft = ft; // Save reference to financial transaction object
    }
//Has two critical section , one that serves the purpose of deposits and one that servees the purpose of withdrawl
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (getName().equals("Deposit Thread")) {
                // Start of deposit thread's critical code section
                ft.transName = "Deposit";
                try {
                    Thread.sleep((int) (Math.random() * 1000));
                } catch (InterruptedException e) {
                }
                ft.amount = 2000.0;
                System.out.println(ft.transName + " " + ft.amount);
                // End of deposit thread's critical code section
            } else {
                // Start of withdrawal thread's critical code section
                ft.transName = "Withdrawal";
                try {
                    Thread.sleep((int) (Math.random() * 1000));
                } catch (InterruptedException e) {
                }
                ft.amount = 250.0;
                System.out.println(ft.transName + " " + ft.amount);
                // End of withdrawal thread's critical code section
            }
        }
    }
}

