package org.bank.demo.BL;

import org.bank.demo.beans.Account;
import org.bank.demo.beans.Loan;
import org.bank.demo.beans.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailServiceBL {

    @Autowired
    private JavaMailSender emailSender;


    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }


    public void DepositEmail(Account account, Double amount) {
        String subject;
        String body;
        if (amount > 0) {
            subject = "SpringBank - Deposit Confirmation";
            body = String.format("Dear %s,\n\n" +
                            "We are pleased to inform you that a deposit has been successfully made to your account.\n\n" +
                            "Deposit Details:\n" +
                            "- Amount: %.2f\n" +
                            "- Account Number: %s\n" +
                            "- Date and Time: %s\n\n" +
                            "Thank you for banking with us. If you have any questions or need further assistance, please contact our customer support.\n\n" +
                            "Best regards,\n" +
                            "SpringBank Customer Service\n" +
                            "[Bank Contact Information]",
                    account.getCustomer().getName(), amount, account.getAccountId(), LocalDateTime.now());

        } else {
            subject = "SpringBank - withdrawal Confirmation";
            body = String.format("Dear %s,\n\n" +
                            "We are pleased to inform you that a withdrawal has been successfully made to your account.\n\n" +
                            "withdrawal Details:\n" +
                            "- Amount: %.2f\n" +
                            "- Account Number: %s\n" +
                            "- Date and Time: %s\n\n" +
                            "Thank you for banking with us. If you have any questions or need further assistance, please contact our customer support.\n\n" +
                            "Best regards,\n" +
                            "SpringBank Customer Service\n" +
                            "[Bank Contact Information]",
                    account.getCustomer().getName(), amount, account.getAccountId(), LocalDateTime.now());

        }

        sendEmail(account.getCustomer().getEmail(), subject, body);
    }


    public void TransactionConformation(Account account, Double amount, Transaction transaction) {
        String subject = "SpringBank - Transaction Confirmation";
        String body = String.format("Dear %s,\n\n" +
                        "We would like to inform you about a recent transaction on your account.\n\n" +
                        "Transaction Details:\n" +
                        "- Amount: %.2f\n" +
                        "- Account Number: %s\n" +
                        "- Transaction ID: %s\n" +
                        "- Date and Time: %s\n" +
                        "If you recognize this transaction, no further action is required. If you do not recognize this transaction or believe it to be unauthorized, please contact our customer service team immediately.\n\n" +
                        "For your security, please do not share your account details with anyone. If you suspect any suspicious activity, we recommend updating your account password and security questions.\n\n" +
                        "Thank you for banking with us.\n\n" +
                        "Best regards,\n" +
                        "[SpringBank] Customer Service\n" +
                        "[Bank Contact Information]",
                account.getCustomer().getName(), amount, account.getAccountId(),transaction.getTransactionId() , LocalDateTime.now());
        sendEmail(account.getCustomer().getEmail(), subject, body);
    }

    public void loanEmailConformation(Loan loan, Account account) {
        String subject = "SpringBank - Loan Confirmation";
        String body = String.format("Dear %s,\n\n" +
                        "We are pleased to inform you that your loan application has been approved.\n\n" +
                        "Loan Details:\n" +
                        "- Loan Amount: %.2f\n" +
                        "- Loan ID: %s\n" +
                        "- Date and Time: %s\n" +
                        "- Interest Rate: %.2f%%\n" +
                        "Thank you for choosing our bank. If you have any questions or need further assistance, please contact our customer support.\n\n" +
                        "Best regards,\n" +
                        "[Bank Name] Customer Service\n" +
                        "[Bank Contact Information]",
                account.getCustomer().getName(), loan.getAmount(), loan.getLoanId(), LocalDateTime.now(), loan.getInterestRate());
        sendEmail(account.getCustomer().getEmail(), subject, body);
    }
}
