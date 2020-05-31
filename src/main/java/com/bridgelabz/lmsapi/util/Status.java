package com.bridgelabz.lmsapi.util;

public enum Status {
    OK {
        @Override
        public String toString() {
            return "OK";
        }
    },
    ACCEPTED {
        @Override
        public String toString() {
            return "ACCEPTED";
        }
    },
    REJECTED {
        @Override
        public String toString() {
            return "REJECTED";
        }
    },
    PENDING {
        @Override
        public String toString() {
            return "PENDING";
        }
    },
    YES {
        @Override
        public String toString() {
            return "YES";
        }
    },
    NO {
        @Override
        public String toString() {
            return "NO";
        }
    },
    VERIFIED {
        @Override
        public String toString() {
            return "VERIFIED";
        }
    },
    RECEIVED {
        @Override
        public String toString() {
            return "RECEIVED";
        }
    }
}
