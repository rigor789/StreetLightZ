package me.rigor789.StreetLightZ;

import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by rigor789 on 2014.06.22..
 */
public class SessionManager {

    private ArrayList<EditSession> sessions;

    private SessionManager() {
        this.sessions = new ArrayList<EditSession>();
    }

    public static SessionManager instance;

    public static SessionManager getInstance() {
        if(instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public EditSession createSession(Player player) {
        EditSession session = getSession(player);
        if(session == null) {
            session =  new EditSession(player);
            sessions.add(session);
            return session;
        } else {
            return session;
        }
    }

    public EditSession getSession(Player player) {
        for(EditSession session : this.sessions) {
            if(session.getPlayer() == player) return session;
        }
        return null;
    }

    public void removeSession(EditSession session) {
        if(session == null) return;
        if(!sessions.contains(session)) return;
        this.sessions.remove(session);
    }

    public void removeSession(Player player) {
        EditSession session = getSession(player);
        if(session == null) return;
        if(!sessions.contains(session)) return;
        this.sessions.remove(session);
    }

    public boolean hasSession(Player player) {
        return getSession(player) != null;
    }

}
