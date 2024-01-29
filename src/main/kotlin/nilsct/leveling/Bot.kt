package nilsct.leveling

import club.minnced.discord.webhook.WebhookClient
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent
import nilsct.leveling.interactions.InteractionManager.Companion.interactionManager
import nilsct.leveling.managers.ConfigReader.Companion.configReader
import nilsct.leveling.managers.DataSaver.Companion.dataSaver
import nilsct.leveling.managers.Log
import java.awt.Color
import java.time.Instant
import java.util.*

class Bot {

    companion object {
        lateinit var jda: JDA

        private var token = "" // très secret

        private val gatewayIntents = listOf(
            GatewayIntent.GUILD_MESSAGES, // ne donne pas accès au contenu des messages
            GatewayIntent.GUILD_VOICE_STATES,
            GatewayIntent.GUILD_MEMBERS,  // nécessaire pour traquer les events voice join / leave (+ facilite la gestion des rôles)
        )
        private var guildID = ""
        var admins = emptyList<String>()

        lateinit var logHook: WebhookClient
        var base64Key = ""

        private val cyanColor = Color(80, 165, 230)
//        private val burple = Color(88, 101, 242)
//        private val lightBurple = Color(114, 137, 218)

        val blueEmbed: EmbedBuilder
            get() = EmbedBuilder().setColor(cyanColor)


        /*
            Les mentions ne sont pas dans InteractionManager,
            car quand on génère les commandes,
            certaines commandes ont besoin des mentions d'autres commandes en cours de génération et ça bug.
         */
        private val mentions = listOf(
            "</help:958701083553849384>", // all
            "</server:958701084115873883>",
            "</privacy:958701086682808361>",
            "</Check stats:958701168366862377>",
            "</my-stats:1007220831090397184>",
            "</member:1007220831962804234>",
            "</leaderboard:1007220832919109683>",
            "</reset server:1007220834026389555>",
            "</reset member:1007220834026389555>",
            "</roles menu:1007220835058188299>",
            "</roles add:1007220835058188299>",
            "</roles remove:1007220835058188299>",
            "</my-roles:1007220916532543568>",
            "</level-up menu:1007220917593723020>",
            "</Reset stats:1007220919422439425>",
            "</stop:1009485821180657674>",
            "</add-xp:1009893551875825794>",
            "</remove-xp:1009896015282851981>",
            "</reset-myself:1011238102142046268>",
            // --- dev ---
            "</load:1007220831086182400>",
            "</save:1007220832164118600>",
            "</back-up:1007220833497927680>",
            "</activity:1021047185263050853>"
        )

        fun mention(command: String) = mentions.first { command in it }


        val readyListener = ReadyListener()

        @JvmStatic
        fun main(args: Array<String>) {
            val config = configReader.read()
            token = config.token
            guildID = config.guildID
            admins = config.admins
            logHook = WebhookClient.withUrl(config.logHookURL)
            base64Key = config.base64Key

            try {
                val start = Instant.now().toEpochMilli()
                val jdaBuilder = JDABuilder.create(
                    token,
                    gatewayIntents
                )
                    .setActivity(Activity.playing("Launching"))
                jda = jdaBuilder.build().awaitReady()
                interactionManager.syncCommand()
                val supportGuild =
                    jda.getGuildById(guildID) ?: throw Exception("Support guild null $guildID")
                interactionManager.syncCommandSupport(supportGuild)
                Log.info("LAUNCH", "Preparation done ${Instant.now().toEpochMilli() - start} ms")
                launchDailyTask()
                dataSaver.load(onStart = true, success = {
                    jda.addEventListener(readyListener) // à la fin pour attendre que tout soit bien construit
                    jda.presence.setPresence(OnlineStatus.ONLINE, Activity.watching("/help"))
                    Log.info("LAUNCH", "Done ${Instant.now().toEpochMilli() - start} ms")
                }, failure = {
                    Log.info("LAUNCH", "Stopped")
                })
            } catch (e: Exception) {
                Log.error("LAUNCH", e.toString())
            }
        }

        private fun launchDailyTask() {
            val task: TimerTask = object : TimerTask() {
                override fun run() {
                    dataSaver.save(autoSave = true)
                }
            }
            val timer = Timer("AutoSave")
            // attend un jour puis tous les jours (pour éviter les répétitions lors du développement)
            val calendar = Calendar.getInstance()
            val secondsElapsed =
                calendar.get(Calendar.HOUR_OF_DAY) * 3600 + calendar.get(Calendar.MINUTE) * 60 + calendar.get(Calendar.SECOND) // secondes écoulées dans la journée
            val delay = if (secondsElapsed > 43200) { // /!\ delay en seconde
                129600L - secondsElapsed // 12h demain
            } else {
                43200L - secondsElapsed  // 12h aujourd'hui
            }
            timer.scheduleAtFixedRate(task, delay * 1000, 86400000)
            Log.log("LAUNCH", "Auto-save started")
        }
    }
}