package server.server;

import java.io.*;

public class FileStorage {
    private static final String FILENAME = "src/server/server/LOG.txt";

    /**
     * Чтение данных из файла
     * */
    public StringBuffer readLogTFromFile() {
        StringBuffer stringBuffer = new StringBuffer();
        try (FileReader reader = new FileReader(FILENAME); BufferedReader buffer = new BufferedReader(reader)) {

            String line = buffer.readLine();
            if (line == null || line.isBlank()) {
                System.out.println("Log is empty.");
                return stringBuffer.append("Log is empty.\n");
            }

            while (line != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
                line = buffer.readLine();
            }
            stringBuffer.append("\n");

            return stringBuffer;

        } catch (IOException ioe) {
            System.out.println("Log file is not found: " + FILENAME);
        }
        return stringBuffer.append("Log file is not found: " + FILENAME + "\n");
    }

    /**
     * Запись истории чата в файл
     * */
    public void writeLogToFile(String data) {
        try (FileWriter writer = new FileWriter(FILENAME, true); BufferedWriter buffer = new BufferedWriter(writer)) {
            buffer.write(data);
            buffer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
