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
                return getDefaultMessage();
            case "/nganyas":
                List<Nganya> nganyas = NganyaRepository.findAll();
                StringBuilder sb = new StringBuilder();
                for (Nganya nganya : nganyas) {
                    sb.append(nganya.getId()).append(".").append(" ").append(nganya.getName()).append("\n");
                }
                return sb.toString();
            case "/routes":
                return "1. Embakasi\n2. Rongai\n3. Umoja\n4. Junkie";
            default:
                return getDefaultMessage();
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

    private String getDefaultMessage() {
        StringBuilder sb = new StringBuilder();

        sb.append("I can help you stay up to date with nganya trends.\n\n");

        sb.append("You can control me by sending these commands:\n\n");

        sb.append("/nganyas - list all nganyas\n");
        sb.append("/routes - list all routes\n");

        return sb.toString();
    }
}
