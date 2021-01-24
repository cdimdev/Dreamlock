package com.dreamlock.core.parser;

import com.dreamlock.core.message_system.constants.ForbiddenWords;
import com.dreamlock.core.message_system.constants.UserQuestions;
import com.dreamlock.core.parser.constants.Rules;
import com.dreamlock.core.parser.constants.TokenType;
import com.dreamlock.core.parser.models.Lexeme;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public JsonObject parse(List<Lexeme> lexemes) {
        Rules rules = Rules.INSTANCE;

        lexemes = connectUnknownLexemes(lexemes);

        StringBuilder stringBuilder = new StringBuilder();
        for (Lexeme lexeme : lexemes) {
            stringBuilder.append(lexeme.getTokenType().toString().charAt(0));
            stringBuilder.append(lexeme.getId());
        }
        JsonObject output;

        if (rules.getRule(stringBuilder.toString())) {
            output = parseCorrectInputString(lexemes, stringBuilder);
        } else {
            output = parseIncorrectInputString(lexemes);
        }
        return output;
    }

    private List<Lexeme> connectUnknownLexemes(List<Lexeme> lexemes) {
        List<Lexeme> filteredLexemes = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        boolean flag = false;

        for (int i = 0; i < lexemes.size(); i++) {
            Lexeme lexeme = lexemes.get(i);

            if (lexeme.getTokenType().toString().equals("UNKNOWN")) {
                stringBuilder.append(lexeme.getTypedString());
                flag = true;
                if (i == (lexemes.size() - 1)) {
                    filteredLexemes.add(new Lexeme(1, stringBuilder.toString(), TokenType.UNKNOWN, 0));
                } else {
                    stringBuilder.append(" ");
                }
            } else {
                if (flag) {
                    stringBuilder.setLength(stringBuilder.length() - 1);
                    filteredLexemes.add(new Lexeme(1, stringBuilder.toString(), TokenType.UNKNOWN, 0));
                    stringBuilder = new StringBuilder();
                }
                filteredLexemes.add(lexeme);
                flag = false;
            }
        }

        return filteredLexemes;
    }

    private JsonObject parseCorrectInputString(List<Lexeme> lexemes, StringBuilder stringBuilder) {
        JsonObject output = new JsonObject();
        JsonObject rule = new JsonObject();
        JsonArray sentences = new JsonArray();
        JsonArray sentence = new JsonArray();

        for (Lexeme lexeme : lexemes) {
            JsonObject word = new JsonObject();
            word.addProperty("word", lexeme.getTokenType().toString().toLowerCase());
            word.addProperty("id", lexeme.getId());
            word.addProperty("description", lexeme.getTypedString());
            word.addProperty("uniqueId", lexeme.getUniqueId());
            sentence.add(word);
        }

        rule.addProperty("rule", stringBuilder.substring(0, 2));
        sentence.add(rule);
        sentences.add(sentence);
        output.addProperty("error", false);
        output.add("sentences", sentences);

        return output;
    }

    private JsonObject parseIncorrectInputString(List<Lexeme> lexemes) {
        Rules rules = Rules.INSTANCE;

        StringBuilder ruleStringBuilder = new StringBuilder();
        StringBuilder command = new StringBuilder();
        int correctWords = 0;
        JsonObject output = new JsonObject();

        output.addProperty("error", true);

        boolean hasForbidden;
        boolean hasQuestion;
        StringBuilder wholeCommandBuilder = new StringBuilder();
        for (Lexeme lexeme : lexemes) {
            wholeCommandBuilder.append(lexeme.getTypedString());
        }

        String wholeCommandString = cleanUpCommand(wholeCommandBuilder.toString());

        hasForbidden = stringHasForbiddenWord(wholeCommandString);

        hasQuestion = stringHasQuestion(wholeCommandString);

        if (hasForbidden) {
            output.addProperty("forbidden", true);
            output.addProperty("has_question", false);
        } else if (hasQuestion) {
            output.addProperty("forbidden", false);
            output.addProperty("has_question", true);
            output.addProperty("question", wholeCommandString);
        } else {
            output.addProperty("forbidden", false);
            output.addProperty("has_question", false);
            output.addProperty("empty", false);
            if (lexemes.size() == 0) {
                output.addProperty("empty", true);
                output.addProperty("correctWords", 0);
                return output;
            }

            for (int i = 0; i < lexemes.size(); i++) {
                ruleStringBuilder.append(lexemes.get(i).getTokenType().toString().charAt(0));
                ruleStringBuilder.append(lexemes.get(i).getId());
                command.append(lexemes.get(i).getTypedString()).append(" ");
                if (rules.getRule(ruleStringBuilder.toString())) {
                    correctWords = i + 1;
                    output.addProperty("correctCommand", command.toString());
                }
            }

            if (correctWords == 0 && lexemes.get(0).getTokenType().toString().equals("VERB")) {
                if (lexemes.size() == 1) {
                    correctWords = 100;
                } else {
                    correctWords++;
                }
                output.addProperty("correctCommand", lexemes.get(0).getTypedString());
            }

            output.addProperty("correctWords", correctWords);
        }
        return output;
    }

    private boolean stringHasQuestion(String wholeCommand) {
        for (String question : UserQuestions.INSTANCE.getQuestions()) {
            if (wholeCommand.contains(question)) {
                return true;
            }
        }
        return false;
    }

    private boolean stringHasForbiddenWord(String wholeCommand) {
        for (ForbiddenWords forbiddenWords : ForbiddenWords.values()) {
            if (wholeCommand.contains(forbiddenWords.toString())) {
                return true;
            }
        }

        return false;
    }

    private String cleanUpCommand(String wholeCommand) {
        if (wholeCommand.length() > 0) {
            wholeCommand = wholeCommand.substring(0, wholeCommand.length() - 1);
        }
        wholeCommand = wholeCommand.replaceAll("[.,;?:'\"!]", "");
        int length = wholeCommand.length();
        for (int i = 0; i < length; i++) {
            char lastCharacter = wholeCommand.charAt(wholeCommand.length() - 1);
            if (Character.toString(lastCharacter).equals(" ")) {
                wholeCommand = wholeCommand.substring(0, wholeCommand.length() - 1);
            }
        }

        return wholeCommand;
    }
}