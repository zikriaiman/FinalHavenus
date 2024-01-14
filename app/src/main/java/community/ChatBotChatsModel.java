package community;

public class ChatBotChatsModel {

    private String message;
    private String sender;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public ChatBotChatsModel(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }
}
