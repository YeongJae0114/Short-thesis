package TestAI.openAI.global.exception;

import org.springframework.ai.retry.NonTransientAiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // OpenAI Quota 초과 예외 처리
    @ExceptionHandler(NonTransientAiException.class)
    public ResponseEntity<String> handleNonTransientAiException(NonTransientAiException e) {
        // 클라이언트에 반환할 메시지
        String errorMessage = "OpenAI API quota exceeded or rate limit reached. Please try again later.";
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(errorMessage);
    }

    // 기타 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        // 기타 예외에 대한 처리
        String errorMessage = "An unexpected error occurred: " + e.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
