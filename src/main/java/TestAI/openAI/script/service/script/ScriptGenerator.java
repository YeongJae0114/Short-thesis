package TestAI.openAI.script.service.script;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.retry.NonTransientAiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScriptGenerator {
    private final OpenAiChatModel openAiChatModel;

    @Value("${message.instructions}")
    private String instructions;
    @Value("${message.question}")
    private String question;
    @Value("${message.additional_instructions}")
    private String additionalInstructions;


    public String generateScript(String introduction) {
        String prompt = createPrompt(introduction);
        try {
            return openAiChatModel.call(prompt);
        } catch (NonTransientAiException e) {
            // 로그 출력 후 예외 전달
            System.err.println("Quota or rate limit exceeded. Details: " + e.getMessage());
            throw e; // ExceptionHandler에서 처리
        } catch (Exception e) {
            // 일반적인 예외 처리
            System.err.println("An unexpected error occurred: " + e.getMessage());
            throw e; // ExceptionHandler에서 처리
        }
    }

    private String createPrompt(String introduction) {
        return "{\n" +
                "  \"message\": {\n" +
                "    \"instructions\": \"" + instructions + "\",\n" +
                "    \"introduction\": \"" + introduction + "\",\n" +
                "    \"question\": \"" + question + "\",\n" +
                "    \"additional_instructions\": \"" + additionalInstructions + "\"\n" +
                "  }\n" +
                "}";
    }
}
