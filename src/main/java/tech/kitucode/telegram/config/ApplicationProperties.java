package tech.kitucode.telegram.config;

public class ApplicationProperties {
    private String botToken;
    private String botUsername;

    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public String getBotUsername() {
        return botUsername;
    }

    public void setBotUsername(String botUsername) {
        this.botUsername = botUsername;
    }

    @Override
    public String toString() {
        return "ApplicationProperties{" +
                "botToken='" + botToken + '\'' +
                ", botUsername='" + botUsername + '\'' +
                '}';
    }
}
