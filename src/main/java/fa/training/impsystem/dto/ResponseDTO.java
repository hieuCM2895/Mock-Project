package fa.training.impsystem.dto;

public class ResponseDTO {
    private boolean hasError;
    private String message;

    public boolean hasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
