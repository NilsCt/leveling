package nilsct.leveling.managers

import java.io.File
import java.io.FileReader

class ConfigReader {

    companion object {
        val configReader = ConfigReader()
    }

    data class Config(
        val token: String,
        val guildID: String,
        val admins: List<String>,
        val logHookURL: String,
        val base64Key: String
    )

    private val file = File("config.txt")

    fun read(): Config {
        val reader = FileReader(file)
        val lines = reader.readLines()
        if (lines.size < 5) throw Exception("Config file structure has been modified")
        val token = lines.first().removePrefix("bot_token=")
        val guildID = lines[1].removePrefix("guild_id=")
        val admins = lines[2].removePrefix("admins_id=").split(',')
        val hook = lines[3].removePrefix("log_hook_url=")
        val base64Key = lines[4].removePrefix("base64_key=")
        return Config(token, guildID, admins, hook, base64Key)
    }
}