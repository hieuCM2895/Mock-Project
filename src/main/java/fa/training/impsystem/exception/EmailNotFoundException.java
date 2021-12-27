package fa.training.impsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailNotFoundException extends Exception{

    public EmailNotFoundException(String errorException) {
        super(errorException);
    }
}
