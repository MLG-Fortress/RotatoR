package net.awesomepowered.rotator.types;

import com.google.common.util.concurrent.AtomicDouble;
import net.awesomepowered.rotator.RotatoR;
import net.awesomepowered.rotator.Spinnable;
import net.awesomepowered.rotator.event.RotatorSpinEvent;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Rotation;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;
import java.util.logging.Level;

public class EntitySpinner implements Spinnable {

    private Entity entity;
    private int mode;
    private int taskID;
    private int rpm;
    private String effect;
    private String sound;
    private String particle;
    private double yawChange = 15; //lower = slower turn
    private AtomicDouble trouble = new AtomicDouble();

    public EntitySpinner(Entity ent, int mode, int rpm) {
        this.entity = ent;
        this.mode = mode;
        this.rpm = rpm;
        if (ent instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) ent;
            livingEntity.setAI(false);
        }
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public double getYawChange() {
        return this.yawChange;
    }

    public void setYawChange(double yawChange) {
        this.yawChange = yawChange;
    }

    public Location getLocation() {
        return entity.getLocation();
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        setYawChange(getYawChange() * -1);
        this.mode = mode;
    }

    public int getTaskID() {
        return this.taskID;
    }

    public void setTaskID(int taskId) {
        this.taskID = taskId;
    }

    public int getRpm() {
        return this.rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public String getEffect() {
        return this.effect;
    }

    public String getSound() {
        return this.sound;
    }

    public String getParticle() {
        return this.particle;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public void setParticle(String particle) {
        this.particle = particle;
    }

    public void refresh() {
        Bukkit.getScheduler().cancelTask(taskID);
        spoolUp();
    }

    public void selfDestruct() {
        if (getEntity() instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) getEntity();
            livingEntity.setAI(true);
        }
        Bukkit.getScheduler().cancelTask(taskID);
        RotatoR.getMain().entitySpinners.remove(entity.getUniqueId());
    }

    /**
     * Re-acquire the live entity if our held reference went stale (e.g. chunk unload/reload).
     * Logs a full state snapshot whenever the held reference is unhealthy; throttled to state
     * transitions so a paused spinner doesn't spam.
     * @return 0 = live and ready, 1 = not in a loaded chunk right now (pause, retry later), 2 = truly dead
     */
    private int lastResolveStatus = 0;

    private int resolveEntity() {
        if (entity != null && !entity.isDead() && entity.isValid()) {
            lastResolveStatus = 0;
            return 0;
        }
        // Held reference is unhealthy; snapshot everything BEFORE touching anything
        UUID uuid = entity.getUniqueId();
        boolean heldDead = entity.isDead();
        boolean heldValid = entity.isValid();
        Entity live = Bukkit.getEntity(uuid);
        String diag = "uuid=" + uuid
                + " type=" + entity.getType()
                + " heldDead=" + heldDead
                + " heldValid=" + heldValid
                + " | liveLookup=" + (live == null ? "absent" : "present")
                + (live == null ? "" : " liveDead=" + live.isDead()
                    + " liveValid=" + live.isValid()
                    + " sameInstance=" + (live == entity))
                + " | originalLogicWouldSelfDestruct=" + heldDead;

        int status;
        if (live != null && !live.isDead()) {
            entity = live;
            status = 0;
            RotatoR.getMain().getLogger().log(Level.INFO, "[diag] stale held ref, re-acquired live entity: " + diag);
        } else if (live != null) {
            status = 2;
            RotatoR.getMain().getLogger().log(Level.WARNING, "Oh noes! An entity is ded! " + diag);
        } else {
            status = 1;
            if (lastResolveStatus != 1) {
                RotatoR.getMain().debug("eSpin", "pausing spin task, entity not in any loaded chunk: " + diag);
            }
        }
        lastResolveStatus = status;
        return status;
    }

    public void spoolUp() {
        Location constant = entity.getLocation();
        RotatoR.getMain().debug("eSpool","Using mode 0");
        if (entity instanceof ItemFrame) {
            RotatoR.getMain().debug("eSpool", "Entity is ItemFrame");
            setTaskID(Bukkit.getScheduler().scheduleSyncRepeatingTask(RotatoR.getMain(), () -> {
                RotatorSpinEvent rotatorSpinEvent = new RotatorSpinEvent(this);
                Bukkit.getServer().getPluginManager().callEvent(rotatorSpinEvent);
                if (rotatorSpinEvent.isCancelled()) {
                    return;
                }
                int status = resolveEntity();
                if (status != 0) {
                    if (status == 2) {
                        selfDestruct();
                    }
                    return;
                }
                ItemFrame itemFrame = (ItemFrame) entity;
                if (mode == 0) {
                    itemFrame.setRotation(itemFrame.getRotation().rotateClockwise());
                } else {
                    itemFrame.setRotation(itemFrame.getRotation().rotateCounterClockwise());
                }
                play();
            }, 0, rpm));
        } else {
            RotatoR.getMain().debug("eSpool", "Entity is LivingEntity");
            setTaskID(Bukkit.getScheduler().scheduleSyncRepeatingTask(RotatoR.getMain(), () -> {
                RotatorSpinEvent rotatorSpinEvent = new RotatorSpinEvent(this);
                Bukkit.getServer().getPluginManager().callEvent(rotatorSpinEvent);
                if (rotatorSpinEvent.isCancelled()) {
                    return;
                }
                int status = resolveEntity();
                if (status != 0) {
                    if (status == 2) {
                        selfDestruct();
                    }
                    return;
                }
                constant.setYaw((float) trouble.getAndAdd(yawChange) % 360); //shhh
                entity.teleport(constant);
                play();
            }, 0, rpm));
        }


    }

    public void play() {
        Location location = entity.getLocation().add(0.5, 0, 0.5);
        if (entity instanceof ItemFrame) {
            ItemFrame frame = (ItemFrame)entity;
            BlockFace face = frame.getFacing();
            location = entity.getLocation().add(face.getModX() / 2.0, 0, face.getModY() / 2.0);
            //location = entity.getLocation().getBlock().getRelative(frame.getFacing()).getLocation().add(0.5,0,0.5);
        }

        if (sound != null) {
            entity.getLocation().getWorld().playSound(location, Sound.valueOf(sound), 1, 1);
        }
        if (effect != null) {
            entity.getLocation().getWorld().playEffect(location, Effect.valueOf(effect), 1);
        }
        if (particle != null) {
            entity.getLocation().getWorld().spawnParticle(Particle.valueOf(particle), location, 1);
        }
    }
}
