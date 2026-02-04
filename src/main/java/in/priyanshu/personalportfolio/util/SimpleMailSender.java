package in.priyanshu.personalportfolio.util;

import java.io.*;
import java.net.Socket;

public class SimpleMailSender {

    public static void sendMail(
            String smtpServer,
            int port,
            String from,
            String to,
            String subject,
            String body
    ) throws IOException {

        Socket socket = new Socket(smtpServer, port);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream())
        );

        readResponse(reader);

        sendCommand(writer, "HELO localhost");
        readResponse(reader);

        sendCommand(writer, "MAIL FROM:<" + from + ">");
        readResponse(reader);

        sendCommand(writer, "RCPT TO:<" + to + ">");
        readResponse(reader);

        sendCommand(writer, "DATA");
        readResponse(reader);

        writer.write("Subject: " + subject + "\r\n");
        writer.write("From: " + from + "\r\n");
        writer.write("To: " + to + "\r\n");
        writer.write("\r\n"); // blank line before body
        writer.write(body + "\r\n");
        writer.write(".\r\n");
        writer.flush();

        readResponse(reader);

        sendCommand(writer, "QUIT");
        socket.close();
    }

    private static void sendCommand(BufferedWriter writer, String command) throws IOException {
        writer.write(command + "\r\n");
        writer.flush();
    }

    private static void readResponse(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        System.out.println("SMTP: " + line);
    }
}
