package com.common.core.logging;

public final class Misc {

    public static boolean isSecret(String key) {
        if (key != null) {
            for (Secret sc : Secret.values()) {
                if (key.toLowerCase().indexOf(sc.key) != -1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static enum Secret {
        PASSWORD("password", 1, 5),

        PWD("pwd", 1, 5),

        IDNO("idno", 4, 14),

        REALNAME("realname", 2, 3),

        CARDNO("cardno", 4, 12),

        BANKNO("bankno", 4, 12),

        CVV("cvv", 1, 3),

        CERT("cert", 4, 14),

        MOBILE("mobile", 3, 8),

        PHONE("phone", 3, 8),

        PAYCODE("paycode", 1, 5);

        private final String key;
        private final int start;
        private final int end;

        private Secret(String key, int start, int end) {
            this.key = key;
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return this.start;
        }

        public int getEnd() {
            return this.end;
        }
    }

}
