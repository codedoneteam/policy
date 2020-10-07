package premium.calculator.exception;

public class PolicyNullException extends RuntimeException {
    @Override
    public String toString() {
        return "PolicyDto should be not null";
    }
}
