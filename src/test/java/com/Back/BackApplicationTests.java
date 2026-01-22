package com.Back;

import com.Back.ai.service.ChatService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BackApplicationTests {
	@Autowired
	private ChatService chatService;
	@Test
	@DisplayName("Ollama Chat 클라이언트 테스트")
	void t1(){
		assertDoesNotThrow(()->{
			String response = chatService.streamChatResponse("Hello, Ollama!")
					.collectList()
					.map(list -> String.join("", list))
					.block();
			assertNotNull(response);
			assertFalse(response.isEmpty());
			System.out.println("Ollama Response: " + response);
		});
	}


}
