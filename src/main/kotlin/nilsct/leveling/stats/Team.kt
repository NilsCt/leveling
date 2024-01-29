package nilsct.leveling.stats

import nilsct.leveling.entities.Privacy
import nilsct.leveling.entities.Server
import nilsct.leveling.entities.Type

/**
 * Somme des statistiques des "mate" d'un "group"
 */
class Team(override val type: Type, val server: Server?) : Stats() {

    override val privacy = Privacy.NORMAL
}