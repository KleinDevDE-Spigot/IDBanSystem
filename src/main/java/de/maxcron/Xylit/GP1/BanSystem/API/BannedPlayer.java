package de.maxcron.Xylit.GP1.BanSystem.API;

import org.bukkit.OfflinePlayer;

import java.util.UUID;

/**
 * ===============================
 * GastPlugin - BanSystem - FlexDE
 * Created by Xylit
 * 2017
 * ==============================
 */
public class BannedPlayer {

    private OfflinePlayer offlinePlayer;
    private String name;
    private UUID uuid;
    private Integer banID;
    private String banReason;
    private String creator;
    private Long time;

    BannedPlayer(OfflinePlayer offlinePlayer, String name, UUID uuid, String banReason, String creator, Integer banID, Long time){
        this.offlinePlayer = offlinePlayer;
        this.name = name;
        this.uuid = uuid;
        this.banReason = banReason;
        this.creator = creator;
        this.banID = banID;
        this.time = time;
    }

    public String getName(){
        return name;
    }

    public UUID getUUID(){
        return uuid;
    }

    public String getBanReason(){
        return banReason;
    }

    public Integer getBanID(){
        return banID;
    }

    public OfflinePlayer getOfflinePlayer(){
        return offlinePlayer;
    }

    public String getCreator(){
        return creator;
    }

    public Long getTimeinMilli(){
        return time;
    }

}