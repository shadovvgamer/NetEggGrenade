package NetEggGrenade;

import org.bukkit.Location;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NetEggGrenade
  extends JavaPlugin
  implements Listener
{
  @Override
public void onEnable()
  {
    getServer().getPluginManager().registerEvents(this, this);
    getConfig().options().copyDefaults(true);
    saveConfig();
  }
  
  @Override
public void onDisable()
  {
    getServer().getPluginManager().registerEvents(this, this);
  }
  
  @EventHandler
  void onEggThrow(ProjectileHitEvent e)
  {
    float Power = getConfig().getInt("Power");
    if ((e.getEntity() instanceof Egg))
    {
      Entity egg = e.getEntity();
      Location loc = egg.getLocation();
      loc.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), Power, false, false);
    }
  }
  
  @EventHandler
  void onCreatureSpawn(CreatureSpawnEvent event)
  {
    if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.EGG) {
      event.setCancelled(true);
    }
  }
}
