package org.ranin.TrueDestiny.job;

/*
author: "chibbi"
description: "Initiator of the whole Plugin"
TODO: ["interfaces for other classes", "create,load and unload DB", "create,select,read,save stuff in DB"]
sources:
    mysqlCommands: "http://g2pc1.bu.edu/~qzpeng/manual/MySQL%20Commands.htm"
    mysql: "https://www.vogella.com/tutorials/MySQLJava/article.html"
    sqlite: "https://www.sqlitetutorial.net/sqlite-java/"
*/

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

public class Sql {

    private Connection conn = null;

    private String uuidUlr = "https://api.mojang.com/users/profiles/minecraft/";

    private String playerLoc = "jdbc:sqlite:plugins/rolePlay/db/Player.db";
    private String table = "playerjobs";

    public Sql(String name) {
        table = name;
        if (!connect()) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "shutdown");
        }
    }

    private boolean connect() {
        File dir = new File("plugins/rolePlay/db/");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            conn = DriverManager.getConnection(playerLoc);

            return true;
        } catch (SQLException e) {
            System.out.println("\033[31mCould not connect to Player database\033[39m at: " + playerLoc);
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
            return true;
        } catch (SQLException e) {
            System.out.println("\033[31mCould not disconnect to Player database\033[39m");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean createJobTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + table + " (\n" + "  player_id integer PRIMARY KEY,\n"
                + " player text NOT NULL UNIQUE,\n" + "    job text NOT NULL,\n" + "    job_xp integer\n" + ");";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            disconnect();
            return true;
        } catch (SQLException e) {
            System.out.println("\033[31mCould not create a table (" + table + ") on Player database\033[39m");
            System.out.println(e.getMessage());
        }
        disconnect();
        return false;
    }

    public boolean addtoJobTable(String player, String job) {
        String sql = "INSERT INTO " + table + "(player,job) VALUES(?,?);";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, getUuid(player));
            pstmt.setString(2, job);
            pstmt.executeUpdate();
            disconnect();
            return true;
        } catch (SQLException e) {
            System.out.println("\033[31mCould not add " + player + " with " + job + " to " + table + " on " + table
                    + " database\033[39m");
            System.out.println(e.getMessage());
        }
        disconnect();
        return false;
    }

    public String[] readfromJobTable(String player) {
        String[] res = new String[8];
        String sql = "SELECT * FROM " + table + " WHERE player=?;";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, getUuid(player));
            ResultSet rs = pstmt.executeQuery();
            res[0] = rs.getString("job");
            res[1] = String.valueOf(rs.getInt("job_xp"));
            disconnect();
        } catch (SQLException e) {
            res[0] = null;
        }
        disconnect();
        return res;
    }

    public void UpdateJobinJobTable(String player, String job) {
        String sql = "UPDATE " + table + " SET job = ? WHERE player=?;";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, job);
            pstmt.setString(2, getUuid(player));
            pstmt.executeUpdate();
            disconnect();
        } catch (SQLException e) {
            System.out.println(
                    "\033[31mCould not update Column 1 at player = " + player + " on " + table + " database\033[39m");
            System.out.println(e.getMessage());
        }
        disconnect();
    }

    public void UpdateXpinJobTable(String player, int xp) {
        // TODO: remove this debug message
        System.out.println(player + " gets " + xp + " xp!");
        String sql = "UPDATE " + table + " SET job_xp = ? WHERE player=?;";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, xp);
            pstmt.setString(2, getUuid(player));
            pstmt.executeUpdate();
            disconnect();
        } catch (SQLException e) {
            System.out.println(
                    "\033[31mCould not update Column 2 at player = " + player + " on " + table + " database\033[39m");
            System.out.println(e.getMessage());
        }
        disconnect();
    }

    public void AddXp(String player, int i) {
        player = getUuid(player);
        UpdateXpinJobTable(player, i);
    }

    public boolean deletefromJobTable(String player) {
        String sql = "DELETE FROM " + table + " WHERE player = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, getUuid(player));
            pstmt.executeUpdate();
            disconnect();
            return true;
        } catch (SQLException e) {
            System.out.println("\033[31mCould not delete " + player + " in " + table + " database\033[39m");
            System.out.println(e.getMessage());
        }
        disconnect();
        return false;
    }

    public String getUuid(String player) {
        // TODO: just split on " or on : and remove before and after UUID
        return (player);
    }
}
