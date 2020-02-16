package de.rares.tobu;

import de.rares.tobu.listener.Listener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;


public class Tobu {
    public static ArrayList<TextChannel> channels = new ArrayList<>();
    public static void main(String[] args) {

        try {
           JDA jda = new JDABuilder("MzYyNjc0OTgzMjI0MjEzNTE0.XkmXHA.lX1JxM6CrAlFf75FB3Wi-mTf9us").setActivity(Activity.playing("a")).addEventListeners(new Listener()).build();

        } catch (LoginException e) {
            e.printStackTrace();
        }

    }

}
