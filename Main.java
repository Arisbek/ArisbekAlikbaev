abstract class PaymentMethod{
    abstract void processPayment(double amount);
    abstract void refund(double amount);
    abstract void checkStatus();
}


class CreditCardPayment extends PaymentMethod {
    private String status="Completed";
    @Override
    void processPayment(double amount) {
        amount = amount * 0.98;
        System.out.println("Net amount processed: " + amount);
    }

    @Override
    void refund(double amount) {
        System.out.println("Refunding credit card payment "+amount);
    }

    @Override
    void checkStatus() {
        System.out.println("Payment status: " + status);
    }
}

class PayPalPayment extends PaymentMethod {
    private String status="Completed";
    @Override
    void processPayment(double amount) {
        amount = amount - 0.30 - amount * 0.029;
        System.out.println("Net amount processed: " + amount);
    }

    @Override
    void refund(double amount) {
        System.out.println("Refunding PayPal payment " + amount);
    }

    @Override
    void checkStatus() {
        System.out.println("Payment status: " + status);
    }
}

class BankTransferPayment extends PaymentMethod {
    private String status="Processing";
    @Override
    void processPayment(double amount) {
        System.out.println("Payment will be processed in 2 days");
    }

    @Override
    void refund(double amount) {
        System.out.println("Refunding bank transfer payment "+amount);
    }

    @Override
    void checkStatus() {
        System.out.println("Payment status: " + status);
    }
}

class PaymentGateway {
    private PaymentMethod[] paymentMethods;
    private double[] amounts, refunds;

    public PaymentGateway(PaymentMethod[] paymentMethods, double[] amounts, double[] refunds) {
        this.paymentMethods = paymentMethods;
        this.amounts = amounts;
        this.refunds = refunds;
    }

    public void processAllPayments() {
        for (int i=0; i<amounts.length; i++) {
            paymentMethods[i].processPayment(amounts[i]);
        }
    }

    public void checkAllStatus() {
        for (PaymentMethod payment : paymentMethods) {
            payment.checkStatus();
        }
    }

    public void refundAllPayments() {
        for (int i=0; i<amounts.length; i++) {
            paymentMethods[i].refund(refunds[i]);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        PaymentMethod payment1 = new CreditCardPayment();
        PaymentMethod payment2 = new PayPalPayment();
        PaymentMethod payment3 = new BankTransferPayment();

        double[] amounts = {200.0, 150.0, 100.0};
        double[] refunds = {50.0, 100.0, 100.0};

        PaymentMethod[] paymentList = {payment1, payment2, payment3};
        PaymentGateway gateway = new PaymentGateway(paymentList, amounts, refunds);

        System.out.println("Processing all payments:");
        gateway.processAllPayments();

        System.out.println("Checking statuses after processing:");
        gateway.checkAllStatus();

        System.out.println("Refunding all payments:");
        gateway.refundAllPayments();
    }
}
