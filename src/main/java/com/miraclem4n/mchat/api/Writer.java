package com.miraclem4n.mchat.api;

import com.miraclem4n.mchat.configs.InfoUtil;
import com.miraclem4n.mchat.types.InfoType;
import com.miraclem4n.mchat.types.config.ConfigType;

public class Writer {
    /**
     * Used to set the Base values of an InfoType.
     * @param type Type of Base you want to set.
     * @param name Defining value of the base(Also known as Name).
     */
    public static void addBase(String name, InfoType type) {
        String base = type.getName();

        if (type.equals(InfoType.USER)) {
            InfoUtil.set(base + "." + name + ".group", ConfigType.INFO_DEFAULT_GROUP.getString());
        }

        InfoUtil.set(base + "." + name + ".info.prefix", "");
        InfoUtil.set(base + "." + name + ".info.suffix", "");

        save();

        if (type.equals(InfoType.USER)) {
            setDGroup(ConfigType.INFO_DEFAULT_GROUP.getString());
        }
    }

    /**
     * Used to add the Base for a Player with a custom DefaultGroup.
     * @param player Player's name.
     * @param group Default Group to set to the Base(Only needed if doing for InfoType.USER).
     * @param createBlank Whether or not to create blank prefix / suffix.
     */
    public static void addBase(String player, String group, Boolean createBlank) {
        InfoUtil.set("users." + player + ".group", group);

        if (createBlank) {
            InfoUtil.set("users." + player + ".info.prefix", "");
            InfoUtil.set("users." + player + ".info.suffix", "");
        }

        save();

        setDGroup(group);
    }

    /**
     * Used to add a World to a Base.
     * @param name Defining value of the Base(Also known as name).
     * @param type Type of Base you want to set.
     * @param world Name of the World you are trying to add.
     */
    public static void addWorld(String name, InfoType type, String world) {
        String base = type.getName();

        if (!InfoUtil.getConfig().isSet(base + "." + name)) {
            addBase(name, type);
        }

        InfoUtil.set(base + "." + name + ".worlds." + world + "prefix", "");
        InfoUtil.set(base + "." + name + ".worlds." + world + "suffix", "");

        save();
    }

    /**
     * Used to add an Info Variable to a Base.
     * @param name Defining value of the Base(Also known as name).
     * @param type Type of Base you want to set.
     * @param var Name of the Variable you are trying to add.
     * @param value Value of the Variable you are trying to add.
     */
    public static void setInfoVar(String name, InfoType type, String var, Object value) {
        String base = type.getName();

        if (!InfoUtil.getConfig().isSet(base + "." + name)) {
            addBase(name, type);
        }

        InfoUtil.set(base + "." + name + ".info." + var, value);

        save();
    }

    /**
     * Used to add a World Variable to a Base.
     * @param name Defining value of the Base(Also known as name).
     * @param type Type of Base you want to set.
     * @param world Name of the World you are trying to add the Variable to.
     * @param var Name of the Variable you are trying to add.
     * @param value Value of the Variable you are trying to add.
     */
    public static void setWorldVar(String name, InfoType type, String world, String var, Object value) {
        String base = type.getName();

        if (!InfoUtil.getConfig().isSet(base + "." + name + ".worlds." + world)) {
            addWorld(name, type, world);
        }

        InfoUtil.set(base + "." + name + ".worlds." + world + "." + var, value);

        save();
    }

    /**
     * Used to set the Group of a Player.
     * @param player Player's name.
     * @param group Group to be set to Player.
     */
    public static void setGroup(String player, String group) {
        if (!InfoUtil.getConfig().isSet(player + "." + group)) {
            addBase(player, group, false);
        }

        InfoUtil.set("users." + player + ".group", group);

        save();
    }

    /**
     * Used to remove a Base.
     * @param name Defining value of the Base(Also known as name).
     * @param type Type of Base you want to remove.
     */
    public static void removeBase(String name, InfoType type) {
        String base = type.getName();

        if (InfoUtil.getConfig().isSet(base + "." + name)) {
            InfoUtil.set(base + "." + name, null);

            save();
        }
    }

    /**
     * Used to remove an Info Variable from a Base.
     * @param name Defining value of the Base(Also known as name).
     * @param type Type of Base you want to remove from.
     * @param var Name of the Variable you are trying to remove.
     */
    public static void removeInfoVar(String name, InfoType type, String var) {
        setInfoVar(name, type, var, null);
    }

    /**
     * Used to remove a World from a Base.
     * @param name Defining value of the Base(Also known as name).
     * @param type Type of Base you want to remove from.
     * @param world Name of the World you are trying to remove.
     */
    public static void removeWorld(String name, InfoType type, String world) {
        String base = type.getName();

        if (InfoUtil.getConfig().isSet(base + "." + name)
                && InfoUtil.getConfig().isSet(base + "." + name + ".worlds." + world)) {
            InfoUtil.set(base + "." + name + ".worlds." + world, null);

            save();
        }
    }

    /**
     * Used to remove a World Variable from a Base.
     * @param name Defining value of the Base(Also known as name).
     * @param type Type of Base you want to remove from.
     * @param world Name of the World you are trying to remove from.
     * @param var Name of the Variable you are trying to remove.
     */
    public static void removeWorldVar(String name, InfoType type, String world, String var) {
        setWorldVar(name, type, world, var, null);
    }

    private static void setDGroup(String group) {
        if (!InfoUtil.getConfig().isSet("groups." + group)) {
            InfoUtil.set("groups." + group + ".info.prefix", "");
            InfoUtil.set("groups." + group + ".info.suffix", "");

            save();
        }
    }

    private static void save() {
        InfoUtil.save();
    }
}
