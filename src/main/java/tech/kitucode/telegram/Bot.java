package tech.kitucode.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tech.kitucode.telegram.config.ConfigManager;
import tech.kitucode.telegram.domain.Nganya;
import tech.kitucode.telegram.repository.NganyaRepository;

import java.util.List;

public class Bot extends TelegramLongPollingBot {
    private final Logger logger = LoggerFactory.getLogger(TelegramLongPollingBot.class);

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        // extract user info
        User user = message.getFrom();

        logger.info("received an update from userId: {}, userName: {}, message: {}", user.getId(), user.getUserName(), message.getText());

        if (message.isCommand()) {
            // get text and see what to do
            String command = message.getText();

            String response = handleCommand(command);

            sendText(user.getId(), response);
        }
    }

    @Override
    public String getBotUsername() {
        return ConfigManager.getProperties().getBotUsername();
    }

    @Override
    public String getBotToken() {
        return ConfigManager.getProperties().getBotToken();
    }

    private String handleCommand(String command) {
        switch (command) {
            case "/start":
                return "Hello World!";
            case "/list":
                List<Nganya> nganyas = NganyaRepository.findAll();
                StringBuilder sb = new StringBuilder();
                for (Nganya nganya : nganyas) {
                    sb.append(nganya.getId()).append(".").append(" ").append(nganya.getName()).append("\n");
                }
                return sb.toString();
            case "/routes":
                return "1. Embakasi\n2. Rongai\n3. Umoja\n4. Junkie";
            default:
                return "Hello World!";
        }
    }

    private void sendText(Long userId, String message) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(userId.toString())
                .text(message)
                .build();

        try {
            execute(sendMessage);
        } catch (TelegramApiException ex) {
            throw new RuntimeException(ex);
        }
    }
}
