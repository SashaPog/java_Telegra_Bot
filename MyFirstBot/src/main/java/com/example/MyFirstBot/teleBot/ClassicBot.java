package com.example.MyFirstBot.teleBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ClassicBot extends TelegramLongPollingBot {


    @Override
    public String getBotUsername() {
        return "PA_sasha_bot";
    }

    @Override
    public String getBotToken() {
        return "5049493548:AAEW5-9ZPSdbwSwMPwzV5J3iGrizziE65xk";
    }

    private final static List<String> draw = new ArrayList<>() {{
        add("010");
        add("111");
        add("212");
    }};
    private final static List<String> senderWins = new ArrayList<>() {{
        add("011");
        add("112");
        add("210");
    }};
    private final static List<String> OponentWins = new ArrayList<>() {{
        add("110");
        add("211");
        add("012");
    }};

    @Override
    public void onUpdateReceived(Update update) {
        CS21 cs = new CS21();
        KeyboardHendler keyboardHendler = new KeyboardHendler();
        if (update.hasMessage()){
            Message message = update.getMessage();
            SendMessage sendMessage = new SendMessage();
            if (message.hasText()) {
                 if (message.getText().equals("/game")){
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setText("choose one of the variant!");
                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
                    keyboard.add(
                            Collections.singletonList(
                                    InlineKeyboardButton.builder()
                                            .text("✊")
                                            .callbackData("0")
                                            .build()));
                    keyboard.add(
                            Collections.singletonList(
                                    InlineKeyboardButton.builder()
                                            .text("✌")
                                            .callbackData("1")
                                            .build()));
                    keyboard.add(
                            Collections.singletonList(
                                    InlineKeyboardButton.builder()
                                            .text("\uD83E\uDD1A")
                                            .callbackData("2")
                                            .build()));
                    inlineKeyboardMarkup.setKeyboard(keyboard);
                    sendMessage.setReplyMarkup(inlineKeyboardMarkup);

                }
                if (message.getText().equals("/start")) {
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.enableMarkdown(true);
                    ReplyKeyboardMarkup keyboardMarkup = keyboardHendler.startKeyboard();
                    sendMessage.setReplyMarkup(keyboardMarkup);
                } else if (message.getText().equals("Back")) {
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setText("Back");
                    sendMessage.enableMarkdown(true);
                    ReplyKeyboardMarkup keyboardMarkup = keyboardHendler.startKeyboard();
                    sendMessage.setReplyMarkup(keyboardMarkup);
                }

                if (message.getText().equals("listIT")) {
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setText("listIT");
                    sendMessage.enableMarkdown(true);
                    ReplyKeyboardMarkup keyboardMarkup = keyboardHendler.getMenuKeyboard();
                    sendMessage.setReplyMarkup(keyboardMarkup);

                } else if (message.getText().equals("КН-21")) {
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setText(cs.kn21());
                } else if (message.getText().equals("КН-22")) {
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setText(cs.kn22());
                } else if (message.getText().equals("teacher")) {
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setText(cs.teacher());
                } else if (message.getText().equals("all Students")) {
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setText(cs.AllStudents());
                } else if (message.getText().equals("/start")) {
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setText("/start");
                }

                if (message.getText().equals("About Bot")) {
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    sendMessage.setText("Hello\n" +
                            "i'm a bot \n" +
                            "created by Alexander\n" +
                            "on January 7, 2022\n");
                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
                    keyboard.add(
                            Collections.singletonList(
                                    InlineKeyboardButton.builder()
                                            .text("Instagram")
                                            .callbackData("Instagram")
                                            .url("https://www.instagram.com/pohranichniy/")
                                            .build()));
                    keyboard.add(
                            Collections.singletonList(
                                    InlineKeyboardButton.builder()
                                            .text("YouTube")
                                            .callbackData("YouTube")
                                            .url("https://www.youtube.com/")
                                            .build()));
                    inlineKeyboardMarkup.setKeyboard(keyboard);
                    sendMessage.setReplyMarkup(inlineKeyboardMarkup);

                }
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } else if (update.hasCallbackQuery()){
            CallbackQuery callbackQuery = update.getCallbackQuery();

            String Sch = callbackQuery.getData();
            String SN = callbackQuery.getFrom().getFirstName();

            if (callbackQuery.getData().equals("0") || callbackQuery.getData().equals("1") || callbackQuery.getData().equals("2") ){
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(String.valueOf(callbackQuery.getMessage().getChatId()));
                sendMessage.setText("Oponent Choose" );
                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
                keyboard.add(
                        Collections.singletonList(
                                InlineKeyboardButton.builder()
                                        .text("✊")
                                        .callbackData(String.format("%s %s %s",Sch, SN, "10"))
                                        .build()));
                keyboard.add(
                        Collections.singletonList(
                                InlineKeyboardButton.builder()
                                        .text("✌")
                                        .callbackData(String.format("%s %s %s",Sch, SN, "11"))
                                        .build()));
                keyboard.add(
                        Collections.singletonList(
                                InlineKeyboardButton.builder()
                                        .text("\uD83E\uDD1A")
                                        .callbackData(String.format("%s %s %s",Sch, SN, "12"))
                                        .build()));
                inlineKeyboardMarkup.setKeyboard(keyboard);
                sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            String[] data = callbackQuery.getData().split(" ");
            if (data.length < 3) {
                return;
            }

            String SenderChoose = data[0];
            String SenderName = data[1];
            String OponentChoose = data[2];
            String OponentName = callbackQuery.getFrom().getFirstName();

            System.out.println(SenderChoose +"  "+ OponentChoose);
             if (draw.contains(SenderChoose + OponentChoose)){
                 String gameText = "draw";
                Integer messageId = callbackQuery.getMessage().getMessageId();
                EditMessageText editMessageText = new EditMessageText();
                editMessageText.setChatId(String.valueOf(callbackQuery.getMessage().getChatId()));
                editMessageText.setMessageId(messageId);
                editMessageText.setText(gameText);
                 try {
                     execute(editMessageText);
                 } catch (TelegramApiException e) {
                    e.printStackTrace();
                 }
             }else if(senderWins.contains(SenderChoose + OponentChoose)){
                 String gameText = String.format("%s!  wins from %s", SenderName, OponentName);
                 Integer messageId = callbackQuery.getMessage().getMessageId();
                 EditMessageText editMessageText = new EditMessageText();
                 editMessageText.setChatId(String.valueOf(callbackQuery.getMessage().getChatId()));
                 editMessageText.setMessageId(messageId);
                 editMessageText.setText(gameText);
                 try {
                     execute(editMessageText);
                 } catch (TelegramApiException e) {
                     e.printStackTrace();
                 }
             }
             else if (OponentWins.contains(SenderChoose + OponentChoose)){
                 String gameText = String.format("%s wins from %s! ", OponentName, SenderName);
                 Integer messageId = callbackQuery.getMessage().getMessageId();
                 EditMessageText editMessageText = new EditMessageText();
                 editMessageText.setChatId(String.valueOf(callbackQuery.getMessage().getChatId()));
                 editMessageText.setMessageId(messageId);
                 editMessageText.setText(gameText);
                 try {
                     execute(editMessageText);
                 } catch (TelegramApiException e) {
                     e.printStackTrace();
                 }
             }

        }
    }
}
