package l1.map.financial;

public class FinancialProducts {
    double debitAccountBalance;
    double creditCardBalance;
    double savingAccountBalance;
    double retirementPlanAccountBalance;

    public FinancialProducts ( double debitAccountBalance ,
                               double creditCardBalance ,
                               double savingAccountBalance ,
                               double retirementPlanAccountBalance ) {
        this.debitAccountBalance = debitAccountBalance;
        this.creditCardBalance = creditCardBalance;
        this.savingAccountBalance = savingAccountBalance;
        this.retirementPlanAccountBalance = retirementPlanAccountBalance;
    }


    public double getDebitAccountBalance () {
        return debitAccountBalance;
    }

    public void setDebitAccountBalance ( double debitAccountBalance ) {
        this.debitAccountBalance = debitAccountBalance;
    }

    public double getCreditCardBalance () {
        return creditCardBalance;
    }

    public void setCreditCardBalance ( double creditCardBalance ) {
        this.creditCardBalance = creditCardBalance;
    }

    public double getSavingAccountBalance () {
        return savingAccountBalance;
    }

    public void setSavingAccountBalance ( double savingAccountBalance ) {
        this.savingAccountBalance = savingAccountBalance;
    }

    public double getRetirementPlanAccountBalance () {
        return retirementPlanAccountBalance;
    }

    public void setRetirementPlanAccountBalance ( double retirementPlanAccountBalance ) {
        this.retirementPlanAccountBalance = retirementPlanAccountBalance;
    }

    public String printAboveHundred () {
        String s = "FinancialProducts{|";
        if ( debitAccountBalance > 100 ) {
            s += "debitAccountBalance=" + debitAccountBalance + "|";
        }
        if ( creditCardBalance > 100 ) {
            s += "creditCardBalance" + creditCardBalance + "|";
        }
        if ( savingAccountBalance > 100 ) {
            s += "savingAccountBalance=" + savingAccountBalance + "|";
        }
        if ( retirementPlanAccountBalance > 100 ) {
            s += ", retirementPlanAccountBalance=" + retirementPlanAccountBalance + "|";
        }
        s += '}';
        return s;
    }

    @Override
    public String toString () {
        return "FinancialProducts{" +
                "debitAccountBalance=" + debitAccountBalance +
                ", creditCardBalance=" + creditCardBalance +
                ", savingAccountBalance=" + savingAccountBalance +
                ", retirementPlanAccountBalance=" + retirementPlanAccountBalance +
                '}';
    }
}
