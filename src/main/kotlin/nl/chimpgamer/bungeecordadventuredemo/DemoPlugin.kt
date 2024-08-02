package nl.chimpgamer.bungeecordadventuredemo

import net.kyori.adventure.platform.bungeecord.BungeeAudiences
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.api.plugin.Plugin
import nl.chimpgamer.bungeecordadventuredemo.commands.PaginationCommand

class DemoPlugin : Plugin(), Listener {

    lateinit var audiences: BungeeAudiences

    override fun onEnable() {
        audiences = BungeeAudiences.create(this)

        proxy.pluginManager.registerCommand(this, PaginationCommand(this))
    }

    override fun onDisable() {
        audiences.close()
    }
}