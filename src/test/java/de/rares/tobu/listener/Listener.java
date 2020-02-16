package de.rares.tobu.listener;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

import javax.annotation.Nonnull;

public class Listener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent e) {
        Guild g = e.getGuild();
        String msg = e.getMessage().getContentRaw();
        Member m = e.getMember();

        if(msg.equalsIgnoreCase("/bewerben")){
                    String name = m.getNickname();
                     g.createTextChannel(name).setParent(g.getCategoriesByName("Bewerbungen",true).stream().findFirst().get()).queue();
                    TextChannel c = g.getTextChannelsByName(name, false).stream().findFirst().get();
            c.createPermissionOverride(m).deny(Permission.MESSAGE_WRITE).queue();
            c.createPermissionOverride(m).deny(Permission.ALL_PERMISSIONS);

        }

    }
}
