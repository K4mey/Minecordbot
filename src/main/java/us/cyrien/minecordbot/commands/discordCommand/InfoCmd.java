package us.cyrien.minecordbot.commands.discordCommand;

import us.cyrien.jdautilities.commandclient.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import org.bukkit.Bukkit;
import us.cyrien.minecordbot.Minecordbot;
import us.cyrien.minecordbot.commands.MCBCommand;
import us.cyrien.minecordbot.configuration.MCBConfig;
import us.cyrien.minecordbot.localization.Locale;

public class InfoCmd extends MCBCommand {

    public InfoCmd(Minecordbot minecordbot) {
        super(minecordbot);
        this.name = "info";
        this.aliases = new String[]{"inf", "in"};
        this.help = Locale.getCommandsMessage("info.description").finish();
        this.category = minecordbot.INFO;
        this.type = Type.EMBED;
    }

    @Override
    protected void doCommand(CommandEvent e) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        String path = "info.minfo.";
        int textChannelCount = e.getGuild().getTextChannels().size();
        int voiceChannelCount = e.getGuild().getVoiceChannels().size();
        String clientID = MCBConfig.get("client_id");
        String botName = e.getJDA().getSelfUser().getName();
        Guild guild = e.getGuild();
        Member member = guild.getMember(e.getJDA().getSelfUser());
        String nickName = (Locale.getCommandsMessage(path + "nonick").finish());
        if (e.getGuild().getMember(e.getJDA().getSelfUser()).getNickname() != null)
            nickName = e.getGuild().getMember(e.getJDA().getSelfUser()).getNickname();
        String mcbInfo = "\n" + Locale.getCommandsMessage(path + "version").finish() + ": " + Bukkit.getPluginManager().getPlugin("MineCordBot").getDescription().getVersion() +
                "\n" + Locale.getCommandsMessage(path + "textchannel").finish() + ": " + textChannelCount +
                "\n" + Locale.getCommandsMessage(path + "voicechannel").finish() + ": " + voiceChannelCount +
                "\n" + Locale.getCommandsMessage(path + "uptime").finish() + ": " + getMinecordbot().getUpTime();
        String botInfo = "\n" + Locale.getCommandsMessage(path + "clientid").finish() + ": " + clientID +
                "\n" + Locale.getCommandsMessage(path + "botname").finish() + ": " + botName +
                "\n" + Locale.getCommandsMessage(path + "botnick").finish() + ": " + nickName;
        embedBuilder.setColor(member.getColor());
        embedBuilder.setThumbnail("https://media-elerium.cursecdn.com/attachments/thumbnails/124/611/310/172/minecord.png");
        embedBuilder.setAuthor("MineCordBot", "https://dev.bukkit.org/projects/minecordbot-bukkit", "https://media-elerium.cursecdn.com/attachments/thumbnails/124/611/310/172/minecord.png" );
        //embedBuilder.addBlankField(false);
        embedBuilder.addField(Locale.getCommandsMessage(path + "generalinfo_header").finish(), mcbInfo, false);
        embedBuilder.addField(Locale.getCommandsMessage(path + "botinfo_header").finish(), botInfo, false);
        //embedBuilder.addBlankField(false);
        embedBuilder.setFooter("- C Y R I \u039E N -", "https://yt3.ggpht.com/-uuXItiIhgcU/AAAAAAAAAAI/AAAAAAAAAAA/3xzbfTTz9oU/s88-c-k-no-mo-rj-c0xffffff/photo.jpg");
        respond(e, embedBuilder.build());
    }
}