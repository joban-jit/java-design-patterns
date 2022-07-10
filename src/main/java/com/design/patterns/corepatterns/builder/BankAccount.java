package com.design.patterns.corepatterns.builder;


public class BankAccount {
    private String name;
    private String accountNumber;
    private String email;
    private boolean newsletter;

    private BankAccount(BankAccountBuilder bankAccountBuilder){
        this.accountNumber = bankAccountBuilder.accountNumber;
        this.name = bankAccountBuilder.name;
        this.email = bankAccountBuilder.email;
        this.newsletter = bankAccountBuilder.newsletter;

    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getEmail() {
        return email;
    }

    public boolean isNewsletter() {
        return newsletter;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "name='" + name + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", email='" + email + '\'' +
                ", newsletter=" + newsletter +
                '}';
    }

    // inner static class
    public static class BankAccountBuilder {
        private String name;
        private String accountNumber;
        private String email;
        private boolean newsletter;

        public BankAccountBuilder(String name, String accountNumber) {
            this.name = name;
            this.accountNumber = accountNumber;
        }

        public BankAccountBuilder withEmail(String email){
            this.email = email;
            return this;
        }

        public BankAccountBuilder wantNewsletter(boolean newsletter){
            this.newsletter = newsletter;
            return this;
        }

        public BankAccount build(){
            return new BankAccount(this);
        }
    }

}

class Demo1{
    public static void main(String[] args) {
        BankAccount newAccount = new BankAccount.BankAccountBuilder("John", "1234567890")
                .withEmail("jon@example.com")
                .wantNewsletter(true)
                .build();
        System.out.println(newAccount);
    }
}
