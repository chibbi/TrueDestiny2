package org.ranin.TrueDestiny.job;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

/*
author: "chibbi"
*/

public class Sql {

    private Connection conn = null;

    private String uuidUlr = "https://api.mojang.com/users/profiles/minecraft/";

    private String playerLoc = "jdbc:sqlite:plugins/TrueDestiny/db/Player.db"; // TODO: Make Configurable
    private String table = "playerjobs";

    public Sql(String name) {
        table = name;
        if (!connect()) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "say HUUGE SERVER ERROORRR");
        }
    }

    private boolean connect() {
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

    public boolean createTable() {
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

    public boolean addtoTable(String player, String job) {
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

    public String[] readfromTable(String player) {
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
        System.out.println(player + " has now " + xp + " xp!");
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

    public void SetXp(String player, int i) {
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
        /*
         * THE UUID PARSING (NOT USED, because INEFFICIENT, mojang api blocks you after
         * a few minutes) TODO: Make this actually efficient (a cache or smth, the
         * server already has smth like that jsut look up how to use it) String uuidUlr
         * = "https://api.mojang.com/users/profiles/minecraft/" + player; String respo =
         * null; try { URL url = new URL(uuidUlr); HttpURLConnection con =
         * (HttpURLConnection) url.openConnection(); con.setRequestMethod("GET");
         * con.setConnectTimeout(500); con.setReadTimeout(500);
         * 
         * int status = con.getResponseCode();
         * 
         * if (status >= 400) { System.out.println("[TrueDestiny] " +
         * "Error with connection with Mojang API !!"); con.disconnect(); return player;
         * } BufferedReader in = new BufferedReader(new
         * InputStreamReader(con.getInputStream())); String inputLine; StringBuffer
         * content = new StringBuffer(); while ((inputLine = in.readLine()) != null) {
         * content.append(inputLine); } respo = content.toString(); in.close();
         * con.disconnect(); if (respo == null) { System.out.println("[TrueDestiny] " +
         * "Mojan API returned no INFO !!"); return player; } respo =
         * respo.split(":")[2].replace("\"", "").replace("}", ""); return respo;
         * 
         * } catch (IOException e) { System.out.println("[TrueDestiny] " +
         * "Connection Error: likely lost internet connection. if multiple errors pop up, please look that your connection is stable\n"
         * + "             You can contact the developer if you have problems."); }
         * return player;
         */
        return player;
    }
}
