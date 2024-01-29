package nilsct.leveling.interactions.components

import nilsct.leveling.interactions.Interaction
import nilsct.leveling.interactions.InteractionContext
import nilsct.leveling.stats.StatsManager.Companion.statsManager

class SelfResetButton : Interaction() {

    override val id = "self-reset"

    override fun execute(context: InteractionContext) {
        context as ComponentContext
        when (context.params.first()) {
            "cancel" -> {
                context.edit("Reset canceled.")
                    .setComponents(emptyList())
                    .queue()
            }

            "confirm" -> {
                val lvlMember = context.lvlMember
                val mate = lvlMember.mate
                val totalXp = mate.totalXP
                statsManager.reset(mate)
                context
                    .edit("Your stats in __this server__ have been reset (`$totalXp` xp removed).")
                    .setComponents(emptyList())
                    .queue()
            }
        }
    }
}