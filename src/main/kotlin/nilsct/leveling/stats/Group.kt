package nilsct.leveling.stats

import nilsct.leveling.entities.Server
import nilsct.leveling.entities.Type

/**
 * Liste de stats en compétition
 */
class Group(val type: Type, val server: Server?) {

    val list = mutableListOf<Stats>()

    var name = ""
}