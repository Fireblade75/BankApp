package com.example.bankapp.cli;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BankCLI {
    private Terminal terminal;
    private PrintWriter writer;
    private CommandHandler commandHandler = new CommandHandler();

    public BankCLI() throws IOException {
        terminal = TerminalBuilder.builder()
                .system(true)
                .streams(System.in, System.out)
                .build();
        writer = terminal.writer();
        writer.write("BankApp 1.0; CLI Edition\n");
        writer.flush();
    }

    private boolean run() {
        writer.print("> ");
        writer.flush();
        Scanner scanner = new Scanner(terminal.input());

        String line = scanner.nextLine();
        String output = commandHandler.handleCommand(line);
        if(output.equals("exit")) {
            return false;
        } else {
            writer.println(output);
            return true;
        }
    }

    private void exit() {
        try {
            terminal.close();
        } catch (IOException e) {
            System.out.println("Failed to close terminal: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            BankCLI bankCLI = new BankCLI();
            while (true) {
                if(!bankCLI.run()) {
                    break;
                }
            }
            bankCLI.exit();
        } catch (IOException e) {
            System.out.println("Failed to create JLine CLI: " + e.getMessage());
        }
    }
}
