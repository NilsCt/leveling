package nilsct.leveling.managers

import nilsct.leveling.Bot.Companion.logHook
import java.text.SimpleDateFormat
import java.time.Instant

class Log { // amélioration possible aligner les logs

    companion object {
        private val latest = mutableListOf<String>()
        private val dateFormat = SimpleDateFormat("HH:mm:ss")

        /*
        time (ERROR) TAG content
        tags : USER, GUILD, CHART, ICON, PING, PURGE, LVL-UP, ROLE, CONTEXT (COMMAND, BUTTON, ...), DAILY, BASE64, LOAD, SAVE, LAUNCH,
         */
        fun log(tag: String, content: String) { // log
            val msg = "${dateFormat.format(Instant.now().toEpochMilli())}  $tag $content"
            println(msg)
            latest.add(msg)
            if (latest.size > 1000) latest.removeFirst()
        }

        fun error(tag: String, content: String) { // log + envoie error webhook + ajoute tag ERROR
            log("ERROR $tag", content)
            logHook.send("`ERROR $tag` $content")
        }

        fun info(tag: String, content: String) { // log + envoie info webhook
            log(tag, content)
            logHook.send("`$tag` $content")
        }
    }
}