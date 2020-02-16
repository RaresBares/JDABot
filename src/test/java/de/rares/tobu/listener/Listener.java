package de.rares.tobu.listener;

import de.rares.tobu.Tobu;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.UUID;

public class Listener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent e) {
        Guild g = e.getGuild();
        String msg = e.getMessage().getContentRaw();
        Member m = e.getMember();
        TextChannel tc = e.getTextChannel();

        if (!Tobu.channels.contains(tc)) {


            if (msg.equalsIgnoreCase("/bewerben")) {
                String name = m.getNickname() == null ? "abc" : m.getNickname().toLowerCase();
                e.getMessage().addReaction(":thumbsup:");

                e.getMessage().delete().queue();

                if (g.getTextChannelsByName("bewerbung-" + name, true).size() == 0) {

                    g.createTextChannel("bewerbung-" + name).setParent(g.getCategoriesByName("bewerbungen", true)
                            .get(0))
                            .addPermissionOverride(m, null, Collections.singleton(Permission.MESSAGE_WRITE))
                            .queue(c -> {
                                Tobu.channels.add(c);

                                for (Member member : g.getMembers()) {
                                    if (!member.getRoles().contains(g.getRolesByName("Bewerbung", true))) {
                                        if (member != c) {
                                            try {
                                                c.createPermissionOverride(member).deny(Permission.VIEW_CHANNEL).queue();

                                            } catch (Exception ex) {

                                            }

                                        }

                                    }
                                }
                                Message mes = new MessageBuilder()
                                        .append("Lieber @" + m.getUser().getAsTag() + ",\n")
                                        .append("Es wird sich ein Teamler um dich kümmern... Bitte habe etwas geduld!")
                                        .build();
                                c.sendMessage(mes).queue();


                            });

                } else {
                    m.getUser().openPrivateChannel().queue(c -> c.sendMessage("Du hast bereits einen BewerbungsChannel geöffnet"));
                }

            }
        }else{
            if(msg.equalsIgnoreCase("quit")){
                e.getMessage().addReaction(":thumbsup:");
                tc.delete().queue();
            }
        }
    }
}
