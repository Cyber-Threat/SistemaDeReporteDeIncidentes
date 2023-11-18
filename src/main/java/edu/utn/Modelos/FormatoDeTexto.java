package edu.utn.Modelos;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class FormatoDeTexto {
    public static class colores {
        public static final String reset = "\u001B[0m";
        public static final String black = "\u001B[30m";
        public static final String red = "\u001B[31m";
        public static final String green = "\u001B[32m";
        public static final String yellow = "\u001B[33m";
        public static final String blue = "\u001B[34m";
        public static final String purple = "\u001B[35m";
        public static final String cyan = "\u001B[36m";
        public static final String white = "\u001B[37m";
    }
    public static class efectos {
        public static final String bold = "\u001B[1m";
        public static final String dim = "\u001B[2m";
        public static final String italic = "\u001B[3m";
        public static final String underline = "\u001B[4m";
        public static final String blink = "\u001B[5m";
        public static final String reverse = "\u001B[7m";
        public static final String hidden = "\u001B[8m";
    }
    public static class iconos {
        public static final String success = "\t[" + colores.green + "►" + colores.reset + "] ";
        public static final String info = "\t[" + colores.blue + "►" + colores.reset + "] ";
        public static final String actionNeeded = "\t[" + colores.purple + "▲" + colores.reset + "] ";
        public static final String actionDone = "\t[" + colores.cyan + "▼" + colores.reset + "] ";
        public static final String error = "\t[" + colores.red + "▲" + colores.reset + "] ";
        public static final String help = "\t[" + colores.white + "►" + colores.reset + "] ";
        public static final String warning = "\t[" + colores.yellow + "▲" + colores.reset + "] ";
        private static final String username = System.getProperty("user.name");
        static Date date = new Date();
        static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(date);
        public static final String prompt = "[" + colores.green
                + dateFormat.format(date)
                + colores.reset + "] " + colores.white + efectos.bold
                + username + colores.reset + " $ ";
    }
}