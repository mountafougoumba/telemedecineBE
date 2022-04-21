package com.telemedecineBE.enumeration;

public enum MessageType {
        EMAIL("EMAIL"),
        CHAT("CHAT");

        private String type;

        MessageType(String type){
            this.type=type;
        }

        public static MessageType findByName(String name){
            switch (name) {
                case "EMAIL":
                    return EMAIL;
                case "CHAT":
                    return CHAT;
            }
            return null;
        }

        public String getType() {
            return type;
        }
}
