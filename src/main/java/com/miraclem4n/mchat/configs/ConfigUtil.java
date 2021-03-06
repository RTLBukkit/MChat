package com.miraclem4n.mchat.configs;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ConfigUtil {
    private static YamlConfiguration config;
    private static File file;
    private static Boolean changed;

    private static ArrayList<String> meAliases = new ArrayList<String>();
    //private static ArrayList<String> userAliases = new ArrayList<String>();
    //private static ArrayList<String> groupAliases = new ArrayList<String>();

    private static HashMap<String, List<String>> aliasMap = new HashMap<String, List<String>>();

    public static void initialize() {
        load();
    }

    public static void dispose() {
        config = null;
        file = null;
        changed = null;
        aliasMap = null;
    }

    public static void load() {
        file = new File("plugins/MChat/config.yml");

        config = YamlConfiguration.loadConfiguration(file);

        config.options().indent(4);
        config.options().header("MChat Config");

        changed = false;

        loadDefaults();
    }

    private static void loadDefaults() {
        removeOption("auto-Changed");
        removeOption("mchat.suppressMessages");

        removeOption("mchat.enableList");

        editOption("mchat-message-format", "format.chat");
        editOption("mchat-API-only", "mchat.apiOnly");
        editOption("mchat-format-events", "mchat.formatEvents");
        editOption("mchat-chat-distance", "mchat.chatDistance");
        editOption("mchat-info-only", "info.useNewInfo");
        editOption("mchat-oldNodes-only", "info.useOldNodes");
        editOption("mchat-add-info-players", "info.addNewPlayers");

        editOption("mchat.formatEvents", "mchat.alter.events");

        editOption("mchat.alterEvents", "mchat.alter.events");
        editOption("mchat.alterDeathMessages", "mchat.alter.death");

        checkOption("mchat.apiOnly", false);
        checkOption("mchat.alter.events", true);
        checkOption("mchat.alter.death", true);
        checkOption("mchat.chatDistance", -1.0);
        checkOption("mchat.varIndicator", "+");
        checkOption("mchat.cusVarIndicator", "-");
        checkOption("mchat.localeVarIndicator", "%");
        checkOption("mchat.spout", true);
        checkOption("mchat.IPCensor", true);
        checkOption("mchat.cLockRange", 3);

        checkOption("suppress.useDeath", false);
        checkOption("suppress.useJoin", false);
        checkOption("suppress.useKick", false);
        checkOption("suppress.useQuit", false);
        checkOption("suppress.maxDeath", 30);
        checkOption("suppress.maxJoin", 30);
        checkOption("suppress.maxKick", 30);
        checkOption("suppress.maxQuit", 30);

        checkOption("info.useNewInfo", false);
        checkOption("info.useLeveledNodes", false);
        checkOption("info.useOldNodes", false);
        checkOption("info.addNewPlayers", false);
        checkOption("info.defaultGroup", "default");

        loadAliases();

        checkOption("aliases.mchatme", meAliases);
        //checkOption("aliases.mchatuser", userAliases);
        //checkOption("aliases.mchatgroup", groupAliases);

        setupAliasMap();

        save();
    }

    public static void set(String key, Object obj) {
        config.set(key, obj);

        changed = true;
    }

    public static Boolean save() {
        if (!changed) {
            return false;
        }

        try {
            config.save(file);
            changed = false;
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public static YamlConfiguration getConfig() {
        return config;
    }

    public static HashMap<String, List<String>> getAliasMap() {
        return aliasMap;
    }

    private static void checkOption(String option, Object defValue) {
        if (!config.isSet(option)) {
            set(option, defValue);
        }
    }

    private static void editOption(String oldOption, String newOption) {
        if (config.isSet(oldOption)) {
            set(newOption, config.get(oldOption));
            set(oldOption, null);
        }
    }

    private static void removeOption(String option) {
        if (config.isSet(option)) {
            set(option, null);
        }
    }

    private static void loadAliases() {
        meAliases.add("me");

        //userAliases.add("muser");
        //groupAliases.add("mgroup");
    }

    private static void setupAliasMap() {
        Set<String> keys = config.getConfigurationSection("aliases").getKeys(false);

        for (String key : keys) {
            aliasMap.put(key, config.getStringList("aliases." + key));
        }
    }
}
