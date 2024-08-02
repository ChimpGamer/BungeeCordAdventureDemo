package nl.chimpgamer.bungeecordadventuredemo.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.feature.pagination.Pagination
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.minimessage.MiniMessage.miniMessage
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.plugin.Command
import nl.chimpgamer.bungeecordadventuredemo.DemoPlugin

class PaginationCommand(private val plugin: DemoPlugin) : Command("pagination") {
    private val paginationBuilder = Pagination.builder()
        .width(53)
        .resultsPerPage(15)
        .renderer(object : Pagination.Renderer {
            override fun renderEmpty(): Component {
                return miniMessage().deserialize("<gray>No results found!")
            }
        })

    private val alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    override fun execute(sender: CommandSender, args: Array<String>) {
        val page = if (args.isEmpty()) 1 else args[0].toInt()

        val rows = ArrayList<Component>()
        for (i in 0..35) {
            val randomString = List(20) { alphabet.random() }.joinToString("")
            rows.add(miniMessage().deserialize("<gray>$randomString!"))
        }
        val render = paginationBuilder.build(Component.text("Random text"), { value: Component?, index ->
            listOf(
                if (value == null) text("${index + 1}. ").color(NamedTextColor.YELLOW).content("ERR?")
                    .color(NamedTextColor.RED) else
                    text("${index + 1}. ")
                        .color(NamedTextColor.YELLOW)
                        .append(value)
            )
        }, { otherPage -> "/$name $otherPage" })
            .render(rows, page)
        render.forEach(plugin.audiences.sender(sender)::sendMessage)
    }
}