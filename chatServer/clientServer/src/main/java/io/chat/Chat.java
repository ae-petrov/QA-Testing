package io.chat;

import com.mashape.unirest.http.Unirest;
import io.javalin.Javalin;
import io.javalin.websocket.WsConnectContext;
import j2html.TagCreator;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static j2html.TagCreator.*;

public class Chat {
    public static Map<WsConnectContext, String> userUsernameMap = new ConcurrentHashMap();

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
           config.addStaticFiles("/public");
        }).start(7071);

        app.ws("/chat", ws -> {
            ws.onConnect(session -> {
                String userName = "User"+ Unirest.get("http://localhost:7070/hashcode").asString().getBody();
                userUsernameMap.put(session, userName);
                broadcastMessage("Server", userName + " joined our chat.");
            });
            ws.onClose(session -> {
                String userName = userUsernameMap.get(session);
                userUsernameMap.remove(userName);
                broadcastMessage(("Server"), userName + "left our chat.");
            });
            ws.onMessage(session -> {
                broadcastMessage(userUsernameMap.get(session), session.message());
            });

        });
        }

    public static String createHTMLMessageFromSender(String sender, String message) {
        return TagCreator.article(
                b(sender + " says:"),
                span(attrs(".timestamp"), new SimpleDateFormat("hh:mm:ss").format(new Date())),
                p(message)
        ).render();
    }

    private static void broadcastMessage(String sender, String message) {
        userUsernameMap.keySet().stream().filter(ctx -> ctx.session.isOpen()).forEach(session -> {
            session.send(
                    new JSONObject()
                        .put("userMessage", createHTMLMessageFromSender(sender, message))
                        .put("userlist", userUsernameMap.values()).toString());
        });
    }
}
