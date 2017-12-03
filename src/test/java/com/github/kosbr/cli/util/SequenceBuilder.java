package com.github.kosbr.cli.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class SequenceBuilder {

    private final StringBuilder stringBuilder;

    public static SequenceBuilder create() {
        return new SequenceBuilder();
    }


    private SequenceBuilder() {
        stringBuilder = new StringBuilder();
    }

    public SequenceBuilder addLine(String line) {
        stringBuilder.append(line);
        stringBuilder.append("\n");
        return this;
    }

    public SequenceBuilder addInputMarker() {
        stringBuilder.append(">");
        /*
        In real life reading line adds CRLF
        in user's console. However, it is not true for tests, because
        separate inputStream is used. That is why there is no CRLF.
         */
        return this;
    }

    public InputStream getAsInputStream() {
        final String inputCommands = stringBuilder.toString();
        try {
            final InputStream inputStream = new ByteArrayInputStream(
                    inputCommands.getBytes(StandardCharsets.UTF_8.name()));
            return inputStream;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getAsString() {
        return stringBuilder.toString();
    }

    public static String createSequence(String... strings) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            stringBuilder.append(strings[i]);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
