package com.test.userstore.models;

import com.test.userstore.exceptions.ValidationException;
import com.test.userstore.processors.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public enum Command implements CommandAction {

    HELP("help") {
        @Override
        public void doAction() {
            System.out.println(Arrays.stream(Command.values()).map(Command::getValue).collect(Collectors.joining("\n")));
        }
    },
    SAVE("save") {
        @Override
        public void doAction() {
            try {
                new SaveProcessor().process();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    },
    UPDATE("update") {
        @Override
        public void doAction() throws IOException {
            new UpdateProcessor().process();
        }
    },
    DELETE("delete") {
        @Override
        public void doAction() throws IOException, ValidationException {
            new DeleteProcessor().process();
        }
    },
    SHOW_LIST("show_list") {
        @Override
        public void doAction() throws IOException {
            new GetAllProcessor().process();
        }
    },
    SHOW_ONE("show_one") {
        @Override
        public void doAction() throws IOException {
            new GetOneProcessor().process();
        }
    },
    QUITE("quite") {
        @Override
        public void doAction() {
            System.out.println("program exist");

        }
    };

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Command from(String value) {
        for (Command command : Command.values()) {
            if (command.name().equalsIgnoreCase(value)) {
                return command;
            }
        }
        return HELP;
    }
}
