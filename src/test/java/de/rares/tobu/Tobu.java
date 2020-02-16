package de.rares.tobu;

import de.rares.tobu.listener.Listener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;


public class Tobu {

    public static void main(String[] args) {
        JDA jda;
        try {
            jda = new JDABuilder("MzYyNjc0OTgzMjI0MjEzNTE0.Xixq7w.aOXsdykmGgqyF6nLJXvdI8SkcBY")
                    .addEventListeners(new Listener())
                    .setActivity(Activity.playing("moderation-Bot"))
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }

}
