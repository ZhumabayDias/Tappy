package com.tappy.app.data

import com.tappy.domain.model.Question

object QuestionSeeder {
    fun getInitialQuestions(): List<Question> = listOf(
        // Android
        Question("android1", "What is the base language for Android?", listOf("Kotlin", "Swift", "C#"), 0, "Android"),
        Question("android2", "What is Jetpack Compose used for?", listOf("UI", "Networking", "Storage"), 0, "Android"),
        Question("android3", "Which component is used for background tasks?", listOf("Service", "Broadcast", "Intent"), 0, "Android"),
        Question("android4", "Which XML tag defines UI layout?", listOf("LinearLayout", "FrameLayout", "GridLayout"), 0, "Android"),
        Question("android5", "What is AndroidManifest.xml?", listOf("App metadata", "UI Code", "Gradle Settings"), 0, "Android"),

        // Web Development
        Question("web1", "What does HTML stand for?", listOf("HyperText Markup Language", "Home Tool Markup Language", "Hyperlinks and Text Markup Language"), 0, "Web Development"),
        Question("web2", "Which CSS property changes text color?", listOf("color", "font-style", "background"), 0, "Web Development"),
        Question("web3", "Which is a JavaScript framework?", listOf("React", "Django", "Laravel"), 0, "Web Development"),
        Question("web4", "What port does HTTP use by default?", listOf("80", "443", "21"), 0, "Web Development"),
        Question("web5", "What is used for responsive design?", listOf("Media Queries", "Font size", "Grid"), 0, "Web Development"),

        // Computer Network
        Question("net1", "What does IP stand for?", listOf("Internet Protocol", "Internal Package", "Information Packet"), 0, "Computer Network"),
        Question("net2", "Which layer is TCP in OSI model?", listOf("Transport", "Network", "Session"), 0, "Computer Network"),
        Question("net3", "What device forwards packets?", listOf("Router", "Switch", "Hub"), 0, "Computer Network"),
        Question("net4", "Which protocol uses port 443?", listOf("HTTPS", "HTTP", "FTP"), 0, "Computer Network"),
        Question("net5", "What does DNS do?", listOf("Resolves domain names", "Encrypts data", "Routes packets"), 0, "Computer Network"),

        // IBM
        Question("ibm1", "What does IBM stand for?", listOf("International Business Machines", "Internet Business Model", "Intel Binary Machine"), 0, "IBM"),
        Question("ibm2", "What is IBM Watson?", listOf("AI System", "Cloud Storage", "Database Tool"), 0, "IBM"),
        Question("ibm3", "IBM Cloud is similar to?", listOf("AWS", "Git", "React"), 0, "IBM"),
        Question("ibm4", "What programming language was developed by IBM?", listOf("COBOL", "Java", "Python"), 0, "IBM"),
        Question("ibm5", "IBM is known for which OS?", listOf("AIX", "Linux", "Windows"), 0, "IBM")
    )

}