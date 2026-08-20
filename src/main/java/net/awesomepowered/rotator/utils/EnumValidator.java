package net.awesomepowered.rotator.utils;

import org.bukkit.Effect;
import org.bukkit.Particle;
import org.bukkit.Sound;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class EnumValidator {

    private static final Map<String, String> SOUND_ALIASES = new HashMap<>();
    private static final Map<String, String> EFFECT_ALIASES = new HashMap<>();
    private static final Map<String, String> PARTICLE_ALIASES = new HashMap<>();

    static {
        // ==========================================
        // SOUND ALIASES
        // ==========================================

        // RECORD_* → MUSIC_DISC_* (1.19.3)
        SOUND_ALIASES.put("RECORD_11", "MUSIC_DISC_11");
        SOUND_ALIASES.put("RECORD_13", "MUSIC_DISC_13");
        SOUND_ALIASES.put("RECORD_5", "MUSIC_DISC_5");
        SOUND_ALIASES.put("RECORD_BLOCKS", "MUSIC_DISC_BLOCKS");
        SOUND_ALIASES.put("RECORD_CAT", "MUSIC_DISC_CAT");
        SOUND_ALIASES.put("RECORD_CHIRP", "MUSIC_DISC_CHIRP");
        SOUND_ALIASES.put("RECORD_FAR", "MUSIC_DISC_FAR");
        SOUND_ALIASES.put("RECORD_MALL", "MUSIC_DISC_MALL");
        SOUND_ALIASES.put("RECORD_MELLOHI", "MUSIC_DISC_MELLOHI");
        SOUND_ALIASES.put("RECORD_STAL", "MUSIC_DISC_STAL");
        SOUND_ALIASES.put("RECORD_STRAD", "MUSIC_DISC_STRAD");
        SOUND_ALIASES.put("RECORD_WAIT", "MUSIC_DISC_WAIT");
        SOUND_ALIASES.put("RECORD_WARD", "MUSIC_DISC_WARD");
        SOUND_ALIASES.put("RECORD_OTHERSIDE", "MUSIC_DISC_OTHERSIDE");
        SOUND_ALIASES.put("RECORD_PIGSTEP", "MUSIC_DISC_PIGSTEP");
        SOUND_ALIASES.put("RECORD_RELIC", "MUSIC_DISC_RELIC");

        // 1.8 NOTE_* → BLOCK_NOTE_BLOCK_*
        SOUND_ALIASES.put("NOTE_PLING", "BLOCK_NOTE_BLOCK_PLING");
        SOUND_ALIASES.put("NOTE_PIANO", "BLOCK_NOTE_BLOCK_HARP");
        SOUND_ALIASES.put("NOTE_BASS", "BLOCK_NOTE_BLOCK_BASS");
        SOUND_ALIASES.put("NOTE_BASS_DRUM", "BLOCK_NOTE_BLOCK_BASEDRUM");
        SOUND_ALIASES.put("NOTE_SNARE_DRUM", "BLOCK_NOTE_BLOCK_SNARE");
        SOUND_ALIASES.put("NOTE_STICKS", "BLOCK_NOTE_BLOCK_HAT");

        // 1.9-1.19.2 BLOCK_NOTE_* → BLOCK_NOTE_BLOCK_* (1.19.3)
        SOUND_ALIASES.put("BLOCK_NOTE_HARP", "BLOCK_NOTE_BLOCK_HARP");
        SOUND_ALIASES.put("BLOCK_NOTE_BASS", "BLOCK_NOTE_BLOCK_BASS");
        SOUND_ALIASES.put("BLOCK_NOTE_PIANO", "BLOCK_NOTE_BLOCK_HARP");
        SOUND_ALIASES.put("BLOCK_NOTE_GUITAR", "BLOCK_NOTE_BLOCK_GUITAR");
        SOUND_ALIASES.put("BLOCK_NOTE_CHIME", "BLOCK_NOTE_BLOCK_CHIME");
        SOUND_ALIASES.put("BLOCK_NOTE_FLUTE", "BLOCK_NOTE_BLOCK_FLUTE");
        SOUND_ALIASES.put("BLOCK_NOTE_BELL", "BLOCK_NOTE_BLOCK_BELL");
        SOUND_ALIASES.put("BLOCK_NOTE_PLING", "BLOCK_NOTE_BLOCK_PLING");
        SOUND_ALIASES.put("BLOCK_NOTE_HAT", "BLOCK_NOTE_BLOCK_HAT");
        SOUND_ALIASES.put("BLOCK_NOTE_SNARE", "BLOCK_NOTE_BLOCK_SNARE");
        SOUND_ALIASES.put("BLOCK_NOTE_DIDGERIDOO", "BLOCK_NOTE_BLOCK_DIDGERIDOO");
        SOUND_ALIASES.put("BLOCK_NOTE_BIT", "BLOCK_NOTE_BLOCK_BIT");
        SOUND_ALIASES.put("BLOCK_NOTE_BANJO", "BLOCK_NOTE_BLOCK_BANJO");
        SOUND_ALIASES.put("BLOCK_NOTE_IRON_XYLOPHONE", "BLOCK_NOTE_BLOCK_IRON_XYLOPHONE");
        SOUND_ALIASES.put("BLOCK_NOTE_COW_BELL", "BLOCK_NOTE_BLOCK_COW_BELL");
        SOUND_ALIASES.put("BLOCK_NOTE_XYLOPHONE", "BLOCK_NOTE_BLOCK_XYLOPHONE");
        SOUND_ALIASES.put("BLOCK_NOTE_TRUMPET", "BLOCK_NOTE_BLOCK_TRUMPET");
        SOUND_ALIASES.put("BLOCK_NOTE_BASEDRUM", "BLOCK_NOTE_BLOCK_BASEDRUM");

        // 1.8 Unprefixed sound names
        SOUND_ALIASES.put("CLICK", "UI_BUTTON_CLICK");
        SOUND_ALIASES.put("EXPLODE", "ENTITY_GENERIC_EXPLODE");
        SOUND_ALIASES.put("LEVEL_UP", "ENTITY_PLAYER_LEVELUP");
        SOUND_ALIASES.put("ORB_PICKUP", "ENTITY_EXPERIENCE_ORB_PICKUP");
        SOUND_ALIASES.put("FIZZ", "BLOCK_FIRE_EXTINGUISH");
        SOUND_ALIASES.put("FUSE", "ENTITY_TNT_PRIMED");
        SOUND_ALIASES.put("HURT_FLESH", "ENTITY_PLAYER_HURT");
        SOUND_ALIASES.put("ITEM_BREAK", "ENTITY_ITEM_BREAK");
        SOUND_ALIASES.put("ITEM_PICKUP", "ENTITY_ITEM_PICKUP");
        SOUND_ALIASES.put("SHOOT_ARROW", "ENTITY_ARROW_SHOOT");
        SOUND_ALIASES.put("SPLASH", "ENTITY_GENERIC_SPLASH");
        SOUND_ALIASES.put("SWIM", "ENTITY_GENERIC_SWIM");
        SOUND_ALIASES.put("FIRE_IGNITE", "ITEM_FLINTANDSTEEL_USE");

        // 1.11 Flattening prefixes
        SOUND_ALIASES.put("HORSE_GALLOP", "ENTITY_HORSE_GALLOP");
        SOUND_ALIASES.put("HORSE_SADDLE", "ENTITY_HORSE_SADDLE");
        SOUND_ALIASES.put("HORSE_BREATHE", "ENTITY_HORSE_BREATHE");
        SOUND_ALIASES.put("HORSE_ANGRY", "ENTITY_HORSE_ANGRY");
        SOUND_ALIASES.put("HORSE_DEATH", "ENTITY_HORSE_DEATH");
        SOUND_ALIASES.put("HORSE_HURT", "ENTITY_HORSE_HURT");
        SOUND_ALIASES.put("HORSE_LAND", "ENTITY_HORSE_LAND");
        SOUND_ALIASES.put("HORSE_JUMP", "ENTITY_HORSE_JUMP");
        SOUND_ALIASES.put("HORSE_ARMOR", "ENTITY_HORSE_ARMOR");
        SOUND_ALIASES.put("HORSE_EAT", "ENTITY_HORSE_EAT");
        SOUND_ALIASES.put("HORSE_STEP", "ENTITY_HORSE_STEP");
        SOUND_ALIASES.put("HORSE_STEP_WOOD", "ENTITY_HORSE_STEP_WOOD");
        SOUND_ALIASES.put("BAT_TAKEOFF", "ENTITY_BAT_TAKEOFF");

        // ==========================================
        // EFFECT ALIASES
        // ==========================================
        EFFECT_ALIASES.put("DOOR_TOGGLE", null);
        EFFECT_ALIASES.put("IRON_DOOR_TOGGLE", null);
        EFFECT_ALIASES.put("TRAPDOOR_TOGGLE", null);
        EFFECT_ALIASES.put("IRON_TRAPDOOR_TOGGLE", null);
        EFFECT_ALIASES.put("FENCE_GATE_TOGGLE", null);
        EFFECT_ALIASES.put("DOOR_CLOSE", null);
        EFFECT_ALIASES.put("IRON_DOOR_CLOSE", null);
        EFFECT_ALIASES.put("TRAPDOOR_CLOSE", null);
        EFFECT_ALIASES.put("IRON_TRAPDOOR_CLOSE", null);
        EFFECT_ALIASES.put("FENCE_GATE_CLOSE", null);

        // ==========================================
        // PARTICLE ALIASES (1.13 & 1.20.5 Flattening)
        // ==========================================
        PARTICLE_ALIASES.put("EXPLOSION_NORMAL", "POOF");
        PARTICLE_ALIASES.put("EXPLOSION_LARGE", "EXPLOSION");
        PARTICLE_ALIASES.put("EXPLOSION_HUGE", "EXPLOSION_EMITTER");
        PARTICLE_ALIASES.put("FIREWORKS_SPARK", "FIREWORK");
        PARTICLE_ALIASES.put("WATER_BUBBLE", "BUBBLE");
        PARTICLE_ALIASES.put("WATER_SPLASH", "SPLASH");
        PARTICLE_ALIASES.put("WATER_WAKE", "FISHING");
        PARTICLE_ALIASES.put("SUSPENDED", "UNDERWATER");
        PARTICLE_ALIASES.put("SUSPENDED_DEPTH", "UNDERWATER");
        PARTICLE_ALIASES.put("CRIT_MAGIC", "ENCHANTED_HIT");
        PARTICLE_ALIASES.put("MAGIC_CRIT", "ENCHANTED_HIT");
        PARTICLE_ALIASES.put("SMOKE_NORMAL", "SMOKE");
        PARTICLE_ALIASES.put("SMOKE_LARGE", "LARGE_SMOKE");
        PARTICLE_ALIASES.put("SPELL", "EFFECT");
        PARTICLE_ALIASES.put("INSTANT_SPELL", "INSTANT_EFFECT");
        PARTICLE_ALIASES.put("SPELL_INSTANT", "INSTANT_EFFECT");
        PARTICLE_ALIASES.put("SPELL_MOB", "ENTITY_EFFECT");
        PARTICLE_ALIASES.put("SPELL_MOB_AMBIENT", "ENTITY_EFFECT");
        PARTICLE_ALIASES.put("SPELL_WITCH", "WITCH");
        PARTICLE_ALIASES.put("VILLAGER_HAPPY", "HAPPY_VILLAGER");
        PARTICLE_ALIASES.put("VILLAGER_ANGRY", "ANGRY_VILLAGER");
        PARTICLE_ALIASES.put("DRIP_WATER", "DRIPPING_WATER");
        PARTICLE_ALIASES.put("WATER_DRIP", "DRIPPING_WATER");
        PARTICLE_ALIASES.put("DRIP_LAVA", "DRIPPING_LAVA");
        PARTICLE_ALIASES.put("LAVA_DRIP", "DRIPPING_LAVA");
        PARTICLE_ALIASES.put("TOWN_AURA", "MYCELIUM");
        PARTICLE_ALIASES.put("ENCHANTMENT_TABLE", "ENCHANT");
        PARTICLE_ALIASES.put("REDSTONE", "DUST");
        PARTICLE_ALIASES.put("SNOWBALL", "ITEM_SNOWBALL");
        PARTICLE_ALIASES.put("SNOW_SHOVEL", "POOF");
        PARTICLE_ALIASES.put("SLIME", "ITEM_SLIME");
        PARTICLE_ALIASES.put("BARRIER", null);
        PARTICLE_ALIASES.put("FOOTSTEP", null);
        PARTICLE_ALIASES.put("ITEM_TAKE", null);
        PARTICLE_ALIASES.put("ITEM_CRACK", "ITEM");
        PARTICLE_ALIASES.put("BLOCK_CRACK", "BLOCK");
        PARTICLE_ALIASES.put("BLOCK_DUST", "BLOCK");
        PARTICLE_ALIASES.put("WATER_DROP", "RAIN");
        PARTICLE_ALIASES.put("MOB_APPEARANCE", "ELDER_GUARDIAN");
        PARTICLE_ALIASES.put("TOTEM", "TOTEM_OF_UNDYING");
    }

    /**
     * Validate and convert an enum value. Returns the canonical uppercase name,
     * or null if invalid/unconvertible. Logs warnings for invalid values.
     * Accepts Enum classes (Particle, Effect) and interfaces (Sound since 1.21.3).
     */
    public static String validate(String value, Class<?> clazz, String configKey, Logger logger) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        String upper = value.trim().toUpperCase();

        // 1. Exact match against current server's class/enum
        if (isValidEnum(upper, clazz)) {
            return upper;
        }

        // 2. Alias lookup
        Map<String, String> aliases = getAliases(clazz);
        if (aliases != null && aliases.containsKey(upper)) {
            String mapped = aliases.get(upper);
            if (mapped == null) {
                // Explicitly mapped to null (removed/deprecated)
                if (logger != null && configKey != null) {
                    logger.warning(clazz.getSimpleName() + " '" + value + "' is deprecated and removed"
                            + " — clearing " + configKey);
                }
                return null;
            }
            if (isValidEnum(mapped, clazz)) {
                if (logger != null && configKey != null) {
                    logger.info("Converted " + clazz.getSimpleName() + " '" + value + "' → '" + mapped + "'"
                            + " for " + configKey);
                }
                return mapped;
            }
        }

        // 3. Fuzzy match (prefix stripping & prefix prepending)
        String[] prefixes = {"ENTITY_", "BLOCK_", "ITEM_", "MUSIC_", "MUSIC_DISC_", "AMBIENT_", "UI_"};

        for (String prefix : prefixes) {
            // Try stripping prefix (e.g. ENTITY_COW_AMBIENT → COW_AMBIENT on legacy servers)
            if (upper.startsWith(prefix)) {
                String stripped = upper.substring(prefix.length());
                if (isValidEnum(stripped, clazz)) {
                    if (logger != null && configKey != null) {
                        logger.info("Converted " + clazz.getSimpleName() + " '" + value + "' → '" + stripped + "'"
                                + " for " + configKey);
                    }
                    return stripped;
                }
            }

            // Try prepending prefix (e.g. COW_AMBIENT → ENTITY_COW_AMBIENT on modern servers)
            String prepended = prefix + upper;
            if (isValidEnum(prepended, clazz)) {
                if (logger != null && configKey != null) {
                    logger.info("Converted " + clazz.getSimpleName() + " '" + value + "' → '" + prepended + "'"
                            + " for " + configKey);
                }
                return prepended;
            }
        }

        if (logger != null && configKey != null) {
            logger.warning("Invalid " + clazz.getSimpleName() + " '" + value + "' for " + configKey
                    + " — removing.");
        }
        return null;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static boolean isValidEnum(String value, Class<?> clazz) {
        try {
            if (clazz.isEnum()) {
                Enum.valueOf((Class<Enum>) clazz, value);
            } else {
                // 1.21.3+ Spigot turned org.bukkit.Sound into an interface backed by
                // registries. A static valueOf(String) is kept for backwards compatibility.
                clazz.getMethod("valueOf", String.class).invoke(null, value);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static Map<String, String> getAliases(Class<?> clazz) {
        if (clazz == Sound.class) return SOUND_ALIASES;
        if (clazz == Effect.class) return EFFECT_ALIASES;
        if (clazz == Particle.class) return PARTICLE_ALIASES;
        return null;
    }
}