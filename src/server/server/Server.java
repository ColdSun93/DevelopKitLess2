package server.server;

import server.client.Client;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private boolean work;
    private ServerView serverView;
    private List<Client> clientList;
    private FileStorage fileStorage;

    public Server(ServerView serverView, FileStorage fileStorage) {
        this.serverView = serverView;
        this.fileStorage = fileStorage;
        clientList = new ArrayList<>();
    }

    public void start(){
        if (work){
            showOnWindow("Сервер уже запущен");
        } else {
            work = true;
            showOnWindow("Сервер запущен!");
        }
    }

    public void stop(){
        if (!work){
            showOnWindow("Сервер уже остановлен");
        } else {
            work = false;
            for (Client client: clientList){
                disconnectUser(client);
            }
            showOnWindow("Сервер остановлен!");
        }
    }

    public void disconnectUser(Client client){
        clientList.remove(client);
        if (client != null){
            client.disconnect();
            showOnWindow(client.getName() + " отключился от чата");
        }
    }

    public boolean connectUser(Client client){
        if (!work){
            return false;
        }
        clientList.add(client);
        showOnWindow(client.getName() + " подключился к чату");
        return true;
    }

    public void sendMessage(String text){
        if (!work){
            return;
        }
        text += "";
        showOnWindow(text);
        answerAll(text);
        saveInHistory(text);
    }

    public StringBuffer getHistory() {
        return fileStorage.readLogTFromFile();
    }

    private void answerAll(String text){
        for (Client client: clientList){
            client.answerFromServer(text);
        }
    }

    private void showOnWindow(String text){
        serverView.showMessage(text + "\n");
    }

    private void saveInHistory(String text){
        fileStorage.writeLogToFile(text);
    }

}
